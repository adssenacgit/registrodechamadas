package com.senac.johnny.service;

import com.senac.johnny.config.SecurityConfiguration;
import com.senac.johnny.dto.CreateUserDto;
import com.senac.johnny.dto.LoginUserDto;
import com.senac.johnny.dto.RecoveryJwtTokenDto;
import com.senac.johnny.entity.Atendente;
import com.senac.johnny.entity.Role;
import com.senac.johnny.repository.AtendenteRepository;
import com.senac.johnny.repository.RoleRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AtendenteService {
    private final AtendenteRepository atendenteRepository;
    private final RoleRepository roleRepository;
    private final SecurityConfiguration securityConfiguration;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AtendenteService(AtendenteRepository atendenteRepository, RoleRepository roleRepository, SecurityConfiguration securityConfiguration, AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.atendenteRepository = atendenteRepository;
        this.roleRepository = roleRepository;
        this.securityConfiguration = securityConfiguration;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    public List<Atendente> listarTodos(){
        return atendenteRepository.findAll();
    }
    public Atendente listarPorId(Integer id){
        return atendenteRepository.findById(id).orElse(null);
    }

    public void criarAtentende(CreateUserDto createUserDto) {
        Role role = roleRepository.findByName(createUserDto.role());

        Atendente novoAtendente = new Atendente();

        novoAtendente.setNome(createUserDto.nome());
        novoAtendente.setUsuarioLogin(createUserDto.usuarioLogin());
        novoAtendente.setChaveAcesso(securityConfiguration.passwordEncoder().encode(createUserDto.chaveAcesso()));
        novoAtendente.setDataCriacao(LocalDateTime.now());
        novoAtendente.setAtivo(1);
        novoAtendente.setRoles(List.of(role));
        atendenteRepository.save(novoAtendente);

    }

    public RecoveryJwtTokenDto login(LoginUserDto loginUserDto) {
        //Passo 1
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUserDto.login(), loginUserDto.chaveAcesso());
        //Passo 2
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //Passo 3
        UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
        //Passo 4
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));

    }
}
