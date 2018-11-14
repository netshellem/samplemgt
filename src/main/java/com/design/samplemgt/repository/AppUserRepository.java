package com.design.samplemgt.repository;


import com.design.samplemgt.pojo.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    public AppUser findOneByUserName(String userName);
    public List<AppUser> findAll();
    public AppUser findByUserId(long userId);
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM AppUser u WHERE u.userName = ?1")
    Boolean existsByUserName(String userName);
}
