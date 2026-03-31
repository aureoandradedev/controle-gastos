package com.javanauta.controle_gastos.controller;

import com.javanauta.controle_gastos.dto.LoginDTO;
import com.javanauta.controle_gastos.dto.LoginResponse;
import com.javanauta.controle_gastos.dto.UsuarioDTORequest;
import com.javanauta.controle_gastos.dto.UsuarioDTOResponse;
import com.javanauta.controle_gastos.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor

public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity <UsuarioDTOResponse> criarUsuario (@RequestBody UsuarioDTORequest usuarioDTORequest){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTORequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUsuario(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.ok(usuarioService.loginUsuario(loginDTO));
    }
}
