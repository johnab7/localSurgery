package com.localgp.localgp.repository;

import com.localgp.localgp.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface GroupedUserRepository <typeParameter extends UserAuth> extends JpaRepository<typeParameter, Long> {
//    Optional<typeParameter>
    typeParameter findByUsername(String username);

    typeParameter findById(long id);


}
