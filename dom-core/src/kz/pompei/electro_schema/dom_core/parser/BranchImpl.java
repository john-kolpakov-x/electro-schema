package kz.pompei.electro_schema.dom_core.parser;

import kz.pompei.electro_schema.dom_core.dom.Branch;
import kz.pompei.electro_schema.dom_core.dom.DomError;
import kz.pompei.electro_schema.dom_core.dom.Node;
import kz.pompei.electro_schema.dom_core.parser.token.Token;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BranchImpl implements Branch {
  private final List<DomError> domErrors;

  public BranchImpl(List<DomError> domErrors) {
    this.domErrors = domErrors;
  }

  private final Grouper grouper=new Grouper();

  @Override
  public Set<String> groups() {
    return grouper.groupNames();
  }

  @Override
  public Map<String, String> group(String groupName) {
    return grouper.byName(groupName);
  }

  @Override
  public int childrenCount() {
    return children.size();
  }

  @Override
  public Node childAt(int index) {
    return children.get(index);
  }

  private final List<Node> children = new ArrayList<>();

  @Override
  public Iterator<Node> iterator() {
    return children.iterator();
  }

  public void parseToken(Token token) {
    throw new RuntimeException("2021-01-15 22:22 Not impl yet: BranchImpl.parseToken");
  }
}
