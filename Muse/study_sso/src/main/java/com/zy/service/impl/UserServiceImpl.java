package com.zy.service.impl;

import com.zy.dao.StudyUserMapper;
import com.zy.dao.UserMapper;
import com.zy.model.StudyUser;
import com.zy.model.User;
import com.zy.service.UserService;
import com.zy.tools.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@CacheConfig(cacheNames = "study_user")
public class UserServiceImpl implements UserService {
    @Autowired
    private StudyUserMapper userMapper;

    @Override
    public void addUser(StudyUser user) throws Exception {
        if (ObjectUtils.isEmpty(user)) {
            throw new Exception("用户信息不可为空！");
        }
        userMapper.insert(user);
    }

    @Override
    @Cacheable(key="'study_user'+#p0")
    public Response getList() throws Exception{
        List<StudyUser> users = userMapper.selectList();
        return Response.newResponse().setData(users);
    }
}
