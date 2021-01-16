package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Branch;
import kz.pompei.electro_schema.dom_core.dom.DomError;
import kz.pompei.electro_schema.dom_core.dom.Node;
import kz.pompei.electro_schema.dom_core.parser.flow.TextFlow;
import kz.pompei.electro_schema.dom_core.parser.token.Token;
import kz.pompei.electro_schema.dom_core.parser.token.TokenType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BranchImpl implements Branch {
  private final List<DomError> domErrors;
  private final Grouper        grouper;

  public BranchImpl(List<DomError> domErrors) {
    this.domErrors = domErrors;
    grouper        = new Grouper(domErrors);
  }

  @Override
  public Set<String> groups() {
    return grouper.groupNames();
  }

  @Override
  public Map<String, String> group(String groupName) {
    return grouper.byName(groupName);
  }

  @Override
  public int childrenCount() {
    return children.size();
  }

  @Override
  public Node childAt(int index) {
    return children.get(index);
  }

  private final List<Node> children = new ArrayList<>();

  @Override
  public Iterator<Node> iterator() {
    return children.iterator();
  }

  public void parseToken(Token token) {
    if (token.type() != TokenType.BRACE) {
      throw new RuntimeException("cRJeLr4J55 :: Illegal charType = " + token.type());
    }

    List<TextFlow> textFlowList = new ArrayList<>();

    for (Token child : token.children()) {
      switch (child.type()) {
        case BRACE -> {
          BranchImpl childBranch = new BranchImpl(domErrors);
          childBranch.parseToken(child);
          children.add(childBranch);
        }

        case ROUND -> children.add(new TextImpl(child.innerText()));

        case TEXT, SQUARE -> textFlowList.add(new TextFlow(
          child.type().flowType(), child.innerText(), child.begin(), child.end()
        ));
      }
    }

    grouper.parse(textFlowList);

  }

  @Override
  public void print(StringBuilder out, int beginSpace) {
    out.append("  ".repeat(beginSpace)).append("{\n");
    grouper.print(out, beginSpace + 1);
    for (Node child : children) {
      child.print(out, beginSpace + 1);
    }
    out.append("  ".repeat(beginSpace)).append("}\n");
  }
}
