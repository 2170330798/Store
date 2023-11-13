package com.cy.store.service;

import com.cy.store.entity.Address;
import com.cy.store.entity.Product;

import java.util.List;

/*用户模块业务层接口*/
public interface IProductService {

       List<Product> findHotList();
       Product findById(Integer id);

}
