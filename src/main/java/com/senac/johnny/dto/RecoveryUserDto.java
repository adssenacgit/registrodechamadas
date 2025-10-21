package com.senac.johnny.dto;


import com.senac.johnny.entity.Role;

import java.util.List;

public record RecoveryUserDto(
        Integer id,
        String usuariologin,
        List<Role> roles
) {
}