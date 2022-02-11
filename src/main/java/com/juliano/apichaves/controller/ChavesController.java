package com.juliano.apichaves.controller;

import com.juliano.apichaves.autentication.Autentication;
import com.juliano.apichaves.logs.APILogger;
import com.juliano.apichaves.logs.models.ResponseDto;
import com.juliano.apichaves.model.Chaves;
import com.juliano.apichaves.service.ChavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;

@RestController
@Validated
@RequestMapping({"api/v1/chaves"})
public class ChavesController {

    @Autowired
    private ChavesService chavesService;

    @Autowired
    private Autentication autentication;

    @GetMapping("/{idChave}")
    public ResponseEntity<Chaves> cadastraChave(
            HttpServletRequest request,
            @PathVariable(name = "idChave") String idChave,
            @RequestHeader HttpHeaders headers
    ) {
            autentication.verificaAuth(request, headers);
            var _result = chavesService.buscaPorId(idChave);
            var _response = new ResponseEntity<>(_result, HttpStatus.OK);
            var _responseLog = new ResponseDto<>(_result);
            APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
            return _response;
    }

    @PostMapping("/add")
    public ResponseEntity<Chaves> cadastraChave(
            HttpServletRequest request,
            @RequestBody Chaves chaves,
            @RequestHeader HttpHeaders headers
    ) {
        if(chaves.getDonoChave().isEmpty()) {
            throw new ConstraintViolationException("Chave Vazia.", new HashSet<>());
        }
        else {
            var _result = chavesService.insert(chaves);
            var _response = new ResponseEntity<>(_result, HttpStatus.OK);
            var _responseLog = new ResponseDto<>(_result);
            APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
            return _response;
        }
    }
}
