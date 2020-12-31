package kz.pompei.electro_schema.frontend;

import kz.pompei.electro_schema.frontend.file_saver.FormPositionLook;
import kz.pompei.electro_schema.frontend.model.Scene;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class Launcher {

  public static void main(String[] args) {
    var mainFormDir = Paths.get("build").resolve("main");

    var frame = new JFrame();
    frame.setSize(800, 400);
    frame.setLocation(100, 100);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    var formPositionLook = new FormPositionLook(mainFormDir.resolve("positions").toFile());
    formPositionLook.register(frame, "main-form");

    var scene = new Scene();

    var paintPanel = new TopPaintPanel(scene);

    frame.setContentPane(paintPanel);

    var refreshing = new AtomicBoolean(true);
    var refreshThread = new Thread(() -> {
      while (refreshing.get()) {

        try {
          //noinspection BusyWait
          Thread.sleep(1000 / 24);
        } catch (InterruptedException e) {
          return;
        }

        paintPanel.repaint();

      }
    });

    refreshThread.start();

    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("Fz1fD1k4Hb :: windowClosing");
        frame.dispose();
        refreshing.set(false);
      }

      @Override
      public void windowClosed(WindowEvent e) {
        System.out.println("JWw6uUIwOe :: windowClosed");
      }
    });

    KeyboardFocusManager.getCurrentKeyboardFocusManager()
                        .addKeyEventDispatcher((KeyEvent e) -> {
                          if (e.getID() == KeyEvent.KEY_PRESSED) {
                            paintPanel.onKeyboardEvent(e);
                          }
                          return true;
                        });

    SwingUtilities.invokeLater(() -> {
      frame.setVisible(true);
      System.out.println("Zfi6pY5N70 frame visibility opened");
    });
  }

}
