package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import com.cy.store.entity.User;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/*用户业务层的实现*/
@Service  //将当前对象交给spring来管理
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;


    @Override
    public List<District> getByParent(String parent) {

        List<District> list = districtMapper.findByParent(parent);
        for(District d : list){
            d.setId(null);
            d.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {

        return districtMapper.findNameByCode(code);
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        return  null;
    }
}
