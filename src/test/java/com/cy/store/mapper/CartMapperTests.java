package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(11);
        cart.setPid(10000002);
        cart.setNum(3);
        cart.setPrice(4L);//������
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(2, 5, "����", new Date());
    }

    @Test
    public void findByUidAndPid() {
        Cart cart = cartMapper.findByUidAndPid(11, 10000001);
        System.out.println(cart);
    }

    @Test
    public void findVOByUid() {
        List<CartVO> list = cartMapper.findVOByUid(11);
        System.out.println(list);
    }

    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(1));
    }

    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 6, 8, 100};//����д���в����ڵ�,�޷Ǿ��ǲ鲻������,�����ᱨ��
        List<CartVO> list = cartMapper.findVOByCids(cids);
        for (CartVO item : list) {
            System.out.println(item);
        }
    }

}
