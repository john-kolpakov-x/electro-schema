package kz.pompei.electro_schema.dom_core.launcher;

import kz.pompei.electro_schema.common.file_saver.FormPositionLook;
import kz.pompei.electro_schema.dom_core.dom.Branch;
import kz.pompei.electro_schema.dom_core.dom.ParseResult;
import kz.pompei.electro_schema.dom_core.painter.dom.DomPainter;
import kz.pompei.electro_schema.dom_core.parser.DomParser;
import lombok.SneakyThrows;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class Launcher {
  public static void main(String[] args) {
    new Launcher().execute();
  }

  @SneakyThrows
  private void execute() {
    var mainFormDir = Paths.get("build").resolve("launcher");
    var pageFile    = mainFormDir.resolve("page.gui-ml");

    if (!Files.exists(pageFile)) {
      pageFile.toFile().getParentFile().mkdirs();
      Files.writeString(pageFile, "{}");
    }

    var frame = new JFrame();
    frame.setTitle("Прорисовка DOM-а");
    frame.setSize(800, 400);
    frame.setLocation(100, 100);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    var formPositionLook = new FormPositionLook(mainFormDir.resolve("positions").toFile());
    formPositionLook.register(frame, "main-form");

    DomPainter domPainter = new DomPainter(() -> readDomFromFile(pageFile));
    frame.setContentPane(domPainter);

    AtomicBoolean pageFileWatcherWorks = new AtomicBoolean(true);

    new Thread(() -> pageFileWatcher(pageFile, pageFileWatcherWorks, domPainter)).start();

    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        frame.dispose();
      }

      @Override
      public void windowClosed(WindowEvent e) {
        pageFileWatcherWorks.set(false);
      }
    });

    SwingUtilities.invokeLater(() -> frame.setVisible(true));

  }

  @SneakyThrows
  private static void pageFileWatcher(Path pageFile,
                                      AtomicBoolean pageFileWatcherWorks,
                                      DomPainter domPainter) {

    long modifiedMillis = Files.getLastModifiedTime(pageFile).toMillis();

    while (pageFileWatcherWorks.get()) {
      Thread.sleep(1000);
      {
        long curModifiedMillis = Files.getLastModifiedTime(pageFile).toMillis();
        if (curModifiedMillis == modifiedMillis) {
          continue;
        }
        modifiedMillis = curModifiedMillis;
      }

      SwingUtilities.invokeLater(domPainter::repaint);
    }
  }

  @SneakyThrows
  private Branch readDomFromFile(Path file) {
    try (FileInputStream fileInputStream = new FileInputStream(file.toFile())) {
      ParseResult parseResult = DomParser.parse(fileInputStream);
      return parseResult.branch();
    }
  }

}
