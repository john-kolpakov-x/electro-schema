package kz.pompei.electro_schema.frontend.key;

import java.util.HashMap;
import java.util.Map;

public class KeyCommandAppenderImpl implements KeyCommandAppender {

  public final Map<HotKey, KeyCommand> cmdMap = new HashMap<>();

  @Override
  public KeyCommandAppender append(KeyCommand keyCommand) {
    cmdMap.put(keyCommand.hotKey(), keyCommand);
    return this;
  }

  public void clear() {
    cmdMap.clear();
  }
}
