package com.javanauta.controle_gastos.repository;

import com.javanauta.controle_gastos.entity.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastosRepository extends JpaRepository <Gastos, Long> {

    List<Gastos> findByUsuarioId (Long usuarioId);
}
