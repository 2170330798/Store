package com.cy.store.mapper;


import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;

/*用户模块持久层接口*/
//Mapper有缺陷，一个项目有很多接口
public interface OrderMapper {


    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);


}
















