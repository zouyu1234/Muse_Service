package com.zy.service;

import com.zy.tools.ParamMap;
import com.zy.tools.Response;

public interface LoginService {
    Response validateUserInfo(ParamMap paramMap);
}
