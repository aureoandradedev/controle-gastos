package com.javanauta.controle_gastos.converter;

import com.javanauta.controle_gastos.dto.GastosDTORequest;
import com.javanauta.controle_gastos.dto.GastosDTOResponse;
import com.javanauta.controle_gastos.entity.Gastos;
import org.springframework.stereotype.Component;


@Component
public class GastosConverter {
    public Gastos paraGastos(GastosDTORequest gastosDTORequest) {
        return Gastos.builder()
                .valor(gastosDTORequest.getValor())
                .descricao(gastosDTORequest.getDescricao())
                .categoria(gastosDTORequest.getCategoria())
                .data(gastosDTORequest.getData())
                .build();
    }

    public GastosDTOResponse paraGastosDTO(Gastos gastos) {
        return GastosDTOResponse.builder()
                .id(gastos.getId())
                .valor(gastos.getValor())
                .descricao(gastos.getDescricao())
                .data(gastos.getData())
                .categoria(gastos.getCategoria())
                .build();
    }


    public Gastos updateGastos(GastosDTORequest gastosDTORequest, Gastos gastos) {
        gastos.setValor(gastosDTORequest.getValor());
        gastos.setCategoria(gastosDTORequest.getCategoria());
        gastos.setDescricao(gastosDTORequest.getDescricao());
        gastos.setData(gastosDTORequest.getData());
        return gastos;
    }
}
