package com.example.demo.controller;

import com.example.demo.dto.TodoDto;
import com.example.demo.model.TodoItem;
import com.example.demo.request.CreateTodoRequest;
import com.example.demo.request.UpdateTodoRequest;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/stuff")

    public List<TodoDto> items() {
        return todoService.items().stream().map(TodoDto::fromTodoItem).collect(Collectors.toList());
    }

    @PostMapping("/create")
    public TodoDto createTodo(@RequestBody CreateTodoRequest createTodoRequest) {
        // 1. Do some validation

        // 2. Pass to the service
        TodoItem created = todoService.createTodoItem(createTodoRequest);

        // 3. Return the DTO
        return TodoDto.fromTodoItem(created);
    }

    @PutMapping("/{id}")
    public TodoDto updateTodo(@RequestBody UpdateTodoRequest updateTodoRequest) {
        return null;
    }

    @GetMapping("/{id}")
    public TodoDto getTodo(@RequestParam("id") String id) {
        return TodoDto.fromTodoItem(todoService.getTodo(id));
    }
}
