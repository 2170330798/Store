package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(1);
        order.setRecvName("С��");
        order.setRecvPhone("133334");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000001);
        orderItem.setTitle("�ߵ�Ǧ��");
        orderMapper.insertOrderItem(orderItem);
    }
}
