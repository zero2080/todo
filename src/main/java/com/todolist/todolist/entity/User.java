package com.todolist.todolist.entity;

import com.todolist.todolist.dto.UserSignupRequest;
import com.todolist.todolist.entity.type.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of="id")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nickname;
  private String loginId;
  private String password;

  @Convert(converter = UserStatus.Converter.class)
  private UserStatus status = UserStatus.ACTIVE;

  @Column(updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  public static User from(UserSignupRequest request) {
    User user = new User();
    user.loginId=request.getLoginId();
    user.password = request.getPassword();
    user.nickname = request.getNickname();
    return user;
  }

  public static User from(long userId){
    User user = new User();
    user.id = userId;
    return user;
  }

  public void update(UserSignupRequest request) {
    if (request.getNickname()!=null){
      this.nickname = request.getNickname();
    }
    if (request.getPassword()!=null){
      this.password = request.getPassword();
    }
  }
}
