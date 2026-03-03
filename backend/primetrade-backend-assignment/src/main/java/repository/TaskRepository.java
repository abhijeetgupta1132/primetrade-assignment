package com.abhijeet.primetrade.repository;

import com.abhijeet.primetrade.entity.Task;
import com.abhijeet.primetrade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);

}