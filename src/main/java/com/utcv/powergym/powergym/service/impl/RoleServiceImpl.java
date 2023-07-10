package com.utcv.powergym.powergym.service.impl;

import com.utcv.powergym.powergym.dao.RoleDao;
import com.utcv.powergym.powergym.entity.Role;
import com.utcv.powergym.powergym.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(String roleName) {
        return roleDao.save(new Role(roleName));
    }
}
