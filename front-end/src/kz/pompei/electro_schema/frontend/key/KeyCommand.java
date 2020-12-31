package kz.pompei.electro_schema.frontend.key;

import kz.pompei.electro_schema.frontend.pen.GraphContext;

import java.awt.event.KeyEvent;

public interface KeyCommand {

  HotKey hotKey();

  void execute(KeyEvent e);

  void paint(GraphContext gc);

}
