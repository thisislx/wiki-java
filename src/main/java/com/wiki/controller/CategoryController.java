package com.wiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.wiki.config.ConstantEnum;
import com.wiki.entity.Category;
import com.wiki.service.impl.CategoryServiceImpl;
import com.wiki.util.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

 @PostMapping(value = "/add")
 public Response add(@RequestBody Category entity) {
  Boolean isResolve = categoryService.save(entity);
  return isResolve ? Response.ok(ConstantEnum.ADD_SUCCESS) : Response.fail(ConstantEnum.ADD_FAIlED.toString(), 400);
 }

 @PostMapping(value = "/update")
 public Response update(@RequestBody Category entity) {
  entity.setCreateTime(null);
  Boolean isResolve = categoryService.updateById(entity);
  return isResolve ? Response.ok(ConstantEnum.UPDATE_SUCCESS) : Response.fail(ConstantEnum.UPDATE_FAIlED.toString(), 400);
 }

 @PostMapping(value = "/remove")
 public Response update(@RequestBody List<Integer> ids) {
  Boolean isResolve = categoryService.removeByIds(ids);
  return isResolve ? Response.ok(ConstantEnum.REMOVE_SUCCESS) : Response.fail(ConstantEnum.REMOVE_FAIlED.toString(), 400);
 }

}
