package com.juliano.apichaves.service;

import com.juliano.apichaves.exceptions.InternalServerErrorException;
import com.juliano.apichaves.exceptions.UnauthorizedException;
import com.juliano.apichaves.model.Chaves;
import com.juliano.apichaves.model.ChavesRequest;
import com.juliano.apichaves.repository.ChavesRepository;
import com.juliano.apichaves.utils.TrataData;
import com.juliano.apichaves.utils.ValidaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class ChavesService {

    @Autowired
    private ChavesRepository chavesRepository;

    @Autowired
    private ValidaUsuario validaUsuario;

    public Optional<Chaves> findById(String id) {
        return chavesRepository.findById(id);
    }

    public Chaves buscaPorId(String id) {
        var _chave = findById(id);
        if (_chave.isEmpty()) {
            throw new UnauthorizedException("Senha inválida.");
        } else {
            return _chave.orElseThrow();
        }
    }

    public Optional<Chaves> buscaPorIdValid(String id) {
        return findById(id);
    }

    public Optional<Chaves> findByIdCliente(String idCliente) {
        return chavesRepository.findByIdCliente(idCliente);
    }

    public Chaves insert(ChavesRequest chaves) throws ParseException {
        if (chaves.getIdCliente() == null || chaves.getIdCliente().isEmpty() || chaves.getIdCliente().isBlank()) {
            throw new RuntimeException("IdCliente não encontrado no request.");
        }
        else if(chaves.getUsuario() == null || chaves.getUsuario().isEmpty() || chaves.getUsuario().isBlank()) {
            throw new RuntimeException("Usuário não encontrado no request.");
        }
        else if(chaves.getDataValidade() == null) {
            throw new RuntimeException("Data de Validade não encontrado no request.");
        }
        else {
            var _chave = findByIdCliente(chaves.getIdCliente());
            if (_chave.isPresent()) {
                throw new InternalServerErrorException("Cliente com chave cadastrada.");
            } else {
                Chaves chRequest = new Chaves(
                        chaves.getIdCliente(),
                        chaves.getUsuario(),
                        TrataData.formataData(chaves.getDataValidade().toString())
                );
                return chavesRepository.insert(chRequest);
            }
        }
    }

    public Chaves update(String idUsuario, ChavesRequest chaves) throws ParseException {
        validaUsuario.checaUsuario(idUsuario, chaves);
        Optional<Chaves> _usuario = chavesRepository.findById(idUsuario);
        if(chaves.getIdCliente() != null){ _usuario.orElseThrow().setIdCliente(chaves.getIdCliente()); }
        if(chaves.getUsuario() != null){ _usuario.orElseThrow().setUsuario(chaves.getUsuario()); }
        if(chaves.getDataValidade() != null){ _usuario.orElseThrow().setDataValidade(TrataData.formataData(chaves.getDataValidade().toString())); }
        return chavesRepository.save(_usuario.orElseThrow());
    }
}

