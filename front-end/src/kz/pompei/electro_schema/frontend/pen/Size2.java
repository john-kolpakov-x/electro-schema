package kz.pompei.electro_schema.frontend.pen;

public class Size2 {
  public double w, h;

  public static Size2 wh(int width, int height) {
    var ret = new Size2();
    ret.w = width;
    ret.h = height;
    return ret;
  }
}
