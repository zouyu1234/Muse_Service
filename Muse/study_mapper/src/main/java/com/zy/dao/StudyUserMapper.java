package com.zy.dao;

import com.zy.model.StudyUser;
import com.zy.model.User;
import com.zy.tools.ParamMap;

import java.util.List;

public interface StudyUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StudyUser record);

    int insertSelective(StudyUser record);

    StudyUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StudyUser record);

    int updateByPrimaryKey(StudyUser record);

    int selectByAccountAndPwd(ParamMap paramMap);

    List<StudyUser> selectList();
}