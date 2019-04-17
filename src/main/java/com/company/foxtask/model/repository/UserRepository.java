package com.company.foxtask.model.repository;

import com.company.foxtask.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
