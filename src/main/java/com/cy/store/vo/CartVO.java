package com.cy.store.vo;

import java.io.Serializable;
import java.util.Objects;

//VO: Value Object：值对象,当SELECT查询时，查询的结果是多张表中的内容，此时发现结果不能直接使用某个POJO实体类来接收，POJO实体类不能包含多张表查询出来的结果。
//解决方法: 重新构建新的对象用户存储所有查询出来的结果集对应的映射,所以把这个对象称之为值对象

public class CartVO implements Serializable {
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private Long price;
    private Integer num;
    private String title;
    private Long realPrice;
    private String image;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Long realPrice) {
        this.realPrice = realPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartVO)) return false;
        CartVO cartVO = (CartVO) o;
        return Objects.equals(getCid(), cartVO.getCid()) && Objects.equals(getUid(), cartVO.getUid()) && Objects.equals(getPid(), cartVO.getPid()) && Objects.equals(getPrice(), cartVO.getPrice()) && Objects.equals(getNum(), cartVO.getNum()) && Objects.equals(getTitle(), cartVO.getTitle()) && Objects.equals(getRealPrice(), cartVO.getRealPrice()) && Objects.equals(getImage(), cartVO.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCid(), getUid(), getPid(), getPrice(), getNum(), getTitle(), getRealPrice(), getImage());
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", price=" + price +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", realPrice=" + realPrice +
                ", image='" + image + '\'' +
                '}';
    }
}