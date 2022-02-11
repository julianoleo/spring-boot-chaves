package com.juliano.apichaves.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContaDto {
    private List<Conta> contas;

    public ContaDto() { }

    public ContaDto(List<Conta> contas) {
        this.contas = contas;
    }
}