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
public class Movimentacao {

    @Id
    private String id;
    private String dataMov;
    private Conta conta;
    private String tipoMov;
    private Double valorMov;
    private String descricao;
    private String dataVcto;
    private String dataPgto;
    private Double valorPago;

    public Movimentacao() { }

    public Movimentacao(String tipoMov, Double valorMov, String descricao, String dataVcto, String dataPgto) {
        this.dataMov = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.tipoMov = tipoMov;
        this.valorMov = valorMov;
        this.descricao = descricao;
        this.dataVcto = dataVcto;
        this.dataPgto = dataPgto;
    }

    @Override
    public String toString() {
        return "Movimentacao{" +
                "id='" + id + '\'' +
                ", dataMov='" + dataMov + '\'' +
                ", conta=" + conta +
                ", tipoMov='" + tipoMov + '\'' +
                ", valorMov=" + valorMov +
                ", descricao='" + descricao + '\'' +
                ", dataVcto='" + dataVcto + '\'' +
                ", dataPgto='" + dataPgto + '\'' +
                ", valorPago=" + valorPago +
                '}';
    }
}