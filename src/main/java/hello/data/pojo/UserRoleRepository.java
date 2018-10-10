package hello.data.pojo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRoleRepository  extends CrudRepository<UserRole, Long> {
    public List<UserRole> findByAppUser(AppUser appUser);
}
