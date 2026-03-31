package com.javanauta.controle_gastos.controller;


import com.javanauta.controle_gastos.dto.GastosDTORequest;
import com.javanauta.controle_gastos.dto.GastosDTOResponse;
import com.javanauta.controle_gastos.service.GastosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
@RequiredArgsConstructor
public class GastosController {
    private final GastosService gastosService;

    @PostMapping
    public ResponseEntity<GastosDTOResponse> criarGasto(@RequestBody GastosDTORequest gastosDTORequest) {
        return ResponseEntity.ok(gastosService.salvaGastos(gastosDTORequest));
    }

    @GetMapping
    public ResponseEntity<List<GastosDTOResponse>> listarGasto() {
        return ResponseEntity.ok(gastosService.listaGastos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGastoPorId(@PathVariable Long id) {
        gastosService.deletaGastos(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity <GastosDTOResponse> atualizarGasto(@PathVariable Long id,@RequestBody GastosDTORequest dtoRequest){
        return ResponseEntity.ok(gastosService.atualizarGastos(id,dtoRequest));
    }

}
