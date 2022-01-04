package com.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wiki.entity.User;
import com.wiki.entity.UserSave;
import com.wiki.service.impl.UserServiceImpl;
import com.wiki.util.QueryPage;
import com.wiki.util.Response;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xin.liu
 * @since 2022-01-04
 */
@RequestMapping("/api/user")
@RestController
public class UserController {

 @Autowired
 UserServiceImpl userService;

 @GetMapping("/list")
 public Response list(QueryPage query) {
  Page<User> page = new Page<User>(query.getCurrent(), query.getPageSize());
  return Response.ok(userService.page(page));
 }

 @PostMapping("/login")
 public Response login(@RequestBody UserSave userSava) {
  return Response.ok(userService.login(userSava));
 }

 @PostMapping("/add")
 public Response add(@RequestBody User user) {
  return userService.save(user) ? Response.ok("新增成功", user) : Response.fail("新增用户失败", 422);
 }

 @PostMapping("/remove")
 public Response remove(@RequestBody List<Integer> ids) {
  return userService.removeByIds(ids) ? Response.ok("删除成功") : Response.fail("删除失败", 422);
 }

 @PostMapping("/update")
 public Response update(@RequestBody User user) {
  user.setPassword(null);
  return userService.updateById(user) ? Response.ok("新增成功") : Response.fail("新增失败", 400);
 }

}
