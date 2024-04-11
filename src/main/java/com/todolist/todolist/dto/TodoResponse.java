package com.todolist.todolist.dto;

import com.todolist.todolist.entity.Todo;
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

  public static TodoResponse from(Todo todo){
    return TodoResponse.builder()
      .id(todo.getId())
      .title(todo.getTitle())
      .success(todo.isSuccess())
      .dueDate(todo.getDueDate())
      .createdAt(todo.getCreatedAt())
      .build();
  }
}
