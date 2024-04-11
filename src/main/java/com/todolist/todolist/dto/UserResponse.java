package com.todolist.todolist.dto;

import com.todolist.todolist.entity.User;
import com.todolist.todolist.entity.type.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String loginId;
    private UserStatus status;
    private String nickname;

  public static UserResponse from(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .loginId(user.getLoginId())
        .nickname(user.getNickname())
        .status(user.getStatus())
        .build();
  }
}
