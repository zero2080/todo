package com.todolist.todolist.service;

import com.todolist.todolist.dto.UserResponse;
import com.todolist.todolist.dto.UserSignupRequest;
import com.todolist.todolist.entity.User;
import com.todolist.todolist.entity.type.UserStatus;
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

  public void update(long userId, UserSignupRequest request) {
    repository.findById(userId).ifPresent(user->{
      user.update(request);
      repository.save(user);
    });
  }

  public void withdraw(long userId) {

    repository.findById(userId).ifPresent(user->{
      user.setStatus(UserStatus.WITHDRAW);
      repository.save(user);
    });

  }

  public UserResponse getMyInfo(long userId) {
    return repository.findById(userId)
        .map(UserResponse::from)
        .orElseThrow(()->new RuntimeException("사용자 정보 없음"));
  }
}
