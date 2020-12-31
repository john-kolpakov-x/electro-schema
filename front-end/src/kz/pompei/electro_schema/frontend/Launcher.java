package kz.pompei.electro_schema.frontend;

import kz.pompei.electro_schema.frontend.file_saver.FormPositionLook;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.Paths;

public class Launcher {

  public static void main(String[] args) {
    var mainFormDir = Paths.get("build").resolve("main");

    var frame = new JFrame();
    frame.setSize(800, 400);
    frame.setLocation(100, 100);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    var formPositionLook = new FormPositionLook(mainFormDir.resolve("positions").toFile());
    formPositionLook.register(frame, "main-form");

    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("Fz1fD1k4Hb :: windowClosing");
        frame.dispose();
      }

      @Override
      public void windowClosed(WindowEvent e) {
        System.out.println("JWw6uUIwOe :: windowClosed");
      }
    });

    frame.setVisible(true);

    System.out.println("Zfi6pY5N70");

  }

}
