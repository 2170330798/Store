package com.cy.store.controller;


import com.cy.store.controller.ex.*;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;

import com.cy.store.util.JsonResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController //@Controller+@ResponseBody
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username,password);
        //全局变量session
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        //输出情况
        return new JsonResult<>(OK,data);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User data = userService.getByUid(getUidFromSession(session));
        if(data != null) {
            System.out.println(data);
        }
        return  new JsonResult<User>(OK,data);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(User user,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return  new JsonResult<>(OK);
    }

    public static final int AVATAR_MAX_SIZE = 10*1024*1024;
    //文件上传类型
    public static  final List<String> AVATAR_TYPE = new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
        AVATAR_TYPE.add("image/jpeg");
    }

    @RequestMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session,
                                           @RequestParam("file") MultipartFile file) throws FileStateException {
        if(file.isEmpty()){
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出上传限制");
        }
        String contentType = file.getContentType();
        if(!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("非规类型的定文件");
        }
        //上传文件的位置/upload/文件.png
        String parent = session.getServletContext().getRealPath("/upload");

        File dir = new File(parent);

        if(!dir.exists()){//检测文件是否存在
            boolean create = dir.mkdirs();//创建当前目录
        }

        String originalFilename = file.getOriginalFilename();
        System.out.println("FileName:"+originalFilename);
        assert originalFilename != null;
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String filename = UUID.randomUUID().toString().toUpperCase()+suffix;
        File dest = new File(dir,filename);
        try{
            file.transferTo(dest);
        } catch(FileStateException e){
            throw new FileStateException("文状态异常");
        } catch(IOException e){
            throw new FileUploadIOException("文件读写异常");
        }

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        String avatar = "/upload/"+filename;
        userService.changeAvatar(uid,avatar,username);
        return new JsonResult<>(OK,avatar);
    }

}
