package com.utcv.powergym.powergym.dao;

import com.utcv.powergym.powergym.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
