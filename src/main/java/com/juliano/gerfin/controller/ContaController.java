package com.juliano.gerfin.controller;

import com.juliano.gerfin.exceptions.ExcpetionHandler;
import com.juliano.gerfin.exceptions.NoContentRuntimeException;
import com.juliano.gerfin.logs.APILogger;
import com.juliano.gerfin.model.Conta;
import com.juliano.gerfin.model.ResponseDto;
import com.juliano.gerfin.service.ContaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@RestController
@Validated
@RequestMapping({"api/v1/gerfin"})
public class ContaController {

    @Autowired
    private ContaService contaService;

    private static Logger logger = LoggerFactory.getLogger(ContaController.class);

    @GetMapping("/conta")
    public ResponseEntity<Conta> findIdConta(
            HttpServletRequest request,
            @RequestParam(name = "agencia")  String agencia,
            @RequestParam(name = "conta")  String conta,
            @RequestHeader HttpHeaders header
    ) {
        var _result = contaService.buscaIdConta(agencia, conta);
        var _response = new ResponseEntity<>(_result, HttpStatus.OK);
        var _responseLog = new ResponseDto<>(_result);
        APILogger.ok(_responseLog.getData(), APILogger.filterHeader(header));
        return _response;
    }

    @PostMapping("/conta/add")
    public ResponseEntity<Conta> cadastraConta(
            HttpServletRequest request,
            @RequestBody Conta conta,
            @RequestHeader HttpHeaders headers
    ) {
         if(conta.toString().isEmpty()) {
             throw new ConstraintViolationException("Conta Vazia.", new HashSet<>());
         } else {
             var _result = contaService.insert(conta);
             var _response = new ResponseEntity<>(_result, HttpStatus.OK);
             var _responseLog = new ResponseDto<Conta>(_result);
             APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
             return _response;
         }
    }
}