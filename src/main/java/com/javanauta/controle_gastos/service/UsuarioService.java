package com.javanauta.controle_gastos.service;

import com.javanauta.controle_gastos.converter.UsuarioConverter;
import com.javanauta.controle_gastos.dto.LoginDTO;
import com.javanauta.controle_gastos.dto.LoginResponse;
import com.javanauta.controle_gastos.dto.UsuarioDTORequest;
import com.javanauta.controle_gastos.dto.UsuarioDTOResponse;
import com.javanauta.controle_gastos.entity.Usuario;
import com.javanauta.controle_gastos.exceptions.ConflictException;
import com.javanauta.controle_gastos.exceptions.ResourceNotFoundExeception;
import com.javanauta.controle_gastos.repository.UsuarioRepository;
import com.javanauta.controle_gastos.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTORequest) {
        emailExiste(usuarioDTORequest.getEmail());
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTORequest);
        usuario.setSenha(passwordEncoder.encode(usuarioDTORequest.getSenha()));
        Usuario entitySalva = usuarioRepository.save(usuario);
        return usuarioConverter.paraUsuarioDTO(entitySalva);
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado", e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public LoginResponse loginUsuario(LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new ResourceNotFoundExeception("Email não encontrado"));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new ResourceNotFoundExeception("Credenciais inválidas");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());

        return new LoginResponse(token);
    }
}
