package com.design.samplemgt.service;

import com.design.samplemgt.pojo.AppUser;

public interface UserRoleService {
    public boolean isAdmin(AppUser appUser);
    public boolean addRole(boolean isAdmin, AppUser appUser);
}
