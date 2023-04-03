package com.zy.service.impl;

import com.zy.dao.UserMapper;
import com.zy.enums.ErrorEnum;
import com.zy.service.LoginService;
import com.zy.tools.ParamMap;
import com.zy.tools.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Response validateUserInfo(ParamMap paramMap) {
        Response response = Response.newResponse();
        if (paramMap.isEmpty("account","pwd")) {
            return response.setError(ErrorEnum.param_is_null);
        }
        int count = userMapper.selectByAccountAndPwd(paramMap);
        if (count == 0) {
            return response.setCodeAndMessage(500,"此用户不存在！");
        }
        return response.ok("登录成功！");
    }
}
