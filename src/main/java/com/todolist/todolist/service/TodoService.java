package com.todolist.todolist.service;

import com.todolist.todolist.dto.TodoRequest;
import com.todolist.todolist.dto.TodoResponse;
import com.todolist.todolist.entity.Todo;
import com.todolist.todolist.repository.TodoRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
  private final TodoRepository repository;

  public void postTodo(TodoRequest request){
    if( request.getTitle() !=null ){
      Todo todo = Todo.from(request);
      repository.save(todo);
    }else{
      throw new RuntimeException("투두 등록 실패");
    }
  }

  public void updateTodo(long todoId, boolean success) {
    repository.findById(todoId).ifPresent(todo->{
      todo.setSuccess(success);
      repository.save(todo);
    });
  }

  public Page<TodoResponse> getMyTodoList(long userId, Pageable pageable) {
    Page<Todo> pages = repository.findAllByUserId(pageable,userId);
    return pages.map(p->TodoResponse.builder().id(p.getId()).title(p.getTitle()).createdAt(p.getCreatedAt()).success(p.isSuccess()).build());
  }

  public void updateTodoDueDate(long todoId, LocalDate dueDate) {
    repository.findById(todoId).ifPresent(todo->{
      todo.setDueDate(dueDate);
      repository.save(todo);
    });
  }
}
