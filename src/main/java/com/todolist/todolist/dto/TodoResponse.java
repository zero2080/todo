package com.todolist.todolist.dto;

import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoResponse {
  private Long id;
  private String title;
  private boolean success;
  private LocalDate dueDate;
  private LocalDateTime createdAt;
}
