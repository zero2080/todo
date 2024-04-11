package com.todolist.todolist.entity.specification;

import com.todolist.todolist.entity.Todo;
import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

public class TodoSpecification {

  public static Specification<Todo> dueDateLessThanEqual(LocalDate dueDate) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), dueDate);
  }

  public static Specification<Todo> dueDateGreaterThanEqual(LocalDate dueDate) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"), dueDate);
  }

  public static Specification<Todo> hasUser(Long userId){
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
  }
}
