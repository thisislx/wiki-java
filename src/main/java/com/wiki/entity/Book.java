package com.wiki.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author xin.liu
 * @since 2021-12-27
 */
@ApiModel(value = "Book对象", description = "")
@TableName(autoResultMap = true)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("书名")
    @NotNull(message = "名字不能为空")
    private String name;

    @ApiModelProperty("分类1")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> category1;

    @ApiModelProperty("分类2")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> category2;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("文档数")
    private Long docCount;

    @ApiModelProperty("阅读数")
    private Long viewCount;

    @ApiModelProperty("点赞数")
    private Long voteCount;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getCategory1() {
        return category1;
    }

    public void setCategory1(List<Integer> category1) {
        this.category1 = category1;
    }

    public List<Integer> getCategory2() {
        return category2;
    }

    public void setCategory2(List<Integer> category2) {
        this.category2 = category2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDocCount() {
        return docCount;
    }

    public void setDocCount(Long docCount) {
        this.docCount = docCount;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name=" + name +
                ", category1=" + category1 +
                ", category2=" + category2 +
                ", description=" + description +
                ", docCount=" + docCount +
                ", viewCount=" + viewCount +
                ", voteCount=" + voteCount +
                ", updateTime=" + updateTime +
                "}";
    }
}
