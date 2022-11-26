package com.one.onegate.mapper;

import com.one.onegate.model.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {
    Role findByName(String name);
    Integer save(Role role);
}
