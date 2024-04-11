package com.todolist.todolist.contraoller;

import com.todolist.todolist.dto.UserResponse;
import com.todolist.todolist.dto.UserSignupRequest;
import com.todolist.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PutMapping("/{userId}")
  public void update(@PathVariable long userId, @RequestBody UserSignupRequest request){
    // 회원정보 수정
    service.update(userId, request);
  }

  @DeleteMapping("/{userId}")
  public void withdraw(@PathVariable long userId){
    // 회원탈퇴
    service.withdraw(userId);
  }

  @GetMapping("/{userId}")
  public UserResponse getMyInfo(@PathVariable long userId){
    // 내 정보 조회
    return service.getMyInfo(userId);
  }
}
