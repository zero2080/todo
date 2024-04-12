package com.todolist.todolist.service;

import com.todolist.todolist.dto.TodoRequest;
import com.todolist.todolist.dto.TodoResponse;
import com.todolist.todolist.entity.Todo;
import com.todolist.todolist.entity.User;
import com.todolist.todolist.entity.specification.TodoSpecification;
import com.todolist.todolist.repository.TodoRepository;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
  private final TodoRepository repository;

  public void postTodo(long userId, TodoRequest request) {
    if (request.getTitle() != null) {
      Todo todo = Todo.from(request);
      todo.setUser(User.from(userId));
      repository.save(todo);
    } else {
      throw new RuntimeException("투두 등록 실패");
    }
  }

  public void updateTodo(long todoId, boolean success) {
    repository.findById(todoId).ifPresent(todo -> {
      todo.setSuccess(success);
      repository.save(todo);
    });
  }

  public Page<TodoResponse> getMyTodoList(long userId, LocalDate dueDate, Pageable pageable) {
    if (dueDate == null)
      dueDate = LocalDate.now();

    // Specification 을 이용해서 동적으로 쿼리 조건 적용
    /*
    장점 : 리포지토리 인터페이스가 지저분해지지 않음
    단점 : 가독성이 좀 떨어짐
     */
    Page<TodoResponse> result = repository.findAll(
            Specification
                .where(TodoSpecification.dueDateLessThanEqual(dueDate))
                .and(TodoSpecification.hasUser(userId)),
            pageable)
        .map(TodoResponse::from);

    // JpaRepository 기본기능인 메서드 명을 이용해서 쿼리 조건 생성
    /*
    장점 : 코드가 간결하고 가독성이 높음
    단좀 : 복잡한 조인이 어렵고, 조건이 많으면 가독성이 떨어짐
     */
    Page<Todo> r = repository.findAllByUserIdAndDueDateLessThanEqual(pageable,userId,dueDate);

    /*
    Pagination

    a. 전체 갯수 = totalCount ( ex 1230 )
    b. 1페이지 당 보여줄 데이터의 갯수 = pageSize ( ex 10 )
    c. 페이지 번호 = pageNum ( ex 1 ~ n )

    d. 전체 페이지 갯수 : a/b = ceil( a / b )
    e. offset을 계산 :

      if ( pageNum == 0 ){
        offset = 0;
      }else{
        offset = ( pageNum-1 ) * pageSize;
      }

    1페이지 목록 : SELECT * FROM todo WHERE user_id = 1 LIMIT 0, 10;
    2페이지 목록 : SELECT * FROM todo WHERE user_id = 1 LIMIT 10, 10;
    3페이지 목록 : SELECT * FROM todo WHERE user_id = 1 LIMIT 20, 10;
    4페이지 목록 : SELECT * FROM todo WHERE user_id = 1 LIMIT 30, 10;

     */


    // Native Query
    /*
    장점 : 직접 쿼리를 작성할 수 있음, 복잡한 JOIN도 쉽게 작성가능
    단점 : 그외 모든것
     */
    long totalCount = repository.countAllByUserIdAndDueDateLessThanEqual(userId,dueDate);
    int offset = pageable.getPageNumber();
    int pageSize = pageable.getPageSize();

    List<Todo> l = repository.callNative(userId,dueDate,offset,pageSize);

    Page<Todo> aa = new PageImpl<>(l,PageRequest.of(offset,pageSize),totalCount);


    return result;
  }

  public void updateTodoDueDate(long todoId, LocalDate dueDate) {
    repository.findById(todoId).ifPresent(todo -> {
      todo.setDueDate(dueDate);
      repository.save(todo);
    });
  }
}
