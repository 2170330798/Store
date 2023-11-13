package com.cy.store.util;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {

    /*状态码*/
    private Integer state;
    /*描述信息*/
    private  String message;
    /*数据*/
    private E date;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getDate() {
        return date;
    }

    public void setDate(E date) {
        this.date = date;
    }

    public JsonResult(){

    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state,E date) {
        this.state = state;
        this.date = date;
    }
}
