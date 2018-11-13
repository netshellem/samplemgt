package com.design.samplemgt.service.impl;

import com.design.samplemgt.pojo.AppUser;
import com.design.samplemgt.pojo.UserRole;
import com.design.samplemgt.repository.AppRoleRepository;
import com.design.samplemgt.repository.UserRoleRepository;
import com.design.samplemgt.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("UserRoleServiceImpl")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    AppRoleRepository appRoleRepository;
    @Override
    public boolean isAdmin(AppUser appUser) {
        List<UserRole> roles = userRoleRepository.findByAppUser(appUser);
        if (roles != null) {
            for (UserRole role : roles) {
                // ROLE_USER, ROLE_ADMIN,..
                if(role.getAppRole().getRoleName().equals("ROLE_ADMIN"))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean addRole(boolean isAdmin, AppUser appUser){
        UserRole userRole;
        if(isAdmin){
            userRole = new UserRole();
            userRole.setAppUser(appUser);
            userRole.setAppRole(appRoleRepository.findByRoleNameContaining("ADMIN"));
            if(null == userRoleRepository.save(userRole)) return false;
        }
        userRole = new UserRole();
        userRole.setAppUser(appUser);
        userRole.setAppRole(appRoleRepository.findByRoleNameContaining("USER"));
        if(null == userRoleRepository.save(userRole)) return false;
        return true;
    }
}
