package com.design.samplemgt.repository;


import com.design.samplemgt.pojo.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    public AppUser findOneByUserName(String userName);

    public AppUser save(AppUser appUser);

}
