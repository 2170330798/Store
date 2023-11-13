package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController{

                /*操作成功状态码*/
       public static final int OK = 200;
       //请求处理方法，把返回值传递给全端的数据
       @ExceptionHandler({ServiceException.class,FileUploadException.class})//用于处理统一的异常
       public JsonResult<Void> handlerException(Throwable e){

           JsonResult<Void> result = new JsonResult<>(e);

           if(e instanceof UsernameDuplicatedException) {
               result.setState(4000);
               result.setMessage("用户名被占用");
           } else if (e instanceof InsertException) {
               result.setState(4001);
               result.setMessage("注册时产生未知异常");
           }else if (e instanceof UserNotFoundException) {
               result.setState(4002);
               result.setMessage("用户不存在");
           }else if (e instanceof PasswordNotMatchException){
               result.setState(4003);
               result.setMessage("密码错误");
           }else if(e instanceof AddressNotFoundException) {
               result.setState(4004);
               result.setMessage("用户收货地址不存在");
           }else if(e instanceof AccessDeniedException) {
               result.setState(4006);
               result.setMessage("收货地址非法数据访问");
           }else if (e instanceof ProductNotFoundException) {
               result.setState(4008);
               result.setMessage("访问的商品数据不存在的异常");
           } else if (e instanceof CartNotFoundException) {
               result.setState(4009);
               result.setMessage("购物车表不存在该商品的异常");
           } else if (e instanceof DeleteException) {
               result.setState(5002);
               result.setMessage("删除数据时产生未知的异常");
           }else if(e instanceof FileEmptyException){
               result.setState(6000);
           }else if(e instanceof FileSizeException){
               result.setState(6001);
           }else if(e instanceof FileTypeException){
               result.setState(6002);
           }else if(e instanceof FileStateException){
               result.setState(6003);
           }else if(e instanceof FileUploadIOException){
               result.setState(6004);
           }
           return result;
       }


       protected final Integer getUidFromSession(HttpSession session){
           if(session.getAttribute("uid") != null)
               return  Integer.valueOf(session.getAttribute("uid").toString());
           else
               return  null;
       }


       protected final String getUsernameFromSession(HttpSession session){
           if(session.getAttribute("username") != null)
              return  session.getAttribute("username").toString();
           return  null;
       }

}









