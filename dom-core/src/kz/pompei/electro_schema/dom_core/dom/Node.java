package kz.pompei.electro_schema.dom_core.dom;

public interface Node {
  void print(StringBuilder out, int beginSpace);

  default void print(StringBuilder out) {
    print(out, 0);
  }
}
