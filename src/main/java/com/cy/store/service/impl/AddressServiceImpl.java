package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;

import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/*用户业务层的实现*/

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countByUid(uid);
        if(count > maxCount){
            throw new AddressCountLimitException("用户收货地址超出上限");
        }

        //对address对象中的数据进行补全
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);


        // uid/isDelete/补全日志
        address.setUid(uid);
        Integer isDefault = count == 0? 1: 0;
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        //插入收货地址
        Integer rows = addressMapper.insert(address);
        if(rows != 1){
            throw new InsertException("插入用户地址时出现异常");
        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for(Address address : list){
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setZip(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid,Integer uid,String username) {

        Address result = addressMapper.findByAid(aid);
        if(result == null) {
            throw new AddressNotFoundException("接收地址不存在");
        }

        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("用户地址非法数据访问");
        }

        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException("更新数据时产生异常");
        }

        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }

    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
           Address result = addressMapper.findByAid(aid);
           if( result == null ){
               throw  new AddressNotFoundException("收货地址不存在");
           }
           if(!result.getUid().equals(uid)){
               throw new AccessDeniedException("非法访问数据");
           }

           Integer rows = addressMapper.deleteByAid(aid);
           if(rows != 1){
               throw new DeleteException("删除数据产生未知异常");
           }

           if(result.getIsDefault() == 0){
               return;
           }

           Integer count = addressMapper.countByUid(uid);
           if(count == 0){
               return;
           }

           Address address = addressMapper.findLastModified(uid);
           rows = addressMapper.updateDefaultByAid(address.getAid(),username,new Date());
           if(rows != 1){
               throw new UpdateException("更新数据时产生未知异常");
           }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {

        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("收货地址数据不存在的异常");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }

}
