package com.wiki.entity;

import org.springframework.util.DigestUtils;

import lombok.Data;

@Data
public class UserSave {
 public static final String passwordReg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";

 public static final String passwordMd5(String password) {
  String formatPassword = password == null ? "" : password;
  return DigestUtils.md5DigestAsHex(formatPassword.getBytes());
 }

 private String userName;
 private String password;

 public void setPassword(String password) {
  this.password = password;
 }

 public String getPasswordMd5() {
  return UserSave.passwordMd5(password);
 }

 
}
