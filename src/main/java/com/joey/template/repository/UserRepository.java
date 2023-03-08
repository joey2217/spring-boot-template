package com.joey.template.repository;

import com.joey.template.entity.DO.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Joey
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO,Long> {
    /**
     * findByUsername
     * @param username
     * @return
     */
    Optional<UserDO> findByUsername(String username);
}
