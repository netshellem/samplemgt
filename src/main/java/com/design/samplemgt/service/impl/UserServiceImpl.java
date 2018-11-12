package com.design.samplemgt.service.impl;

import com.design.samplemgt.pojo.AppUser;
import com.design.samplemgt.repository.AppUserRepository;
import com.design.samplemgt.service.UserService;
import com.design.samplemgt.utils.EncrytedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public void UpdatePassword(String password) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findOneByUserName(userName);
        if(user.isEnabled()){
            user.setEncrytedPassword(EncrytedPasswordUtils.encrytePassword(password));
            appUserRepository.save(user);
        }

    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }
}
