package com.juliano.apichaves.service;

import com.juliano.apichaves.exceptions.NoContentRuntimeException;
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

    public Chaves insert(Chaves chaves) {
        return chavesRepository.insert(chaves);
    }
}
