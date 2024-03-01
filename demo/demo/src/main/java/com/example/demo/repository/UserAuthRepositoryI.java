package com.example.demo.repository;

import com.example.demo.user.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserAuthRepositoryI extends JpaRepository<UserAuth, UUID> {
    UserAuth findByUsername(String name);

}
