package com.todolist.todolist.repository;

import com.todolist.todolist.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

  Page<Todo> findAllByUserId(Pageable pageable, long userId);

}
