package com.wiki.service.impl;

import com.wiki.entity.User;
import com.wiki.entity.UserPassword;
import com.wiki.entity.UserSave;
import com.wiki.exception.BusinessException;
import com.wiki.exception.BusinessExceptionCode;
import com.wiki.mapper.UserMapper;
import com.wiki.service.IUserService;
import com.wiki.util.Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xin.liu
 * @since 2022-01-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

 private String concatPath(String path) {
  return "/user/" + path;
 }

 /** 正则是守门员 */
 private UserServiceImpl checkPassword(String path, String password) {
  if (!password.matches(UserSave.passwordReg)) {
   Utils.info(concatPath(path), "密码格式不对");
   throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR, 422);
  }

  return this;
 }

 /** 登录名唯一性 */
 private UserServiceImpl checkLoginNameUnique(String path, String loginName) {
  User user = selectByLoginName(loginName);

  if (user != null) {
   Utils.info(concatPath(path), BusinessExceptionCode.USER_LOGIN_NAME_EXIST.getDesc());
   throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST, 422);
  }

  return this;
 }

 private User passwordToMd5(User user) {
  user.setPassword(UserSave.passwordMd5(user.getPassword()));
  return user;
 }

 public User selectByLoginName(String name) {
  QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
  queryWrapper.eq("login_name", name);
  User user = super.getOne(queryWrapper);
  return user;
 }

 public User login(UserSave user) {
  checkPassword("login", user.getPassword());

  User data = selectByLoginName(user.getUserName());

  if (data == null || !UserSave.passwordMd5(data.getPassword()).equals(user.getPasswordMd5())) {
   Utils.info(concatPath("login"), "密码或者密码错误");
   throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR, 422);
  }

  return data;
 }

 @Override
 public boolean save(User user) {
  checkPassword("add", user.getPassword()).checkLoginNameUnique("add", user.getLoginName());
  return super.save(passwordToMd5(user));
 }

 @Override
 public boolean updateById(User entity) {
  User dbUser = selectByLoginName(entity.getLoginName());

  if (dbUser.getId() != entity.getId()) {
   Utils.info("POST", "user/updateById", "用户名已经存在");
   throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST, 422);
  }
  return super.updateById(entity);
 }

 public boolean updatePassword(UserPassword userPassword) {
  checkPassword("updatePassword", userPassword.getPassword());

  User user = this.getById(userPassword.getId());

  if (user == null) {
   Utils.info(concatPath("updatePassword"), "用户不存在, id = " + userPassword.getId());
   throw new BusinessException(BusinessExceptionCode.USER_IS_NULL, 422);
  }

  user.setPassword(UserSave.passwordMd5(userPassword.getPassword()));

  return this.updateById(user);
 }

}
