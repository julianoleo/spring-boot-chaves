package com.juliano.apichaves.service;

import com.juliano.apichaves.exceptions.InternalServerErrorException;
import com.juliano.apichaves.exceptions.UnauthorizedException;
import com.juliano.apichaves.model.Chaves;
import com.juliano.apichaves.repository.ChavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChavesService {

    @Autowired
    private ChavesRepository chavesRepository;

    public Optional<Chaves> findById(String id) {
        var _chave =  chavesRepository.findById(id);
        if(_chave.isEmpty()){
            throw new UnauthorizedException("Senha inválida.");
        } else {
            return _chave;
        }
    }

    public Chaves buscaPorId(String id){
        var _chave = findById(id);
        if(_chave.isEmpty()){
            throw new UnauthorizedException("Senha inválida.");
        }
        else {
            return _chave.orElseThrow();
        }
    }

    public Optional<Chaves> buscaPorIdValid(String id){
        return findById(id);
    }

    public Optional<Chaves> findByIdCliente(String idCliente) { return findByIdCliente(idCliente); }

    public Chaves insert(Chaves chaves) {
        var _chave = findByIdCliente(chaves.getIdCliente());
        if(_chave.isPresent()){
            throw new InternalServerErrorException("Cliente com chave cadastrada.");
        } else {
            return chavesRepository.insert(chaves);
        }
    }
}
