package com.design.samplemgt.service.impl;

import com.design.samplemgt.pojo.AppUser;
import com.design.samplemgt.pojo.UserRole;
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
    @Override
    public boolean isAdmin(AppUser appUser) {
        List<UserRole> roles = userRoleRepository.findByAppUser(appUser);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (UserRole role : roles) {
                // ROLE_USER, ROLE_ADMIN,..
                if(role.getAppRole().getRoleName().equals("ROLE_ADMIN"))
                    return true;
            }
        }
        return false;
    }

}
