package com.javanauta.controle_gastos.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTORequest {
    private String nome;
    private String senha;
    private String email;

}
