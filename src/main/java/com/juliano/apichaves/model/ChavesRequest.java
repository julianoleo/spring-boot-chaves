package com.juliano.apichaves.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChavesRequest {

    private String idCliente;
    private String usuario;
    private String dataValidade;

    public ChavesRequest() { super(); }

    public ChavesRequest(String idCliente, String usuario, String dataValidade) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.dataValidade = dataValidade;
    }
}