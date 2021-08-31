package com.tabwu.health.mapper;

import com.tabwu.health.entity.User;


public interface UserMapper {
    User getUserByUsername(String username);
}
