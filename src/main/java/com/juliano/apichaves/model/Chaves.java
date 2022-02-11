package com.juliano.apichaves.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chaves {
    @Id
    private String id;
    private Date dataCriacao;
    private String donoChave;

    public Chaves(Date dataCriacao, String donoChave) {
        this.dataCriacao = new Date();
        this.donoChave = donoChave;
    }
}
