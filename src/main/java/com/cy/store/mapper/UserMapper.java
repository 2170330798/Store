package com.cy.store.mapper;


import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/*用户模块持久层接口*/
//Mapper有缺陷，一个项目有很多接口
public interface UserMapper {
    /**
     * 插入用户数据
     * @Param user用户数据
     * @return 受影响的行数
     * */
    Integer insert(User user);

    /**
     * username:用户名
     * password:新密码
     * */
    Integer updateInfoByUid(User user);

    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    User findByUid(Integer uid);

    User findByUsername(String username);

    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
















