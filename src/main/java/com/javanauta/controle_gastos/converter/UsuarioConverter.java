package com.javanauta.controle_gastos.converter;

import com.javanauta.controle_gastos.dto.UsuarioDTORequest;
import com.javanauta.controle_gastos.dto.UsuarioDTOResponse;
import com.javanauta.controle_gastos.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {
public Usuario paraUsuario (UsuarioDTORequest usuarioDTORequest){
    return Usuario.builder()
            .email(usuarioDTORequest.getEmail())
            .senha(usuarioDTORequest.getSenha())
            .nome(usuarioDTORequest.getNome())
            .build();
}
   public UsuarioDTOResponse paraUsuarioDTO (Usuario usuario){
    return UsuarioDTOResponse.builder()
            .id(usuario.getId())
            .email(usuario.getEmail())
            .nome(usuario.getNome())
            .build();
   }
}
