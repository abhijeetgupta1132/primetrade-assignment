package com.abhijeet.primetrade.service;

import com.abhijeet.primetrade.dto.TaskRequest;
import com.abhijeet.primetrade.dto.TaskResponse;
import com.abhijeet.primetrade.entity.Task;
import com.abhijeet.primetrade.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import com.abhijeet.primetrade.entity.User;
import com.abhijeet.primetrade.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // ✅ CREATE
    public TaskResponse createTask(TaskRequest request) {

        // ✅ get logged-in user email
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        // ✅ fetch user from DB
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ build task
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .completed(false)
                .user(user)
                .build();

        Task saved = taskRepository.save(task);

        return mapToResponse(saved);
    }

    // ✅ GET ALL
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ GET BY ID
    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return mapToResponse(task);
    }

    // ✅ UPDATE
    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());

        return mapToResponse(taskRepository.save(task));
    }

    // ✅ DELETE
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}