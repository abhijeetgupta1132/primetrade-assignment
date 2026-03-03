package com.abhijeet.primetrade.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    // 🔥 MUST EXIST
    private String status;
}