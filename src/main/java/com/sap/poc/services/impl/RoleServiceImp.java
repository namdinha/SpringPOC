package com.sap.poc.services.impl;

import com.sap.poc.daos.RoleDao;
import com.sap.poc.models.Role;
import com.sap.poc.services.RoleService;

import javax.annotation.Resource;
import java.util.List;

public class RoleServiceImp implements RoleService {

    @Resource
    private RoleDao hibernateRoleDao;

    public RoleServiceImp(RoleDao hibernateRoleDao) {
        this.hibernateRoleDao = hibernateRoleDao;
    }

    @Override
    public Role getRole(String roleName) {
        Role role;
        role = hibernateRoleDao.getRoleByName(roleName);
        if(role == null){
            hibernateRoleDao.create(new Role(roleName));
            role = hibernateRoleDao.getRoleByName(roleName);
        }
        return role;
    }

    @Override
    public void createRolesIfNotCreated(List<String> roleNames){
        if(hibernateRoleDao.getRoles().size() == 0) {
            for (String roleName : roleNames){
                hibernateRoleDao.create(new Role(roleName));
            }
        }
    }
}
