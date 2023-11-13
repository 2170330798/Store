package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//SpringBootTest����ʾ��ע����һ�������಻��������Ŀһ����
@SpringBootTest
//��ʾ����һ����Ԫ������
@RunWith(SpringRunner.class)
public class UserServiceTest{

    //idea�м��Ĺ��ܣ��ӿ��ǲ���ֱ�Ӵ���Bean��
    @Autowired
    private IUserService userService;


    @Test
    public void reg(){
        try {
            User user = new User();
            user.setUsername("CSDN");
            user.setPassword("123456");

            userService.reg(user);
            System.out.println("OK");

        }catch(ServiceException e){
            //��ȡ��Ķ���
            System.out.println(e.getClass().getSimpleName());
            //��ȡ�쳣����
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo(){
        User user = new User();
        user.setPhone("1933023878");
        user.setEmail("2345678989@qq.com");
        user.setGender(0);
        userService.changeInfo(15,"LJQ",user);
    }

}
