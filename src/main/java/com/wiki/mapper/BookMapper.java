package com.wiki.mapper;

import com.wiki.entity.Book;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xin.liu
 * @since 2021-12-27
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
