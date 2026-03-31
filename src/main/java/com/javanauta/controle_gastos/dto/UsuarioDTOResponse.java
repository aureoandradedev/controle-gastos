package com.javanauta.controle_gastos.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTOResponse {
    private Long id;
    private String nome;
    private String email;
}
