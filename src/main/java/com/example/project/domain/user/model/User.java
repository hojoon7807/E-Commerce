package com.example.project.domain.user.model;

import com.example.project.domain.common.EncryptConverter;
import com.example.project.domain.common.model.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false, columnDefinition = "char(130)")
  @Convert(converter = EncryptConverter.class)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false, columnDefinition = "char(60)")
  @Convert(converter = EncryptConverter.class)
  private String phoneNum;

  @Column(nullable = false, columnDefinition = "varchar")
  @Enumerated(EnumType.STRING)
  private UserRole userRole;

  @Column(nullable = false, columnDefinition = "varchar")
  @Enumerated(EnumType.STRING)
  private UserStatus userStatus;

  public boolean isApproved() {
    return userStatus.equals(UserStatus.APPROVED);
  }

  public void changePassword(String newPassword) {
    this.password = newPassword;
  }

  public void updateUser(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  @Builder
  public User(Long userId, String username, String password, String email, String phoneNum,
      UserRole userRole, UserStatus userStatus) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNum = phoneNum;
    this.userRole = userRole;
    this.userStatus = userStatus;
  }
}
