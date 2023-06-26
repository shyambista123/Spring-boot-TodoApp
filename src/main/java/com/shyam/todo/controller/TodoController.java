package com.shyam.todo.controller;

import com.shyam.todo.model.Todo;
import com.shyam.todo.service.todo.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "index";
    }
    @GetMapping("/add")
    public String addTodo(){
        return "add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute Todo todo) {
        if (todo.getTitle() != null && !todo.getTitle().isEmpty() &&
                todo.getDescription() != null && !todo.getDescription().isEmpty()) {
            Date currentDate = new Date();
            todo.setDate(currentDate);
            todo.setCompleted(false);
            todoService.save(todo);
        }
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        todoService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/delete-all/")
    public String deleteAll() {
        todoService.deleteAll();
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Todo todo = todoService.findById(id).orElse(null);
        model.addAttribute("todo", todo);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute Todo updatedTodo) {
        Todo todo = todoService.findById(id).orElse(null);
        if (todo != null) {
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
            todoService.save(todo);
        }
        return "redirect:/";
    }
    @GetMapping("/toggle-completion/{id}")
    public String toggleCompletionStatus(@PathVariable("id") Long id) {
        Todo todo = todoService.findById(id).orElse(null);
        if (todo != null) {
            boolean completed = todo.isCompleted();
            todo.setCompleted(!completed);
            todoService.save(todo);
        }
        return "redirect:/";
    }


}
