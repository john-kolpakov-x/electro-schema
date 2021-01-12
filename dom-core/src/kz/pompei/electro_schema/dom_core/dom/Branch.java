package kz.pompei.electro_schema.dom_core.dom;

import java.util.Map;
import java.util.Set;

public interface Branch extends Node, Iterable<Node> {

  Set<String> groups();

  Map<String, String> group(String groupName);

  default Map<String, String> style() {
    return group("style");
  }

  default Map<String, String> def() {
    return group("def");
  }


  int childrenCount();

  Node childAt(int index);

}
