package com.javanauta.controle_gastos.service;

import com.javanauta.controle_gastos.converter.GastosConverter;
import com.javanauta.controle_gastos.dto.GastosDTORequest;
import com.javanauta.controle_gastos.dto.GastosDTOResponse;
import com.javanauta.controle_gastos.entity.Gastos;
import com.javanauta.controle_gastos.entity.Usuario;
import com.javanauta.controle_gastos.exceptions.ResourceNotFoundExeception;
import com.javanauta.controle_gastos.repository.GastosRepository;
import com.javanauta.controle_gastos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class GastosService {
    private final GastosRepository gastosRepository;
    private final GastosConverter gastosConverter;
    private final UsuarioRepository usuarioRepository;

    public GastosDTOResponse salvaGastos(GastosDTORequest gastosDTORequest) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExeception("Usuário não encontrado"));

        Gastos gastos = gastosConverter.paraGastos(gastosDTORequest);
        gastos.setUsuarioId(usuario.getId());

        Gastos entitySalva = gastosRepository.save(gastos);

        return gastosConverter.paraGastosDTO(entitySalva);
    }


    public List<GastosDTOResponse> listaGastos() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExeception("Usuário não encontrado"));

        List<Gastos> listarGastos = gastosRepository.findByUsuarioId(usuario.getId());

        List<GastosDTOResponse> listaGastosDTO = new ArrayList<>();

        for (Gastos gastos : listarGastos) {
            listaGastosDTO.add(gastosConverter.paraGastosDTO(gastos));
        }

        return listaGastosDTO;
    }

    public GastosDTOResponse atualizarGastos(Long id, GastosDTORequest gastosDTORequest) {

        Gastos entity = gastosRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExeception("Id não encontrado " + id));

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExeception("Usuário não encontrado"));

        if (!entity.getUsuarioId().equals(usuario.getId())) {
            throw new RuntimeException("Acesso negado");
        }

        Gastos gastos = gastosConverter.updateGastos(gastosDTORequest, entity);

        return gastosConverter.paraGastosDTO(gastosRepository.save(gastos));
    }

    public boolean verificaIdExistente(Long id) {
        return gastosRepository.existsById(id);
    }

    public void deletaGastos(Long id) {

        Gastos gastos = gastosRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExeception("Id não encontrado " + id));

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExeception("Usuário não encontrado"));

        if (!gastos.getUsuarioId().equals(usuario.getId())) {
            throw new RuntimeException("Acesso negado");
        }

        gastosRepository.deleteById(id);
    }
}
