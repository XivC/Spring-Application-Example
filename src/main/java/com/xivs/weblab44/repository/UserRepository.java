package com.xivs.weblab44.repository;

import com.xivs.weblab44.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByLogin(String login);
    User findByLogin(String login);

}
