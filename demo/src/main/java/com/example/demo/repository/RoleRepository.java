package com.example.demo.repository;

import com.example.demo.model.entity.RoleEntity;
import com.example.demo.model.entity.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByName(RoleName name);
}
