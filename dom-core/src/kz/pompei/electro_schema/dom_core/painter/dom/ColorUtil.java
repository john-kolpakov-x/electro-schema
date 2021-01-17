package kz.pompei.electro_schema.dom_core.painter.dom;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.reflect.Modifier.isStatic;

public class ColorUtil {

  private static final Map<String, Color> FIX_COLORS = new HashMap<>();

  static {
    for (Field field : Color.class.getFields()) {
      if (isStatic(field.getModifiers())) {
        try {
          String name  = field.getName().toUpperCase();
          Object color = field.get(null);
          if (color instanceof Color c) {
            FIX_COLORS.put(name, c);
          }
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  private static final Pattern
    RGB = Pattern.compile("\\s*rgb\\s+(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*",
                          Pattern.CASE_INSENSITIVE);

  private static final Pattern
    RGBA = Pattern.compile("\\s*rgba\\s+(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(\\d+)\\s*",
                           Pattern.CASE_INSENSITIVE);

  public static Color parseColor(String color) {
    if (color == null) return null;

    Color fixedColor = FIX_COLORS.get(color.trim().toUpperCase());
    if (fixedColor != null) {
      return fixedColor;
    }

    {
      Matcher m = RGB.matcher(color);
      if (m.matches()) {
        int red   = Integer.parseInt(m.group(1));
        int green = Integer.parseInt(m.group(2));
        int blue  = Integer.parseInt(m.group(3));
        return new Color(red, green, blue);
      }
    }

    {
      Matcher m = RGBA.matcher(color);
      if (m.matches()) {
        int red   = Integer.parseInt(m.group(1));
        int green = Integer.parseInt(m.group(2));
        int blue  = Integer.parseInt(m.group(3));
        int alpha = Integer.parseInt(m.group(4));
        return new Color(red, green, blue, alpha);
      }
    }

    return null;
  }
}
