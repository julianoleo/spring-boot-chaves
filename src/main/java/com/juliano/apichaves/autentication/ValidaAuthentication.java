package com.juliano.apichaves.autentication;

import com.juliano.apichaves.exceptions.UnauthorizedException;
import com.juliano.apichaves.model.Chaves;
import com.juliano.apichaves.model.ValidationDto;
import com.juliano.apichaves.service.ChavesService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class ValidaAuthentication {

    @Autowired
    private ChavesService chavesService;

    public ValidationDto validaAutenticacao(HttpServletRequest request, HttpHeaders headers) {
        String valores[] = new String(Base64.decodeBase64(request.getHeader(headers.AUTHORIZATION).substring(6))).split(":");
        var _usuario = valores[0];
        var _senha = valores[1];
        var _result = chavesService.buscaPorIdValid(_senha);

        if(_result == null || _result.isEmpty() || !_result.isPresent()) {
            return new ValidationDto(
                    401,
                    "Password incorreto."
            );
        } else {
            if (checaIdentidade(_result, _usuario, _senha)){
                return new ValidationDto(
                        200,
                        "Autorized."
                );
            } else {
                return new ValidationDto(
                        401,
                        "User or password incorreto."
                );
            }
        }
    }

    private boolean checaIdentidade(Optional<Chaves> chaves, String usuario, String senha) {
        if(chaves == null || chaves.isEmpty() || !chaves.isPresent()) {
            return false;
        } else {
            if(chaves.orElseThrow().getDonoChave().equals(usuario) && chaves.orElseThrow().getId().equals(senha)) {
                return true;
            } else {
                return false;
            }
        }
    }

}
