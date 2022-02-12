package com.juliano.apichaves.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chaves {
    @Id
    private String id;
    private Date dataCriacao;
    private String idCliente;
    private String usuario;
    private Date dataValidade;

    public Chaves(String idCliente, String usuario, Date dataValidade) {
        this.dataCriacao = new Date();
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.dataValidade = dataValidade;
    }
}
