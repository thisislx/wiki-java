package com.wiki.service.impl;

import com.wiki.entity.Category;
import com.wiki.entity.CategoryTree;
import com.wiki.mapper.CategoryMapper;
import com.wiki.service.ICategoryService;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
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

  public List<CategoryTree> treeList() {
    QueryWrapper<Category> wrapper = new QueryWrapper<Category>();
    wrapper.orderByDesc("sort");
    List<Category> categorys = this.list(wrapper);
    ArrayList<CategoryTree> result = new ArrayList<CategoryTree>();
    HashMap<Integer, CategoryTree> parentMap = new HashMap<Integer, CategoryTree>();
    ArrayList<Category> childrens = new ArrayList<Category>();

    categorys.forEach((c) -> {
      CategoryTree tree = CategoryTree.toCategoryTree(c);
      if (c.getParent() == null) {
        result.add(tree);
        parentMap.put(c.getId(), tree);
      } else
        childrens.add(c);
    });

    childrens.forEach((val) -> {
      CategoryTree parent = parentMap.get((val.getParent()));
      if (parent == null)
        return;
      parent.setChildren(parent.getChildren() != null ? parent.getChildren() : new ArrayList<CategoryTree>());
      parent.getChildren().add(CategoryTree.toCategoryTree(val));
    });

    return result;
  }
}
