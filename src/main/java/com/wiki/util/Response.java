package com.wiki.util;

import lombok.Data;

@Data
public class Response {
 private Boolean success;
 private Integer code = 200;
 private String message;
 private Object data;

 Response() {
 }

 Response(String message, Boolean success) {
  this.message = message;
  this.success = success;
 }

 Response(String message, Integer code, Boolean success) {
  this.message = message;
  this.success = success;
  this.code = code;
 }

 Response(String message, Boolean success, Object data) {
  this.message = message;
  this.success = success;
  this.data = data;
 }

 Response(String message, Integer code, Boolean success, Object data) {
  this.success = success;
  this.code = code;
  this.message = message;
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

 public static Response fail(String message, Integer code) {
  return new Response(message, code, false);
 }

}
