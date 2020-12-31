package kz.pompei.electro_schema.frontend.file_saver;

public interface ObjectSerializer<T> {

  String toStr(T t);

  T fromStr(String str);

}
