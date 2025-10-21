package com.senac.johnny.dto;


import com.senac.johnny.entity.RoleName;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateUserDto(
        String nome,
        String usuarioLogin,
        String chaveAcesso,
        LocalDateTime dataCriacao,
        RoleName role
) {
}