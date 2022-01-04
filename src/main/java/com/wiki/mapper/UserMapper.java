package com.wiki.mapper;

import com.wiki.entity.User;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xin.liu
 * @since 2022-01-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
