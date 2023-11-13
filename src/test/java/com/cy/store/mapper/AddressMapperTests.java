package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.User;
import com.cy.store.service.IAddressService;
import com.cy.store.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;

//SpringBootTest：表示标注的是一个测试类不会随着项目一块打包
@SpringBootTest
//表示这是一个单元测试类
@RunWith(SpringRunner.class)
public class AddressMapperTests {


    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(14);
        address.setName("ttyu");
        address.setPhone("19330238896");
        address.setAid(15);
        address.setIsDefault(1);
        addressMapper.insert(address);

    }

    @Test
    public void countByUid(){
         Integer count = addressMapper.countByUid(1);
         System.out.println(count);
    }

    @Test
    public void findByUid(){
           List<Address> list = addressMapper.findByUid(15);
           System.out.println(list);

    }

    @Test
    public void findByAid(){
          System.out.println(addressMapper.findByAid(10));
    }

    @Test
    public void updateNonDefault(){
          addressMapper.updateNonDefault(10);
    }

    @Test
    public void updateDefaultByAid(){
          addressMapper.updateDefaultByAid(12,"sb",new Date());
    }

    @Autowired
    private IAddressService addressService;
    @Test
    public void setDefault(){
        addressService.setDefault(8,15,"TYH");
    }

    @Test
    public void deleteByAid(){
        addressMapper.deleteByAid(8);
    }

    @Test
    public void findLastModified(){
        System.out.println(addressMapper.findLastModified(15));
    }


}
