package kz.pompei.electro_schema.frontend.key;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class KeyCommandAppenderImpl implements KeyCommandAppender {
  private final @NonNull KeyStates keyStates;

  public final Map<HotKey, KeyCommand> cmdMap = new HashMap<>();

  @Override
  public KeyCommandAppender append(KeyCommand keyCommand) {
    var hotKey = keyCommand.hotKey();
    if (hotKey.stateEquals(keyStates)) {
      cmdMap.put(hotKey, keyCommand);
    }
    return this;
  }

  public void clear() {
    cmdMap.clear();
  }
}
