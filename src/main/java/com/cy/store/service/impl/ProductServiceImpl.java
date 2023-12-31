package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Product;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/*用户业务层的实现*/

    @Service
    public class ProductServiceImpl implements IProductService {
        @Autowired
        private ProductMapper productMapper;

        @Override
        public List<Product> findHotList() {
            List<Product> list = productMapper.findHotList();
            for (Product product : list) {
                product.setPriority(null);
                product.setCreatedUser(null);
                product.setCreatedTime(null);
                product.setModifiedUser(null);
                product.setModifiedTime(null);
            }
            return list;
        }

        @Override
        public Product findById(Integer id) {
            Product product = productMapper.findById(id);
            // 判断查询结果是否为null
            if (product == null) {
                throw new ProductNotFoundException("尝试访问的商品数据不存在");
            }
            // 将查询结果中的部分属性设置为null
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);

            return product;
        }
    }