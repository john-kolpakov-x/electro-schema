package kz.pompei.electro_schema.dom_core.painter.dom;

import kz.pompei.electro_schema.dom_core.dom.Branch;
import kz.pompei.electro_schema.dom_core.dom.Node;
import kz.pompei.electro_schema.dom_core.dom.Text;
import lombok.RequiredArgsConstructor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Map;

@RequiredArgsConstructor
public class FixRect {
  final Branch branch;
  final int    x;
  final int    y;
  final int    width;
  final int    height;

  public FixRect(Branch subBranch, Rectangle rect) {
    this.branch = subBranch;
    x           = rect.x;
    y           = rect.y;
    width       = rect.width;
    height      = rect.height;
  }

  public void paint(Graphics2D g) {
    Color color = ColorUtil.parseColor(branch.style().get("color"));
    if (color != null) {
      g.setColor(color);
      g.fillRect(x, y, width, height);
    }

    for (Node node : branch) {
      if (node instanceof Branch b) {
        paintSubBranch(g, b);
        continue;
      }
      if (node instanceof Text text) {
        paintText(g, text);
        continue;
      }
    }
  }

  int textY = 0;

  private void paintText(Graphics2D g, Text text) {
    Color color = ColorUtil.parseColor(branch.style().get("text-color"));
    if (color != null) {
      g.setColor(color);
    }

    int textHeight = g.getFontMetrics().getHeight();
    g.drawString(text.content(), x, textY);
    textY += textHeight;
  }

  private void paintSubBranch(Graphics2D g, Branch subBranch) {
    String display = subBranch.style().get("display");

    if ("rect".equals(display)) {
      Rectangle rect = readRect(subBranch.style());
      new FixRect(subBranch, rect).paint(g);
    }

  }

  private Rectangle readRect(Map<String, String> style) {
    Integer iLeft  = IntUtil.readInt(style.get("left"), width);
    Integer iWidth = IntUtil.readInt(style.get("width"), width);
    Integer iRight = IntUtil.readInt(style.get("right"), width);

    Integer iTop    = IntUtil.readInt(style.get("top"), height);
    Integer iHeight = IntUtil.readInt(style.get("height"), height);
    Integer iBottom = IntUtil.readInt(style.get("bottom"), height);

    StartLength hor = StartLength.from(iLeft, iWidth, iRight, x, width);
    StartLength ver = StartLength.from(iTop, iHeight, iBottom, y, height);

    Rectangle ret = new Rectangle();
    ret.x      = hor.start;
    ret.width  = hor.length;
    ret.y      = ver.start;
    ret.height = ver.length;

    return ret;
  }

}
