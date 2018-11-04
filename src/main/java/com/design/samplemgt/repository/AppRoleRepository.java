package com.design.samplemgt.repository;

import com.design.samplemgt.pojo.AppRole;
import org.springframework.data.repository.CrudRepository;

public interface AppRoleRepository extends CrudRepository<AppRole, Long> {

    public AppRole findByRoleId(long roleId);

}
