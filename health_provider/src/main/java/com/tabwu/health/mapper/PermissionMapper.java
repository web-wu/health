package com.tabwu.health.mapper;

import com.tabwu.health.entity.Permission;

import java.util.Set;

public interface PermissionMapper {
     Set<Permission> getPermissionByRoleId(Integer RoleId);
}
