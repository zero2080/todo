package com.todolist.todolist.entity;

import com.todolist.todolist.dto.TodoRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  @ManyToOne(fetch = FetchType.EAGER)
  private User user;
  private boolean success;

  private LocalDate dueDate;

  @Column(updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  public static Todo from(TodoRequest request) {
    Todo todo = new Todo();

    todo.title = request.getTitle();
    todo.user = User.from(request.getUserId());
    todo.dueDate = request.getDueDate();

    return todo;
  }
}
