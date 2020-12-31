package kz.pompei.electro_schema.frontend.pen;

public class RealScreenConverter {

  public double kx     = 1;
  public double ky     = 1;
  public double deltaX = 0;
  public double deltaY = 0;

  public double screenWidth  = 0;
  public double screenHeight = 0;

  public Vec2 toScreen(Vec2 realPoint) {
    return Vec2.xy(
      realPoint.x * kx + deltaX,
      realPoint.y * ky + deltaY
    );
  }

  public Vec2 toReal(double x, double y) {
    return toReal(Vec2.xy(x, y));
  }

  public Vec2 toReal(Vec2 screenPoint) {
    return Vec2.xy(
      (screenPoint.x - deltaX) / kx,
      (screenPoint.y - deltaY) / ky
    );
  }

  public Vec2 getDelta() {
    return Vec2.xy(deltaX, deltaY);
  }

  public void setDelta(Vec2 point) {
    deltaX = point.x;
    deltaY = point.y;
  }

}
