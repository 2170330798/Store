package com.cy.store.service;

import com.cy.store.entity.User;

/*用户模块业务层接口*/
public interface IUserService {
      //用户注册方法
       void reg(User user);

       User login(String username,String password);

       void changePassword(Integer uid,
                           String username,
                           String oldPassword,
                           String newPassword);

       User getByUid(Integer uid);

       void changeInfo(Integer uid,String username , User user);
       /**
        *
        *修改用户头像
        * @Param uid
        * @Param avatar
        * @Param username
        * */
       void changeAvatar(Integer uid,
                         String avatar,
                         String username);

}
