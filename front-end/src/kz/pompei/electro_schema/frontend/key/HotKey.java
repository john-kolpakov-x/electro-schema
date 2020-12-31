package kz.pompei.electro_schema.frontend.key;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.awt.event.KeyEvent;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class HotKey {

  public final int     code;
  public final boolean shift;
  public final boolean ctrl;
  public final boolean alt;

  public static final HotKey R     = new HotKey(KeyEvent.VK_R, false, false, false);
  public static final HotKey Right = new HotKey(KeyEvent.VK_RIGHT, false, false, false);
  public static final HotKey Left  = new HotKey(KeyEvent.VK_LEFT, false, false, false);


  public HotKey shift() {
    return new HotKey(code, true, ctrl, alt);
  }

  public HotKey ctrl() {
    return new HotKey(code, shift, true, alt);
  }

  public HotKey alt() {
    return new HotKey(code, shift, ctrl, true);
  }

  public boolean doesItMatch(KeyEvent e) {
    return e.getKeyCode() == code
      && (e.isShiftDown() == shift)
      && (e.isControlDown() == ctrl)
      && (e.isAltDown() == alt);
  }
}
