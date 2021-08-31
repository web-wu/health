package com.tabwu.health.mapper;

import com.tabwu.health.entity.Role;

import java.util.Set;

public interface RoleMapper {
    Set<Role> getRolesByUserId(Integer UserId);
}
