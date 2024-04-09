package com.todolist.todolist.contraoller;

import com.todolist.todolist.dto.UserSignupRequest;
import com.todolist.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void signup(@RequestBody UserSignupRequest request){
    // 회원가입
    service.signup(request);
  }

}
