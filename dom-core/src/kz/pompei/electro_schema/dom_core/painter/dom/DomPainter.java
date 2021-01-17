package kz.pompei.electro_schema.dom_core.painter.dom;

import kz.pompei.electro_schema.dom_core.dom.Branch;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.function.Supplier;

public class DomPainter extends JPanel {

  private final Supplier<Branch> branch;

  public DomPainter(Supplier<Branch> branch) {
    this.branch = branch;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    paintBranch((Graphics2D) g);
  }

  private void paintBranch(Graphics2D g) {
    new FixRect(branch.get(), 0, 0, getWidth(), getHeight()).paint(g);
    System.out.println("aW6H58351y :: w = " + getWidth() + ", h = " + getHeight());
  }

}
