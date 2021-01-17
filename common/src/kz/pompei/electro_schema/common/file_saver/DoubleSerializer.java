package kz.pompei.electro_schema.common.file_saver;

public class DoubleSerializer implements ObjectSerializer<Double> {
  @Override
  public String toStr(Double aDouble) {
    return "" + aDouble;
  }

  @Override
  public Double fromStr(String str) {
    return Double.parseDouble(str);
  }
}
