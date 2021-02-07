package kz.pompei.electro_schema.frontend.images;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class AbstractFloatRgbRaster {
  public abstract int width();

  public abstract int height();

  public abstract void setAlpha(int x, int y, float alfa);

  public abstract void setRed(int x, int y, float red);

  public abstract void setGreen(int x, int y, float green);

  public abstract void setBlue(int x, int y, float blue);

  public abstract float getAlpha(int x, int y);

  public abstract float getRed(int x, int y);

  public abstract float getGreen(int x, int y);

  public abstract float getBlue(int x, int y);

  public abstract BufferedImage toImage();

  public void setRgb(int x, int y, float red, float green, float blue) {
    setRed(x, y, red);
    setGreen(x, y, green);
    setBlue(x, y, blue);
  }

  public void setArgb(int x, int y, float alpha, float red, float green, float blue) {
    setAlpha(x, y, alpha);
    setRed(x, y, red);
    setGreen(x, y, green);
    setBlue(x, y, blue);
  }

  public void set(int x, int y, Color color) {
    setAlpha(x, y, (float) color.getAlpha() / 255f);
    setRed(x, y, (float) color.getRed() / 255f);
    setGreen(x, y, (float) color.getGreen() / 255f);
    setBlue(x, y, (float) color.getBlue() / 255f);
  }

  public Color get(int x, int y) {
    return new Color(getRed(x, y), getGreen(x, y), getBlue(x, y), getAlpha(x, y));
  }

}
