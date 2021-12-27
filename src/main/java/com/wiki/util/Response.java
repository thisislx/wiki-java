package com.wiki.util;

public class Response {
 private Boolean success;
 private String message;
 private Object data;

 Response(String message, Boolean success) {
  this.message = message;
  this.success = success;
 }

 Response(String message, Boolean success, Object data) {
  this.message = message;
  this.success = success;
  this.data = data;
 }

 public static Response ok(String message) {
  return new Response(message, true);
 }

 public static Response ok(Object data) {
  return new Response(null, true, data);
 }

 public static Response ok(String message, Object data) {
  return new Response(message, true, data);
 }

 public static Response fail(String message) {
  return new Response(message, false);
 }

 public Boolean getSuccess() {
  return success;
 }

 public void setSuccess(Boolean success) {
  this.success = success;
 }

 public String getMessage() {
  return message;
 }

 public void setMessage(String message) {
  this.message = message;
 }

 public Object getData() {
  return data;
 }

 public void setData(Object data) {
  this.data = data;
 }

}
