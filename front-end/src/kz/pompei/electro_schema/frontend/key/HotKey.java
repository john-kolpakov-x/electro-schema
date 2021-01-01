package kz.pompei.electro_schema.frontend.key;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.awt.event.KeyEvent;

@ToString
@EqualsAndHashCode
public class HotKey {

  public final int             code;
  public final HotKeyModifiers mod;

  public HotKey(int code) {
    this.code = code;
    this.mod  = HotKeyModifiers.empty();
  }

  public HotKey(int code, HotKeyModifiers mod) {
    this.code = code;
    this.mod  = mod;
  }

  public HotKey with(HotKeyModifier... modifier) {
    var mod = this.mod;
    for (HotKeyModifier x : modifier) {
      mod = mod.with(x);
    }
    return new HotKey(code, mod);
  }

  public static final HotKey R     = new HotKey(KeyEvent.VK_R);
  public static final HotKey Right = new HotKey(KeyEvent.VK_RIGHT);
  public static final HotKey Left  = new HotKey(KeyEvent.VK_LEFT);


  public HotKey shift() {
    return with(HotKeyModifier.SHIFT);
  }

  public HotKey ctrl() {
    return with(HotKeyModifier.CTRL);
  }

  public HotKey alt() {
    return with(HotKeyModifier.ALT);
  }

  public boolean doesItMatch(KeyEvent e) {
    return e.getKeyCode() == code && mod.doesItMatch(e);
  }
}
