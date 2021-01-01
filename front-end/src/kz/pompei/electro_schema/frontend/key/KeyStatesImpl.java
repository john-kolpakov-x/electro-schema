package kz.pompei.electro_schema.frontend.key;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class KeyStatesImpl implements KeyStates {
  @Override
  public boolean isCapsLockDown() {
    return Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
  }

  public boolean isShiftDown = false;
  public boolean isCtrlDown  = false;
  public boolean isAltDown   = false;

  @Override
  public boolean isShiftDown() {
    return isShiftDown;
  }

  @Override
  public boolean isCtrlDown() {
    return isCtrlDown;
  }

  @Override
  public boolean isAltDown() {
    return isAltDown;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (isCapsLockDown()) {
      sb.append("CapsLock ");
    }
    if (isShiftDown) {
      sb.append("Shift ");
    }
    if (isCtrlDown) {
      sb.append("Ctrl ");
    }
    if (isAltDown) {
      sb.append("Alt ");
    }
    return sb.toString().trim();
  }
}
