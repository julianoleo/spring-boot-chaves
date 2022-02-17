package com.juliano.apichaves.utils;

import com.juliano.apichaves.exceptions.NotFoundException;
import com.juliano.apichaves.model.Chaves;
import com.juliano.apichaves.repository.ChavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidaUsuario {

    @Autowired
    private ChavesRepository chavesRepository;

    public void checaUsuario(String idUsuario, Chaves chaves) {
        if(!usuarioExiste(idUsuario)){
            throw new NotFoundException("Usuário inexistente.");
        }
        else { }
    }

    private Boolean usuarioExiste(String idUsuario) {
        var _usuario = chavesRepository.findById(idUsuario);
        if(_usuario.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
