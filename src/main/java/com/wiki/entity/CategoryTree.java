package com.wiki.entity;

import java.util.List;

import lombok.Data;

@Data
public class CategoryTree {
 private String name;
 private Integer id;
 private Integer sort;
 private String updatTime;
 private List<CategoryTree> children;

 public static CategoryTree toCategoryTree(Category category) {
  CategoryTree tree = new CategoryTree();
  tree.setId(category.getId());
  tree.setSort(category.getSort());
  tree.setName(category.getName());
  tree.setUpdatTime(category.getUpdateTime());
  return tree;
 }
}
