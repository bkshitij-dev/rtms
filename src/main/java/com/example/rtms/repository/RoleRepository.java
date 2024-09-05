package com.example.rtms.repository;

import com.example.rtms.enums.RoleType;
import com.example.rtms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleType name);
}
