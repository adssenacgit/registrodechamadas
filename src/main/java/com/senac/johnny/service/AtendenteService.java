package com.senac.johnny.service;

import com.senac.johnny.config.SecurityConfiguration;
import com.senac.johnny.dto.CreateUserDto;
import com.senac.johnny.entity.Atendente;
import com.senac.johnny.entity.Role;
import com.senac.johnny.repository.AtendenteRepository;
import com.senac.johnny.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtendenteService {
    private final AtendenteRepository atendenteRepository;
    private final RoleRepository roleRepository;
    private final SecurityConfiguration securityConfiguration;

    public AtendenteService(AtendenteRepository atendenteRepository, RoleRepository roleRepository, SecurityConfiguration securityConfiguration) {
        this.atendenteRepository = atendenteRepository;
        this.roleRepository = roleRepository;
        this.securityConfiguration = securityConfiguration;
    }

    public List<Atendente> listarTodos(){
        return atendenteRepository.findAll();
    }
    public Atendente listarPorId(Integer id){
        return atendenteRepository.findById(id).orElse(null);
    }

    public void criarAtentende(CreateUserDto createUserDto) {
        Role role = roleRepository.findByName(createUserDto.role().name());

        Atendente novoAtendente = new Atendente();

        novoAtendente.setNome(createUserDto.nome());
        novoAtendente.setUsuarioLogin(createUserDto.usuarioLogin());
        novoAtendente.setChaveAcesso(securityConfiguration.passwordEncoder().encode(createUserDto.chaveAcesso()));
        novoAtendente.setDataCriacao(LocalDateTime.now());
        novoAtendente.setAtivo(1);
        novoAtendente.setRoles(List.of(role));
        atendenteRepository.save(novoAtendente);

    }
}
