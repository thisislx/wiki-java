package com.wiki.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;

@Data
public class Tree {
  private Integer id;
  private Integer parent;
  @TableField(exist = false)
  private List<Tree> children;

  public Tree() {
  }

  public Tree(Integer id, Integer parent) {
    this.id = id;
    this.parent = parent;
  }

  /**
   * 减熵： treeList -> 结构化treeList
   * 维护3个map, {parentId: node}, {id: node}, {parnetId == null: node}(List形式输出)
   */
  public static <D extends Tree> List<D> treeListStructured(List<D> list) {
    HashMap<Integer, D> needParentMap = new HashMap<Integer, D>();
    HashMap<Integer, D> parentIsNullMap = new HashMap<Integer, D>();
    HashMap<Integer, D> idMap = new HashMap<Integer, D>();

    list.forEach(v -> {
      idMap.put(v.getId(), v);
      if (v.getParent() == null || v.getParent() == 0)
        parentIsNullMap.put(v.getId(), v);
      else
        needParentMap.put(v.getParent(), v);
    });

    needParentMap.forEach((parentId, v) -> {
      Tree parent = idMap.get(parentId);
      if (parent != null)
        parent.addChildren(v);
    });

    return new ArrayList<D>(parentIsNullMap.values());
  };

  public Tree addChildren(Tree node) {
    children = children != null ? children : new ArrayList<Tree>();
    children.add(node);
    return this;
  }

}
