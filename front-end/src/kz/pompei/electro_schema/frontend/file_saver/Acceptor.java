package kz.pompei.electro_schema.frontend.file_saver;

import java.util.Optional;

public interface Acceptor<T> {

  default Optional<T> get() {
    return Optional.ofNullable(getValueOrNull());
  }

  T getValueOrNull();

  @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
  void set(Optional<T> optional);

  default void setValue(T t) {
    set(Optional.ofNullable(t));
  }

}
