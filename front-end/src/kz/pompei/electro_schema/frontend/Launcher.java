package kz.pompei.electro_schema.frontend;

import kz.pompei.electro_schema.frontend.file_saver.FormPositionLook;
import kz.pompei.electro_schema.frontend.key.KeyStatesImpl;
import kz.pompei.electro_schema.frontend.scene.Scene;

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
    new Launcher().execute();
  }

  KeyStatesImpl keyStates  = new KeyStatesImpl();
  Scene         scene      = new Scene();
  TopPaintPanel paintPanel = new TopPaintPanel(scene, keyStates);

  public void execute() {
    var mainFormDir = Paths.get("build").resolve("main");

    var frame = new JFrame();
    frame.setSize(800, 400);
    frame.setLocation(100, 100);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    var formPositionLook = new FormPositionLook(mainFormDir.resolve("positions").toFile());
    formPositionLook.register(frame, "main-form");

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
        frame.dispose();
      }

      @Override
      public void windowClosed(WindowEvent e) {
        refreshing.set(false);
      }
    });

    KeyboardFocusManager.getCurrentKeyboardFocusManager()
                        .addKeyEventDispatcher((KeyEvent e) -> {
                          dispatchEvent(e);
                          return true;
                        });

    SwingUtilities.invokeLater(() -> frame.setVisible(true));
  }

  private void dispatchEvent(KeyEvent e) {
    if (e.getID() == KeyEvent.KEY_PRESSED || e.getID() == KeyEvent.KEY_RELEASED) {
      if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
        keyStates.isShiftDown = e.getID() == KeyEvent.KEY_PRESSED;
        System.out.println("6K5gah6MY1 :: keyStates = " + keyStates);
        return;
      }
      if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
        keyStates.isCtrlDown = e.getID() == KeyEvent.KEY_PRESSED;
        System.out.println("XpMNDl4zw7 :: keyStates = " + keyStates);
        return;
      }
      if (e.getKeyCode() == KeyEvent.VK_ALT) {
        keyStates.isAltDown = e.getID() == KeyEvent.KEY_PRESSED;
        System.out.println("V5nv1h4xnM :: keyStates = " + keyStates);
        return;
      }
      if (e.getKeyCode() == KeyEvent.VK_CAPS_LOCK) {
        return;
      }
    }

    if (e.getID() == KeyEvent.KEY_PRESSED) {
      paintPanel.onKeyboardEvent(e);
    }
  }

}
