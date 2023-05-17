package com.example.demo.controller;

import com.example.demo.dto.TodoDto;
import com.example.demo.model.TodoItem;
import com.example.demo.request.CreateTodoRequest;
import com.example.demo.request.UpdateTodoRequest;
import com.example.demo.service.TodoService;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private static final Logger log =
            LoggerFactory.getLogger(TodoController.class);
    private final TodoService todoService;
    @Value("otel.traces.api.version")
    private String tracesApiVersion;

    @Value("otel.metrics.api.version")
    private String metricsApiVersion;

    private LongCounter numberOfExecutions;

    private final Tracer tracer =
            GlobalOpenTelemetry.getTracer("io.opentelemetry.traces.hello",
                    tracesApiVersion);

    private final Meter meter =
            GlobalOpenTelemetry.meterBuilder("io.opentelemetry.metrics.hello")
                    .setInstrumentationVersion(metricsApiVersion)
                    .build();

    @Autowired

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/stuff")

    public List<TodoDto> items() {
        return todoService.items().stream().map(TodoDto::fromTodoItem).collect(Collectors.toList());
    }

    @PostMapping("/create")
    // TODO: This endpoint should require a user
    public TodoDto createTodo(@RequestBody CreateTodoRequest createTodoRequest) {
        // 1. Do some validation
        TodoItem created = todoService.createTodoItem(createTodoRequest);
        // 2. Pass to the service
        Span span = tracer.spanBuilder("mySpan").startSpan();
        try (Scope scope = span.makeCurrent()) {
            log.info("Created a new todo with id: " + created.getExternalId());
        } finally {
            span.end();
        }

        // 3. Return the DTO
        return TodoDto.fromTodoItem(created);
    }

    @PutMapping("/{id}")
    public TodoDto updateTodo(@PathVariable String id, @RequestBody UpdateTodoRequest updateTodoRequest) {
        return null;
    }

    @GetMapping("/{id}")
    public TodoDto getTodo(@PathVariable String id) {
        return TodoDto.fromTodoItem(todoService.getTodo(id));
    }
}
