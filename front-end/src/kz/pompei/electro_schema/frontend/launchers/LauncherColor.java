package kz.pompei.electro_schema.frontend.launchers;

import kz.pompei.electro_schema.frontend.images.FloatRgbRaster;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LauncherColor {
  public static void main(String[] args) throws Exception {
    new LauncherColor().execute();
  }

  private void execute() throws Exception {

    Path dir = Paths.get("build").resolve(getClass().getSimpleName());
    dir.toFile().mkdirs();

    Color color = Color.getHSBColor(0.5f, 1, 1);
//    Color color = new Color(255,0,0);
    System.out.println("color = " + color);

    FloatRgbRaster raster = new FloatRgbRaster(800, 600);
    for (int y = 0; y < raster.height(); y++) {
      for (int x = 0; x < raster.width(); x++) {
        var z = f(x - raster.width() / 2f, raster.height() / 2f - y);
        raster.set(x, y, rainbowColor((z + 1) / 2));
      }
    }


    ImageIO.write(raster.toImage(), "png", dir.resolve("out.png").toFile());

  }

  private static float f(float x, float y) {
    float x1 = x + 100, y1 = y + 100;
    float x2 = x + 100, y2 = y - 100;
    float x3 = x - 100, y3 = y + 100;
    float x4 = x - 100, y4 = y - 100;
    float r0 = (float) Math.sqrt(x * x + y * y);
    float r1 = (float) Math.sqrt(x1 * x1 + y1 * y1);
    float r2 = (float) Math.sqrt(x2 * x2 + y2 * y2);
    float r3 = (float) Math.sqrt(x3 * x3 + y3 * y3);
    float r4 = (float) Math.sqrt(x4 * x4 + y4 * y4);

    float d = 10f;

    return (float) (
      Math.sin(r0 / d) + Math.sin(r1 / d) + Math.sin(r2 / d) + Math.sin(r3 / d) + Math.sin(r4 / d)
    ) /2f ;
  }

  private static Color rainbowColor(float v) {
    if (v < 0) v = 0;
    else if (v > 1) v = 1;

    return Color.getHSBColor((1 - v) * (240f / 360f), 1, 1);
  }

}
