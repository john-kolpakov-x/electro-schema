package kz.pompei.electro_schema.frontend.file_saver;

public interface FieldAcceptor {

  String read();

  void write(String strValue);

  String name();

}
