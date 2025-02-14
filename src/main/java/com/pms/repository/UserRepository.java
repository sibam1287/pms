package com.pms.repository;

import com.pms.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByMobile(String mobile);

    Optional<Users> findByUserId(String userId);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    Optional<Users> findByEmailAndUserIdAndUserType(String email, String userId, String userType);
}

