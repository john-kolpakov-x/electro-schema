package kz.pompei.electro_schema.frontend.images;

import java.awt.image.BufferedImage;

public class FloatRgbRaster extends AbstractFloatRgbRaster {
  private final int     width;
  private final int     height;
  private final float[] channelAlpha;
  private final float[] channelRed;
  private final float[] channelGreen;
  private final float[] channelBlue;

  public FloatRgbRaster(int width, int height) {
    this.width  = width;
    this.height = height;
    int size = width * height;
    channelAlpha = new float[size];
    channelRed   = new float[size];
    channelGreen = new float[size];
    channelBlue  = new float[size];
    for (int i = 0; i < size; i++) {
      channelAlpha[i] = 1;
    }
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

  private int xy(int x, int y) {
    return x + y * width;
  }

  @Override
  public void setAlpha(int x, int y, float alfa) {
    if (isOut(x, y)) return;
    channelAlpha[xy(x, y)] = correctColorValue(alfa);
  }

  @Override
  public void setRed(int x, int y, float red) {
    if (isOut(x, y)) return;
    channelRed[xy(x, y)] = correctColorValue(red);
  }

  @Override
  public void setGreen(int x, int y, float green) {
    if (isOut(x, y)) return;
    channelGreen[xy(x, y)] = correctColorValue(green);
  }

  @Override
  public void setBlue(int x, int y, float blue) {
    if (isOut(x, y)) return;
    channelBlue[xy(x, y)] = correctColorValue(blue);
  }

  @Override
  public float getAlpha(int x, int y) {
    if (isOut(x, y)) return 0;
    return channelAlpha[xy(x, y)];
  }
  @Override
  public float getRed(int x, int y) {
    if (isOut(x, y)) return 0;
    return channelRed[xy(x, y)];
  }

  @Override
  public float getGreen(int x, int y) {
    if (isOut(x, y)) return 0;
    return channelGreen[xy(x, y)];
  }

  @Override
  public float getBlue(int x, int y) {
    if (isOut(x, y)) return 0;
    return channelBlue[xy(x, y)];
  }

  private boolean isOut(int x, int y) {
    return x < 0 || width <= x || y < 0 || height <= y;
  }

  private float correctColorValue(float value) {
    if (value < 0) return 0;
    if (value > 1) return 1;
    return value;
  }

  @Override
  public BufferedImage toImage() {
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    int   size  = width * height;
    int[] array = new int[size];
    for (int i = 0; i < size; i++) {
      array[i] = toIntColor(channelAlpha[i], channelRed[i], channelGreen[i], channelBlue[i]);
    }

    img.setRGB(0, 0, width, height, array, 0, width);

    return img;
  }

  private static int toIntColor(float alfa, float red, float green, float blue) {
    int ALFA  = Math.round(alfa * 255f);
    int RED   = Math.round(red * 255f);
    int GREEN = Math.round(green * 255f);
    int BLUE  = Math.round(blue * 255f);
    return ((BLUE & 0xFF) << 0) | ((GREEN & 0xFF) << 8) | ((RED & 0xFF) << 16) | ((ALFA & 0xFF) << 24);
  }

}
