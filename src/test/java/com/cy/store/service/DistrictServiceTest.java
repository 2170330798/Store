package com.cy.store.service;

import com.cy.store.entity.District;
import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//SpringBootTest：表示标注的是一个测试类不会随着项目一块打包
@SpringBootTest
//表示这是一个单元测试类
@RunWith(SpringRunner.class)
public class DistrictServiceTest {

    //idea有检测的功能，接口是不能直接创建Bean的
    @Autowired
    private IDistrictService districtService;


    @Test
    public void getByParent(){
           List<District> list = districtService.getByParent("86");
           for( District d : list) {
               System.err.println(d);
           }
    }
}
