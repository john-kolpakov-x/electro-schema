package kz.pompei.electro_schema.common.file_saver;

public interface FieldAcceptor {

  String read();

  void write(String strValue);

  String name();

}
