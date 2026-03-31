package com.javanauta.controle_gastos.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    private String email;
    private String senha;

}
