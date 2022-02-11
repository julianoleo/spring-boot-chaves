package com.juliano.gerfin.service;

import com.juliano.gerfin.exceptions.NoContentRuntimeException;
import com.juliano.gerfin.exceptions.NotFoundException;
import com.juliano.gerfin.model.Movimentacao;
import com.juliano.gerfin.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ContaService contaService;

    public Optional<Movimentacao> findById(String id) {
        var _mov = movimentacaoRepository.findById(id);
        if(_mov.isEmpty()) {
            throw new RuntimeException("Movimentação não encontrada.");
        }
        else {
            return _mov;
        }
    }

    public Movimentacao insert(Movimentacao mov, String idConta) {
        var _conta = contaService.findById(idConta);
        if(_conta.isEmpty()) {
            throw new NotFoundException("Conta Inexistente.");
        }
        else {
            mov.setConta(_conta.orElseThrow());
            return movimentacaoRepository.insert(mov);
        }
    }

    public Movimentacao update(String id, Movimentacao movimentacao) {
        Optional<Movimentacao> _mov = movimentacaoRepository.findById(id);
        if(_mov.isEmpty()){
            throw new NotFoundException("Movimentação Inexistente.");
        }
        else {
            if (movimentacao.getDataPgto() != null) { _mov.orElseThrow().setDataPgto(movimentacao.getDataPgto()); }
            if (movimentacao.getDescricao() != null) { _mov.orElseThrow().setDescricao(movimentacao.getDescricao()); }
            if (movimentacao.getDataVcto() != null) { _mov.orElseThrow().setDataVcto(movimentacao.getDataVcto()); }
            if (movimentacao.getValorMov() != null) { _mov.orElseThrow().setValorMov(movimentacao.getValorMov()); }
            if (movimentacao.getValorPago() != null) { _mov.orElseThrow().setValorPago(movimentacao.getValorPago()); }
            return movimentacaoRepository.save(_mov.orElseThrow());
        }
     }
}

//    public Double saldo(String conta) throws Exception {
//        try {
//            List<Movimentacao> _movs = movService.findByConta(conta);
//            if(_movs.isEmpty()) {
//                return 0.0;
//            } else {
//                Double _saldo = 0.00;
//                for (Movimentacao mov : _movs) {
//                    if (mov.getTipoMov().equalsIgnoreCase("d")) {
//                        _saldo = _saldo + mov.getValorMov();
//                    }
//                    if (mov.getTipoMov().equalsIgnoreCase("s")){
//                        _saldo = _saldo - mov.getValorMov();
//                    }
//                }
//                return _saldo;
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    public Boolean verificaSaldo(String conta, Double valor) throws Exception {
//        try {
//            var _saldo = saldo(conta);
//            if(valor > _saldo) {
//                return false;
//            } else {
//                return true;
//            }
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }
