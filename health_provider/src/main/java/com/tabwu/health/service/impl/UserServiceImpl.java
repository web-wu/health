package com.tabwu.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tabwu.health.entity.Permission;
import com.tabwu.health.entity.Role;
import com.tabwu.health.entity.User;
import com.tabwu.health.mapper.PermissionMapper;
import com.tabwu.health.mapper.RoleMapper;
import com.tabwu.health.mapper.UserMapper;
import com.tabwu.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        Set<Role> roles = roleMapper.getRolesByUserId(user.getId());

        for (Role role : roles) {
            Set<Permission> permissions = permissionMapper.getPermissionByRoleId(role.getId());
            role.setPermissions(permissions);
        }

        user.setRoles(roles);

        return user;
    }
}
