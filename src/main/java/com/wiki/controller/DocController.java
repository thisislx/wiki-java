package com.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.wiki.service.impl.DocServiceImpl;
import com.wiki.util.Response;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 文档 前端控制器
 * </p>
 *
 * @author xin.liu
 * @since 2021-12-30
 */
@RestController
@RequestMapping("/api/doc")
public class DocController {

 @Autowired
 DocServiceImpl categoryService;

 @GetMapping("/tree")
 Response tree(Integer id) {
  List<?> res = categoryService.treeList(id);
  return Response.ok(res);
 }

}
                                        