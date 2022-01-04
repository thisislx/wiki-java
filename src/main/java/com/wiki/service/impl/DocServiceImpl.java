package com.wiki.service.impl;

import com.wiki.entity.Doc;
import com.wiki.entity.Tree;
import com.wiki.mapper.DocMapper;
import com.wiki.service.IDocService;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 文档 服务实现类
 * </p>
 *
 * @author xin.liu
 * @since 2021-12-30
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements IDocService {
 public List<Doc> treeList(Integer id) {
  if (id == null || id < 1)
   return new ArrayList<>();
  QueryWrapper<Doc> queryWrapper = new QueryWrapper<Doc>();
  queryWrapper.eq("book_id", id);
  List<Doc> list = super.list(queryWrapper);
  return Tree.treeListStructured(list);
 }
}
