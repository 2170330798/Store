package com.cy.store.mapper;


import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/*用户模块持久层接口*/
//Mapper有缺陷，一个项目有很多接口
public interface CartMapper {

    Integer insert(Cart cart);

    Integer updateNumByCid(@Param("cid") Integer cid,
                           @Param("num") Integer num,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime") Date modifiedTime);

    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);

    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> findVOByUid(Integer uid);
    Cart findByCid(Integer cid);
    List<CartVO> findVOByCids(Integer[] cids);


}
















