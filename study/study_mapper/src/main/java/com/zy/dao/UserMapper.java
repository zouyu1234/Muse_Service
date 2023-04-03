package com.zy.dao;

import com.zy.model.User;
import com.zy.tools.ParamMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insert(User user);

    int selectByAccountAndPwd(ParamMap paramMap);

    List<User> selectList();
}
