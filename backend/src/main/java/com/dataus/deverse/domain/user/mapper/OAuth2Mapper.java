package com.dataus.deverse.domain.user.mapper;

import java.util.Optional;

import com.dataus.deverse.domain.user.vo.UserVO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OAuth2Mapper {

    public Optional<UserVO> findById(@Param("userId") String userId);

    public void addUser(@Param("user") UserVO user);

    public void modifyUser(@Param("user") UserVO user);
    
}   
