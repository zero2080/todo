package com.todolist.todolist.repository;

import com.todolist.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TodoRepository extends JpaRepository<Todo,Long> , JpaSpecificationExecutor<Todo> {


}
