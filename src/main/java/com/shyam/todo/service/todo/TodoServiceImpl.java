package com.shyam.todo.service.todo;

import com.shyam.todo.model.Todo;
import com.shyam.todo.repo.TodoRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepo todoRepo;

    @Override
    public List<Todo> findAll() {
        return todoRepo.findAll();
    }

    @Override
    public Todo findAll(Todo todo) {
        return todoRepo.save(todo);
    }

    @Override
    public void save(Todo todo) {
        todoRepo.save(todo);
    }

    @Override
    public Optional<Todo> findById(long id) {
        return todoRepo.findById(id);
    }

    @Override
    public void deleteAll() {
        todoRepo.deleteAll();
    }

    @Override
    public void deleteById(long id) {
        todoRepo.deleteById(id);
    }
}
