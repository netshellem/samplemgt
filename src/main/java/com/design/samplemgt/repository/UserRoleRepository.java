package com.design.samplemgt.repository;

import com.design.samplemgt.pojo.AppUser;
import com.design.samplemgt.pojo.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository  extends CrudRepository<UserRole, Long> {
    public List<UserRole> findByAppUser(AppUser appUser);
}
