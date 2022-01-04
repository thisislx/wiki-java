package com.wiki.service.impl;

import com.wiki.entity.Category;
import com.wiki.entity.Tree;
import com.wiki.mapper.CategoryMapper;
import com.wiki.service.ICategoryService;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xin.liu
 * @since 2021-12-28
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

  public List<Category> treeList() {
    QueryWrapper<Category> wrapper = new QueryWrapper<Category>();
    wrapper.orderByDesc("sort");
    List<Category> categorys = this.list(wrapper);
    List<Category> res = Tree.treeListStructured(categorys);
    return res;
  }
}
