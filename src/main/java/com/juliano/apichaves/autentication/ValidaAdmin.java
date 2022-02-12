package com.juliano.apichaves.autentication;

import com.juliano.apichaves.exceptions.UnauthorizedException;
import com.juliano.apichaves.model.Chaves;
import com.juliano.apichaves.service.ChavesService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class ValidaAdmin {

    private static final String adminUser = "admin";

    @Autowired
    private ChavesService chavesService;

    public void verificaAuth(HttpServletRequest request, HttpHeaders headers) {
        if(request.getHeader(headers.AUTHORIZATION) == null || request.getHeader(headers.AUTHORIZATION).isBlank() || request.getHeader(headers.AUTHORIZATION).isEmpty()) {
            throw new UnauthorizedException("Dados de autenticação não encontrados.");
        }
        else {
            String valores[] = new String(Base64.decodeBase64(request.getHeader(headers.AUTHORIZATION).substring(6))).split(":");
            var _usuario = valores[0];
            var _senha = valores[1];
            var _result = checaIdentidade(Optional.ofNullable(chavesService.buscaPorId(_senha)), _usuario, _senha);
            if(!_result) {
                throw new UnauthorizedException("Usuário e/ou senha inválidos.");
            }
        }
    }

    private boolean checaIdentidade(Optional<Chaves> chaves, String usuario, String senha) {
        if(chaves == null || chaves.isEmpty() || !chaves.isPresent()) {
            return false;
        } else {
            if(chaves.orElseThrow().getUsuario().equals(usuario) && usuario.equals(adminUser) && chaves.orElseThrow().getId().equals(senha)){
                return true;
            } else {
                return false;
            }
        }
    }
}
