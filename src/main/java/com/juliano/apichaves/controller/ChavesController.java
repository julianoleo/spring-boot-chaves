package com.juliano.apichaves.controller;

import com.juliano.apichaves.autentication.Autentication;
import com.juliano.apichaves.autentication.ValidaAdmin;
import com.juliano.apichaves.autentication.ValidaAuthentication;
import com.juliano.apichaves.logs.APILogger;
import com.juliano.apichaves.logs.models.ResponseDto;
import com.juliano.apichaves.model.ChavesRequest;
import com.juliano.apichaves.model.ValidationDto;
import com.juliano.apichaves.service.ChavesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RestController
@Validated
@RequestMapping({"api/v1/chaves"})
public class ChavesController {

    @Autowired
    private ChavesService chavesService;

    @Autowired
    private Autentication autentication;

    @Autowired
    private ValidaAdmin validaAdmin;

    @Autowired
    private ValidaAuthentication validaAuthentication;

    @GetMapping("/validation")
    public ResponseEntity<ValidationDto> validaChave(
            HttpServletRequest request,
            @RequestHeader HttpHeaders headers
    ) {
        var _result = validaAuthentication.validaAutenticacao(request, headers);
        var _response = new ResponseEntity<>(_result, HttpStatus.OK);
        var _responseLog = new ResponseDto<>(_result);
        APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
        return _response;
    }

    @GetMapping("/{idChave}")
    public ResponseEntity<?> cadastraChave(
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
    public ResponseEntity<?> updateChave(
            HttpServletRequest request,
            @RequestBody ChavesRequest chaves,
            @RequestHeader HttpHeaders headers
    ) throws ParseException {
            validaAdmin.verificaAuth(request, headers);
            var _result = chavesService.insert(chaves);
            var _response = new ResponseEntity<>(_result, HttpStatus.OK);
            var _responseLog = new ResponseDto<>(_result);
            APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
            return _response;
    }

    @PutMapping("/update/{idChave}")
    public ResponseEntity<?> cadastraChave(
            HttpServletRequest request,
            @PathVariable(name = "idChave") String idChave,
            @RequestBody ChavesRequest chaves,
            @RequestHeader HttpHeaders headers
    ) throws ParseException {
        validaAdmin.verificaAuth(request, headers);
        var _result = chavesService.update(idChave, chaves);
        var _response = new ResponseEntity<>(_result, HttpStatus.OK);
        var _responseLog = new ResponseDto<>(_result);
        APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
        return _response;
    }

    @DeleteMapping("/delete/{idChave}")
    public ResponseEntity<?> deleteChave(
            HttpServletRequest request,
            @PathVariable(name = "idChave") String idChave,
            @RequestHeader HttpHeaders headers
    ) throws ParseException {
        validaAdmin.verificaAuth(request, headers);
        chavesService.delete(idChave);
        var _response = new ResponseEntity<>("", HttpStatus.OK);
        var _responseLog = new ResponseDto<>("");
        APILogger.ok(_responseLog.getData(), APILogger.filterHeader(headers));
        return _response;
    }
}
