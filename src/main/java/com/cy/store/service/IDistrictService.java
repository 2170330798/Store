package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;

import java.util.List;

/*用户模块业务层接口*/
public interface IDistrictService {

       List<District> getByParent(String parent);

       String getNameByCode(String code);

       List<Address> getByUid(Integer uid);
}
