package com.cy.store.mapper;

import com.cy.store.entity.User;
import com.cy.store.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//SpringBootTest����ʾ��ע����һ�������಻��������Ŀһ����
@SpringBootTest
//��ʾ����һ����Ԫ������
@RunWith(SpringRunner.class)
public class UserMapperTests {

    //idea�м��Ĺ��ܣ��ӿ��ǲ���ֱ�Ӵ���Bean��
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserServiceImpl userService;


    @Test
    public void insert(){
         User user = new User();
         user.setUsername("TJX");
         user.setPassword("123456");
         userService.reg(user);
    }

    @Test
    public void findByUsername(){
        User user = userMapper.findByUsername("LJQ");
        System.out.println(user);
    }



    @Test
    public void login(){

        //ע��UserMapper.xml�е�select username,password,salt,�����⿴���ǲ���©�ֶ���
        User user = userService.login("LJQ","LJQ2003.");
        System.out.println(user);

    }
    @Test
    public void updatePasswordByUid(){
         Integer rows = userMapper.updatePasswordByUid(13,"TYH","TJX2002.",new Date());
         System.out.println(rows);
    }
    @Test
    public void findByUid(){
         User user = userMapper.findByUid(13);
         System.out.println(user);
    }

    @Test
    public void changePassword(){
        userService.changePassword(15,"LJQ","123456","LJQ2003.");
    }


    @Test
    public void updateIfoByUid(){
        User user = userMapper.findByUid(16);
        user.setEmail("2170330798@qq.com");
        user.setPhone("18674720574");
        user.setGender(1);
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println(rows+"Test Successfully!");
    }


    @Test
    public void getByUid() {
       System.out.println(userService.getByUid(15));
    }


    @Test
    public  void changeAvatar(){
         userService.changeAvatar(16,"/upload/avatar.png","����Ա");
    }


}
