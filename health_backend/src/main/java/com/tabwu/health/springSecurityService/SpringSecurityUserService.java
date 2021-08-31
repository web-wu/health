package com.tabwu.health.springSecurityService;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tabwu.health.entity.Permission;
import com.tabwu.health.entity.Role;
import com.tabwu.health.entity.User;
import com.tabwu.health.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByUsername(username);

            if (user == null) {
                return null;
            }

            Set<Role> roles = user.getRoles();

            List<GrantedAuthority> list = new ArrayList<>();

            for (Role role : roles) {
                list.add(new SimpleGrantedAuthority(role.getKeyword()));

                Set<Permission> permissions = role.getPermissions();
                for (Permission permission : permissions) {
                    list.add(new SimpleGrantedAuthority(permission.getKeyword()));
                }
            }

            UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
            return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
