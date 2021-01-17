package kz.pompei.electro_schema.common.file_saver;

public interface ObjectSerializer<T> {

  String toStr(T t);

  T fromStr(String str);

}
