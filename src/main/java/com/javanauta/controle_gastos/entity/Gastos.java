package com.javanauta.controle_gastos.entity;

import com.javanauta.controle_gastos.dto.GastosDTORequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "gastos")

public class Gastos{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "valor")
    private Long valor;
    @Column (name = "descricao", length = 100)
    private String descricao;
    @Column (name = "data")
    private LocalDateTime data;
    @Column (name = "categoria", length = 100)
    private String categoria;
    @Column (name = "usuario_id")
    private Long usuarioId;
}
