package com.easybuy.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.kob.backend.mapper.UserMapper;
//import com.kob.backend.pojo.User;
//import com.kob.backend.service.impl.utils.UserDetailsImpl;

import com.easybuy.backend.mapper.EasybuyUserMapper;
import com.easybuy.backend.pojo.EasybuyUser;
import com.easybuy.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EasybuyUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<EasybuyUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("loginname",username);
        EasybuyUser user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}
