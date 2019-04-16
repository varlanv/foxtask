package com.company.foxtask.model.repository;

import com.company.foxtask.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface UserRepository extends JpaRepository<User, CriteriaBuilder.In> {
}
