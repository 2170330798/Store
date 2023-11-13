package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/*用户业务层的实现*/
@Service  //将当前对象交给spring来管理
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        //通过user参数获取传递过来的username
        String username = user.getUsername();
        //调用findByUsername(username)判断用户是否被注册
        User result = userMapper.findByUsername(username);
        //结果集是否为null，不为null则被注册了
        if(result != null){
            //抛出异常
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //密码加密处理:md5算法
        //(串+password+串)-----md5加密,连续加载3次
        //盐值+password+盐值 ----随机字符串
        String oldPassword = user.getPassword();
        //获取随机盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全盐值的记录
        user.setSalt(salt);
        //将密码和盐值做为一个整体进行加密处理，忽略原有密码强度
        String md5Password = getMD5Password(oldPassword,salt);
        //将加密后的密码重新提交到数据库中
        user.setPassword(md5Password);

        //补全数据is_delete 设置为0
        user.setIsDelete(0);
        //补全数据:4个日志
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务功能（rows == 1）
        Integer rows = userMapper.insert(user);
        if(rows != 1){
            throw new InsertException("在用户组测过程中产生了未知的异常");
        }

    }

    @Override
    public User login(String username, String password) {

        User result = userMapper.findByUsername(username);
        if(result == null){
             throw new UserNotFoundException("用户不存在");
        }
        /*
         *1.获取旧密码
         *2.获取盐值
         *3.按照MD5进行加密对比
         */
        String oldPassword = result.getPassword();
        //salt没有获取出来
        String salt = result.getSalt();
        String newMD5Password = getMD5Password(password,salt);
        if(!newMD5Password.equals(oldPassword))
        {
            throw new PasswordNotMatchException("用户密码错误");
        }
        if(result.getIsDelete() != null) {
            if (result.getIsDelete() == 1) {
                throw new UserNotFoundException("用户不存在");
            }
        }
        User user = userMapper.findByUsername(username);
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return  user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
          User result = userMapper.findByUid(uid);

          if (result == null || result.getIsDelete() == 1){
              throw new UserNotFoundException("用户不存在");
          }

          //原始密码和新密码比较
          String oldMd5Password = getMD5Password(oldPassword, result.getSalt());
          if(!result.getPassword().equals(oldMd5Password)){
              throw new PasswordNotMatchException("密码错误");
          }
          //将加密后的密码保存到数据库
          String newMd5Password = getMD5Password(newPassword, result.getSalt());
          Integer rows = userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());
          if(rows != 1) {
              throw new UpdateException("更新时发生未知异常");
          }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return  user;
    }
    @Override
    //User对象的数据phone/email/gender,手动将uid,/username封装
    public void changeInfo(Integer uid,String username , User user){
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete() == 1){
            throw new UserNotFoundException("用户不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if(rows != 1){
            throw new UpdateException("更新时异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result = userMapper.findByUid(uid);
        if(result == null || result.getIsDelete().equals(1)){
            throw new UserNotFoundException("用户不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if(rows != 1){
            throw new UpdateException("更新时出现未知异常");
        }
    }


    /*定义一个md5加密的算法*/
    private String getMD5Password(String password,String salt){

        for(int i=0;i<3;i++) {
            //md5加密算法调用
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();

        }
        return password;
    }

}
