package com.wiki.util;

import javax.validation.constraints.Max;

public class QueryPage {
 private Integer current = 1;
 @Max(value = 1000, message = "每页最大长度为1000")
 private Integer pageSize = 10;

 public Integer getCurrent() {
  return current;
 }

 public void setCurrent(Integer current) {
  this.current = current;
 }

 public Integer getPageSize() {
  return pageSize;
 }

 public void setPageSize(Integer pageSize) {
  this.pageSize = pageSize;
 }

 @Override
 public String toString() {
  return "QueryPage [current=" + current + ", pageSize=" + pageSize + "]";
 }
}
