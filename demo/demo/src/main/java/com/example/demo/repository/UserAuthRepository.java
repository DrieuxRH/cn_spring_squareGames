package com.example.demo.repository;

import com.example.demo.user.UserAuth;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserAuthRepository extends CrudRepository<UserAuth, UUID> {
}
