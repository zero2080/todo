package com.todolist.todolist.repository;

import com.todolist.todolist.entity.Todo;

import org.antlr.v4.runtime.atn.SemanticContext.Predicate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoRepository extends JpaRepository<Todo,Long> , JpaSpecificationExecutor<Todo> {
    Page<Todo> findAllByUserId(Predicate predicate, Pageable pageable, long userId);

}
