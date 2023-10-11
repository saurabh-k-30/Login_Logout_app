package com.demo.repository;

import com.demo.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails,Integer> {
    UserDetails findFirstByEmail(String email);

    public boolean existsByEmail(String email);
}
