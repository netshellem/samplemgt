package hello.data.pojo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    public AppUser findOneByUserName(String userName);

}

