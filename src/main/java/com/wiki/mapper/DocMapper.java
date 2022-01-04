package com.wiki.mapper;

import com.wiki.entity.Doc;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 文档 Mapper 接口
 * </p>
 *
 * @author xin.liu
 * @since 2021-12-30
 */
@Mapper
public interface DocMapper extends BaseMapper<Doc> {

}
