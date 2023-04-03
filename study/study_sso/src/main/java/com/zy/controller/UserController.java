package com.zy.controller;

import com.zy.model.StudyUser;
import com.zy.model.User;
import com.zy.service.UserService;
import com.zy.tools.ParamMap;
import com.zy.tools.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public Response addUser(@RequestBody StudyUser user){
        Response response = Response.newResponse();
        try {
            userService.addUser(user);
        }catch (Exception e) {
            response.setCodeAndMessage(500,"添加失败:"+e.getMessage());
            return response;
        }
        return response;
    }

    @GetMapping("/getUserList")
    public Response getUserList(){
        Response response = Response.newResponse();
        try {
            response = userService.getList();
        }catch (Exception e){
            response.setCodeAndMessage(500,"查询失败:"+e.getMessage());
            return response;
        }
        return response;
    }

}
