package com.wiki.util;

import java.util.List;

import lombok.Data;

public class MyInterface {
 @Data
 public static abstract class Id {
  private Integer id;
 };

 @Data
 public static abstract class Parent {
  private Integer parent;
 };

 @Data
 public static abstract class Children<T> {
  private List<T> children;

  public void setChildren(List<?> newChildren) {
   this.children = (List<T>) children;
  }
 }
}
