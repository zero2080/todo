package com.todolist.todolist.repository;

import com.todolist.todolist.entity.Todo;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo,Long> , JpaSpecificationExecutor<Todo> {

  Page<Todo> findAllByUserIdAndDueDateLessThanEqual(Pageable pageable, Long userId, LocalDate dueDate);

  @Query(value="SELECT * FROM todo WHERE user_id = :userId AND due_date = :dueDate LIMIT :offset, :limit",nativeQuery = true)
  List<Todo> callNative(Long userId, LocalDate dueDate, int offset, int limit);

  long countAllByUserIdAndDueDateLessThanEqual(long userId,LocalDate dueDate);

  // QueryDSL
}
