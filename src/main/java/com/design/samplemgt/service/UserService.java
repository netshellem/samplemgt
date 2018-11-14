package com.design.samplemgt.service;

import com.design.samplemgt.pojo.AppUser;

import java.util.List;

public interface UserService {
    public void UpdatePassword(String password);
    public List<AppUser> findAll();
    public AppUser save(AppUser user);
    public AppUser findByUserId(long id);
    public Boolean existByUserName(String name);
}
