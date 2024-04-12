package com.todolist.todolist.entity.specification;

import com.todolist.todolist.entity.Todo;

import com.todolist.todolist.entity.type.UserStatus;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public class TodoSpecification {

  public static Specification<Todo> dueDateLessThanEqual(LocalDate dueDate) {

    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), dueDate);
  }

  public static Specification<Todo> dueDateGreaterThanEqual(LocalDate dueDate) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"), dueDate);
  }

  public static Specification<Todo> hasUser(Long userId){
    return (root, query, criteriaBuilder) -> {
      Predicate[] where = new Predicate[2];
      where[0] = criteriaBuilder.equal(root.get("user").get("id"), userId);
      where[1] = criteriaBuilder.equal(root.get("user").get("status"), UserStatus.ACTIVE);
      return criteriaBuilder.and(where);
    };
  }

  public static Specification<Todo> isSuccess(Boolean success){
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("success"),success);
  }
}
