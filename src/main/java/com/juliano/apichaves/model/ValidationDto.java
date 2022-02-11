package com.juliano.apichaves.model;

import lombok.Data;

@Data
public class ValidationDto {
    private Integer statusCode;
    private String descStatus;

    public ValidationDto(Integer statusCode, String descStatus) {
        this.statusCode = statusCode;
        this.descStatus = descStatus;
    }
}
