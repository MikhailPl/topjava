package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.util.List;

public interface Crud <T> {

  public T create(Object...values);
  public List<T> readAll();
  public T readById(Long id);
  public Object update(Long id, Object...values);
  public void delete(Long id);

}
