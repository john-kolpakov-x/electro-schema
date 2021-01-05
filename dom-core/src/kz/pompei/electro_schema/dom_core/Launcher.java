package kz.pompei.electro_schema.dom_core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Launcher {
  private static final String outDir = "/home/pompei/IdeaProjects/electro-schema/dom-core/src/"
    + "kz/pompei/electro_schema/dom_core/wow";

  private static String loadTemplateContent() {
    try {
      return new String(Launcher.class.getResourceAsStream("Class.txt").readAllBytes(), UTF_8);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static final String TEMPLATE_CONTENT = loadTemplateContent();

  public static void main(String[] args) {
    new Launcher().exec();
  }

  private static final Random rnd = new Random();

  private static String rndInt() {
    int    intValue1 = Math.abs(rnd.nextInt());
    int    intValue2 = Math.abs(rnd.nextInt());
    String I         = intValue1 + "" + intValue2;
    while (I.length() < 20) {
      //noinspection StringConcatenationInLoop
      I = "0" + I;
    }
    return I;
  }


  public static class ClassData {
    public final String name;
    public final String subPackage;
    public       String content;

    public ClassData callPrint = null;

    public ClassData(String name, String subPackage) {
      this.name       = name;
      this.subPackage = subPackage;
    }

    public String fullClassName() {
      return "kz.pompei.electro_schema.dom_core.wow" + subPackage.replace('/', '.') + '.' + name;
    }

    @Override
    public String toString() {
      return "ClassData{" +
        "name='" + name + '\'' +
        ", subPackage='" + subPackage + '\'' +
        '}';
    }

    void loadContent() {

      String printClass = "";
      if (callPrint != null) {
        printClass = "new " + callPrint.fullClassName() + "().print();";
      }

      content = TEMPLATE_CONTENT.replaceAll("__SUB_PACK__", subPackage.substring(1).replace('/', '.'))
                                .replaceAll("__CLASS__", name)
                                .replaceAll("__RND001__", rndInt())
                                .replaceAll("__PRINT_CLASS__", printClass)
      ;


    }

    private void save() {
      String dir = outDir + subPackage;
      new File(dir).mkdirs();
      String fileName = dir + "/" + name + ".java";
      try (var outStream = new FileOutputStream(fileName)) {
        outStream.write(content.getBytes(UTF_8));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void exec() {

    List<ClassData> list = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      String I = rndInt();

      String name = "Class" + I;
      String subPackage = ""
        + "/a" + I.substring(0, 2)
        + "/b" + I.substring(2, 4)
        + "/c" + I.substring(4, 6)
        + "/d" + I.substring(6, 20);

      list.add(new ClassData(name, subPackage));
    }

    for (int i = 0; i < list.size(); i++) {
      ClassData classData = list.get(i);
      int       j         = i + 1;
      if (j < list.size()) {
        classData.callPrint = list.get(j);
      }
    }

    list.forEach(ClassData::loadContent);
    list.forEach(ClassData::save);

    System.out.println("First class : " + list.get(0).fullClassName());
  }
}
