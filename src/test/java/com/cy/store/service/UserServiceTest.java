package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//SpringBootTest：表示标注的是一个测试类不会随着项目一块打包
@SpringBootTest
//表示这是一个单元测试类
@RunWith(SpringRunner.class)
public class UserServiceTest{

    //idea有检测的功能，接口是不能直接创建Bean的
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
            //获取类的对象
            System.out.println(e.getClass().getSimpleName());
            //获取异常描述
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
