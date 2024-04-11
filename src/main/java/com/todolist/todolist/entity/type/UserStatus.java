package com.todolist.todolist.entity.type;

import jakarta.persistence.AttributeConverter;
import lombok.Getter;

@Getter
public enum UserStatus {
  ACTIVE(1),
  INACTIVE(2),
  WITHDRAW(3);

  private final int code;

  UserStatus(int code) {
    this.code = code;
  }

  @jakarta.persistence.Converter
  public static class Converter implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus attribute) {
      return attribute.getCode();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer dbData) {
      return UserStatus.values()[dbData-1];
    }
  }
}
