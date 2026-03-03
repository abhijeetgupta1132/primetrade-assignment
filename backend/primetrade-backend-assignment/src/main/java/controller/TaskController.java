package com.abhijeet.primetrade.controller;

import com.abhijeet.primetrade.dto.TaskRequest;
import com.abhijeet.primetrade.dto.TaskResponse;
import com.abhijeet.primetrade.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request) {
        return ResponseEntity.ok(taskService.createTask(request));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // ✅ GET ONE
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request
    ) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    // ✅ DELETE
    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        // 🔥 ADD THIS LINE HERE
        System.out.println("🔥 DELETE HIT id=" + id);

        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
}