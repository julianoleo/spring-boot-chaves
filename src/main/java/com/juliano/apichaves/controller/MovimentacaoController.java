package com.juliano.apichaves.controller;

import com.juliano.apichaves.logs.APILogger;
import com.juliano.apichaves.model.Movimentacao;
import com.juliano.apichaves.model.ResponseDto;
import com.juliano.apichaves.service.MovimentacaoService;
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
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    private static Logger logger = LoggerFactory.getLogger(ContaController.class);

    @PostMapping("/mov")
    public ResponseEntity<Movimentacao> cadastraMovimentacao(
            HttpServletRequest request,
            @RequestParam(name = "id_conta")  String idConta,
            @RequestBody Movimentacao mov,
            @RequestHeader HttpHeaders headers
    ) {
         if(mov.toString().isEmpty()) {
             throw new ConstraintViolationException("Movimentação Vazia.", new HashSet<>());
         }
         else {
             var _result = movimentacaoService.insert(mov, idConta);
             var _response = new ResponseEntity<>(_result, HttpStatus.OK);
             var _responseLog = new ResponseDto<Movimentacao>(_result);
             APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
             return _response;
         }
    }

    @PutMapping("/mov")
    public ResponseEntity<Movimentacao> atualizaMovimentacao(
            HttpServletRequest request,
            @RequestParam(name = "id_mov")  String idMov,
            @RequestBody Movimentacao mov,
            @RequestHeader HttpHeaders headers
    ) throws MissingServletRequestParameterException {
        if(mov.toString().isEmpty()) {
            throw new ConstraintViolationException("Movimentação Vazia.", new HashSet<>());
        }
        else {
            var _result = movimentacaoService.update(idMov, mov);
            var _response = new ResponseEntity<>(_result, HttpStatus.OK);
            var _responseLog = new ResponseDto<Movimentacao>(_result);
            APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
            return _response;
        }
    }
}
