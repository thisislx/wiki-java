package com.wiki.config;

public enum ConstantEnum {
 ADD_SUCCESS("新增成功"),
 ADD_FAIlED("新增失败"),
 UPDATE_SUCCESS("修改成功"),
 UPDATE_FAIlED("修改失败"),
 REMOVE_SUCCESS("修改成功"),
 REMOVE_FAIlED("修改失败");

 private String name;

 ConstantEnum(String name) {
  this.name = name;
 }

 @Override
 public String toString() {
  return this.name;
 }
}
