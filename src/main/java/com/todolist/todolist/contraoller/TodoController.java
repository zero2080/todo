package com.todolist.todolist.contraoller;

import com.todolist.todolist.dto.TodoRequest;
import com.todolist.todolist.dto.TodoResponse;
import com.todolist.todolist.service.TodoService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

  private final TodoService service;

  @PostMapping("/user/{userId}")
  @ResponseStatus(HttpStatus.CREATED)
  public void postTodo(@PathVariable Long userId, @RequestBody TodoRequest request){
    // 할일 등록
    service.postTodo(userId,request);
  }

  @PutMapping("/{todoId}/{success}")
  public void updateTodo(@PathVariable long todoId, @PathVariable boolean success){
    service.updateTodo(todoId,success);
  }

  @GetMapping("/user/{userId}")
  public Page<TodoResponse> getMyTodoList(
      @PathVariable
      long userId,
      @RequestParam(required = false)
      LocalDate dueDate,
      Pageable pageable){
    return service.getMyTodoList(userId,dueDate,pageable);
  }

  @PutMapping("/{todoId}")
  public void updateTodo(@PathVariable long todoId, @RequestParam LocalDate dueDate){
    service.updateTodoDueDate(todoId,dueDate);
  }
}
