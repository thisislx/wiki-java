package com.wiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wiki.service.impl.CategoryServiceImpl;
import com.wiki.util.Response;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xin.liu
 * @since 2021-12-28
 */

@RequestMapping("/api/category")
@RestController
public class CategoryController {
 @Autowired
 CategoryServiceImpl categoryService;

 @GetMapping("/all")
 Response getAll() {
  return Response.ok(categoryService.treeList());
 }
}
