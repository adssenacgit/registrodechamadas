package com.senac.johnny.controller;

import com.senac.johnny.dto.CreateUserDto;
import com.senac.johnny.dto.LoginUserDto;
import com.senac.johnny.dto.RecoveryJwtTokenDto;
import com.senac.johnny.entity.Atendente;
import com.senac.johnny.service.AtendenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/atendente")
public class AtendenteController {
    private final AtendenteService atendenteService;

    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @GetMapping("/listar")
    public ResponseEntity <List<Atendente>> listarTodos(){
        return ResponseEntity.ok(atendenteService.listarTodos());
    }

    @GetMapping("/listarporid/{id}")
    public ResponseEntity<Atendente> listarPorId(@PathVariable ("id") Integer id){
        return ResponseEntity.ok(atendenteService.listarPorId(id));
    }

    @PostMapping("/criar")
    public ResponseEntity<Void> criarAtendente (@RequestBody CreateUserDto createUserDto){
        atendenteService.criarAtentende(createUserDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<RecoveryJwtTokenDto> loginAtendente(@RequestBody LoginUserDto loginUserDto){
        return new ResponseEntity<>(atendenteService.login(loginUserDto),HttpStatus.OK);
    }
}
