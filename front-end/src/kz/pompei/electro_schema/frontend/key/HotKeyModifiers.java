package kz.pompei.electro_schema.frontend.key;

import lombok.RequiredArgsConstructor;

import java.awt.event.KeyEvent;

@RequiredArgsConstructor
@SuppressWarnings("ClassCanBeRecord")
public class HotKeyModifiers {
  public final boolean shift;
  public final boolean ctrl;
  public final boolean alt;

  public static HotKeyModifiers empty() {
    return new HotKeyModifiers(false, false, false);
  }

  public HotKeyModifiers with(HotKeyModifier modifier) {
    return switch (modifier) {
      case SHIFT -> new HotKeyModifiers(true, ctrl, alt);
      case CTRL -> new HotKeyModifiers(shift, true, alt);
      case ALT -> new HotKeyModifiers(shift, ctrl, true);
    };
  }

  public boolean doesItMatch(KeyEvent e) {
    return shift == e.isShiftDown() && ctrl == e.isControlDown() && alt == e.isAltDown();
  }

  public boolean stateEquals(KeyStates keyStates) {
    return shift == keyStates.isShiftDown()
      && ctrl == keyStates.isCtrlDown()
      && alt == keyStates.isAltDown();
  }
}
