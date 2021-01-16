package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.DomError;
import kz.pompei.electro_schema.dom_core.parser.flow.TextFlow;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class Grouper {
  private final List<DomError> domErrors;

  public Grouper(List<DomError> domErrors) {
    this.domErrors = domErrors;
  }

  private final Map<String, Map<String, String>> data = new HashMap<>();

  public Set<String> groupNames() {
    return data.keySet();
  }

  public Map<String, String> byName(String groupName) {
    Map<String, String> group = data.get(groupName);
    return group == null ? Map.of() : Collections.unmodifiableMap(group);
  }

  public void print(StringBuilder out, int beginSpace) {
    for (String groupName : groupNames().stream().sorted().collect(toList())) {
      out.append("  ".repeat(beginSpace)).append(groupName);
      for (Map.Entry<String, String> e : byName(groupName).entrySet().stream()
                                                          .sorted(Map.Entry.comparingByKey())
                                                          .collect(toList())) {

        out.append(' ').append(e.getKey()).append('[').append(e.getValue()).append(']');

      }
      out.append('\n');
    }
  }

  public void parse(List<TextFlow> textFlowList) {

    TextFlow prevPlainText = null;

    for (TextFlow textFlow : textFlowList) {
      if (!textFlow.isSquare()) {
        prevPlainText = textFlow;
      } else {

        if (prevPlainText == null || prevPlainText.text.isBlank()) {
          domErrors.add(new DomError("0egA536thy", "Значение без ключа: `" + textFlow.text + "`",
                                     textFlow.begin, textFlow.end));
          continue;
        }
        appendKeyValue(prevPlainText, textFlow);
        prevPlainText = null;
      }
    }

    if (prevPlainText != null && prevPlainText.text.trim().length() > 0) {
      domErrors.add(new DomError("Nk1pNYe2jo", "Ключ без значения `" + prevPlainText.text + "`",
                                 prevPlainText.begin, prevPlainText.end));
    }

  }

  private String currentGroupName = "";

  private void appendKeyValue(TextFlow keyText, TextFlow valueText) {

    String key = keyText.text.trim().replace('\n', ' ').replace('\t', ' ');
    int    idx = key.lastIndexOf(' ');

    if (idx < 0) {
      doAppendKeyValue(key, keyText, valueText);
      return;
    }

    currentGroupName = key.substring(0, idx).trim();
    doAppendKeyValue(key.substring(idx + 1).trim(), keyText, valueText);

  }

  private void doAppendKeyValue(String key, TextFlow keyText, TextFlow valueText) {

    Map<String, String> groupData = data.get(currentGroupName);
    if (groupData == null) {
      HashMap<String, String> map = new HashMap<>();
      map.put(key, valueText.text);
      data.put(currentGroupName, map);
      return;
    }

    {
      String value = groupData.get(key);
      if (value != null) {
        domErrors.add(new DomError("3wwa6zS0M5", "Повторное определение параметра " + key + " - игнорируется",
                                   keyText.begin, keyText.end));
        return;
      }
    }

    groupData.put(key, valueText.text);

  }
}
