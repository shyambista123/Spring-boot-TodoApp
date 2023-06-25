package com.shyam.todo.service.todo;

import com.shyam.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<Todo> findAll();

    Todo findAll(Todo todo);

    void save(Todo todo);

    Optional<Todo> findById(long id);

    void deleteAll();

    void deleteById(long id);
}
