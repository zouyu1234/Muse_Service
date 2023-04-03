package com.zy.service;

import com.zy.model.StudyUser;
import com.zy.model.User;
import com.zy.tools.ParamMap;
import com.zy.tools.Response;

public interface UserService {
    void addUser(StudyUser user) throws Exception;

    Response getList() throws Exception;
}
