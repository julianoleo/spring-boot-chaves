package com.juliano.gerfin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Conta {

    @Id
    private String id;
    private String dataCriacao;
    private String numAgencia;
    private String numConta;
    private String nomeTitular;

    public Conta() { }

    public Conta(String numAgencia, String numConta, String nomeTitular) {
        this.dataCriacao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.numAgencia = numAgencia;
        this.numConta = numConta;
        this.nomeTitular = nomeTitular;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id='" + id + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", numAgencia='" + numAgencia + '\'' +
                ", numConta='" + numConta + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                '}';
    }
}
