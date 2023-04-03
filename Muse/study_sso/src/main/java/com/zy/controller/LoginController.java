package com.zy.controller;

import com.zy.service.LoginService;
import com.zy.service.UserService;
import com.zy.tools.ParamMap;
import com.zy.tools.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zy/sso/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/validateUser")
    public Response validateUser(@RequestBody ParamMap paramMap){
        Response response = Response.newResponse();
        try {
            response = loginService.validateUserInfo(paramMap);
        }catch (Exception e) {
            response.setCodeAndMessage(500,e.getMessage());
            return response;
        }
        return response;
    }
}
