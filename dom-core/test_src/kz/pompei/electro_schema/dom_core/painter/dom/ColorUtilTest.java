package kz.pompei.electro_schema.dom_core.painter.dom;

import org.testng.annotations.Test;

import java.awt.Color;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorUtilTest {

  @Test
  public void parseColor_001() {
    Color color = ColorUtil.parseColor("red");

    assertThat(color).isNotNull();
    assertThat(color.getRed()   ).isEqualTo(255);
    assertThat(color.getGreen() ).isEqualTo(0);
    assertThat(color.getBlue()  ).isEqualTo(0);
    assertThat(color.getAlpha() ).isEqualTo(255);
  }

  @Test
  public void parseColor_002() {
    Color color = ColorUtil.parseColor("rgb 100 ,234, 17");

    assertThat(color).isNotNull();
    assertThat(color.getRed()   ).isEqualTo(100);
    assertThat(color.getGreen() ).isEqualTo(234);
    assertThat(color.getBlue()  ).isEqualTo(17);
    assertThat(color.getAlpha() ).isEqualTo(255);
  }

  @Test
  public void parseColor_003() {
    Color color = ColorUtil.parseColor("rgba 230,101, 117 , 178");

    assertThat(color).isNotNull();
    assertThat(color.getRed()   ).isEqualTo(230);
    assertThat(color.getGreen() ).isEqualTo(101);
    assertThat(color.getBlue()  ).isEqualTo(117);
    assertThat(color.getAlpha() ).isEqualTo(178);
  }
}
