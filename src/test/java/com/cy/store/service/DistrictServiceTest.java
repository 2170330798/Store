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

//SpringBootTest����ʾ��ע����һ�������಻��������Ŀһ����
@SpringBootTest
//��ʾ����һ����Ԫ������
@RunWith(SpringRunner.class)
public class DistrictServiceTest {

    //idea�м��Ĺ��ܣ��ӿ��ǲ���ֱ�Ӵ���Bean��
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
