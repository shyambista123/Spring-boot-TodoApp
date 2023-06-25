package com.shyam.todo.controller;

import com.shyam.todo.model.Todo;
import com.shyam.todo.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TodoController {
    @Autowired
    private TodoRepo todoRepo;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoRepo.findAll());
        return "index";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addTodo(){
        return "add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute Todo todo) {
        if (todo.getTitle() != null && !todo.getTitle().isEmpty() &&
                todo.getDescription() != null && !todo.getDescription().isEmpty()) {
            todoRepo.save(todo);
        }
        return "redirect:/";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        todoRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/delete-all/")
    public String deleteAll() {
        todoRepo.deleteAll();
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Todo todo = todoRepo.findById(id).orElse(null);
        model.addAttribute("todo", todo);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute Todo updatedTodo) {
        Todo todo = todoRepo.findById(id).orElse(null);
        if (todo != null) {
            todo.setTitle(updatedTodo.getTitle());
            todo.setDescription(updatedTodo.getDescription());
            todoRepo.save(todo);
        }
        return "redirect:/";
    }
}
