package com.todolist.todolist.service;

import com.todolist.todolist.dto.UserSignupRequest;
import com.todolist.todolist.entity.User;
import com.todolist.todolist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository repository;

  public void signup(UserSignupRequest request){
    if (request.getLoginId()!=null
        && request.getPassword()!=null
        && request.getNickname()!=null){
      User user = User.from(request);
      repository.save(user);
    }else{
      throw new RuntimeException("회원가입 불가");
    }

  }

}
