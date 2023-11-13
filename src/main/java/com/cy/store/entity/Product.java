package com.cy.store.entity;

import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.io.Serializable;
import java.util.Objects;

public class Product extends BaseEntity implements Serializable{
    private Integer id;// int(20) NOT NULL COMMENT '商品id',
    private Integer categoryId;// DEFAULT NULL COMMENT '分类id',
    private String itemType; //varchar(100) DEFAULT NULL COMMENT '商品系列',
    private String title; //varchar(100) DEFAULT NULL COMMENT '商品标题',
    private String sellPoint; //varchar(150) DEFAULT NULL COMMENT '商品卖点',
    private Long price;// bigint(20) DEFAULT NULL COMMENT '商品单价',
    private Integer num; //int(10) DEFAULT NULL COMMENT '库存数量',
    private String image; //varchar(500) DEFAULT NULL COMMENT '图片路径',
    private Integer status;// int(1) DEFAULT '1' COMMENT '商品状态  1：上架   2：下架   3：删除',
    private Integer priority;// int(10) DEFAULT NULL COMMENT '显示优先级',


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(getId(), product.getId()) && Objects.equals(getCategoryId(), product.getCategoryId()) && Objects.equals(getItemType(), product.getItemType()) && Objects.equals(getTitle(), product.getTitle()) && Objects.equals(getSellPoint(), product.getSellPoint()) && Objects.equals(getPrice(), product.getPrice()) && Objects.equals(getNum(), product.getNum()) && Objects.equals(getImage(), product.getImage()) && Objects.equals(getStatus(), product.getStatus()) && Objects.equals(getPriority(), product.getPriority());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getCategoryId(), getItemType(), getTitle(), getSellPoint(), getPrice(), getNum(), getImage(), getStatus(), getPriority());
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", itemType='" + itemType + '\'' +
                ", title='" + title + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }

}