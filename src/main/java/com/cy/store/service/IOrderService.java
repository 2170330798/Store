package com.cy.store.service;

import com.cy.store.entity.Order;

/*用户模块业务层接口*/
public interface IOrderService {
       Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
