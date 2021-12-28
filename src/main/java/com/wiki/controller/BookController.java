package com.wiki.controller;

import java.util.List;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wiki.entity.Book;
import com.wiki.service.impl.BookServiceImpl;
import com.wiki.util.QueryPage;
import com.wiki.util.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "书本管理")
@RequestMapping("/api/book")
@RestController
@Validated
public class BookController {
 @Autowired
 BookServiceImpl bookService;

 @GetMapping("/v1")
 Response v1() {
  return Response.ok("success");
 }

 @GetMapping("/list")
 @ApiOperation(value = "分页查询")
 Response list(QueryPage query) {
  Page<Book> page = new Page<Book>(query.getCurrent(), query.getPageSize());
  QueryWrapper<Book> queryWrapper = new QueryWrapper<Book>();
  queryWrapper.orderByAsc("update_time");
  Page<Book> res = bookService.page(page, queryWrapper);
  return Response.ok(res);
 }

 @PostMapping("/add")
 @ApiOperation(value = "新增", httpMethod = "POST")
 Response add(@RequestBody @Valid Book book) {
  Boolean isReolve = bookService.save(book);
  return isReolve ? Response.ok("新增成功") : Response.fail("新增失败");
 }

 @PostMapping("/remove")
 @ApiOperation(value = "批量删除", httpMethod = "POST")
 Response remove(@RequestBody List<Integer> ids) {
  Boolean isRight = bookService.removeByIds(ids);
  return isRight ? Response.ok("删除成功") : Response.fail("删除失败");
 }

 @PostMapping("/update")
 @ApiOperation(value = "更新", httpMethod = "POST")
 Response update(@RequestBody Book book) {
  Boolean isRight = bookService.updateById(book);
  return isRight ? Response.ok("更新成功") : Response.fail("更新失败");
 }

}
