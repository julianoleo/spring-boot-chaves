package com.juliano.gerfin.service;

import com.juliano.gerfin.exceptions.NoContentRuntimeException;
import com.juliano.gerfin.exceptions.NotFoundException;
import com.juliano.gerfin.model.Conta;
import com.juliano.gerfin.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Optional<Conta> findByNumAgenciaAndNumConta(String numAgencia, String numConta) {
        var _conta = contaRepository.findByNumAgenciaAndNumConta(numAgencia, numConta);
        if(_conta.isEmpty()) {
            throw new NoContentRuntimeException("Conta não encontrada.");
        }
        else {
            return _conta;
        }
    }

    public Optional<Conta> findById(String id) {
        return contaRepository.findById(id);
    }

    public Conta buscaIdConta(String numAgencia, String numConta) {
        Conta conta = new Conta();
        conta.setId(findByNumAgenciaAndNumConta(numAgencia, numConta).orElseThrow().getId());
        return conta;
    }

    public Conta insert(Conta conta) {
        if(contaExiste(conta)) {
            throw new NotFoundException("Conta Já cadastrada");
        } else {
            var _conta = new Conta(
                    conta.getNumAgencia(),
                    conta.getNumConta(),
                    conta.getNomeTitular()
            );
            return contaRepository.insert(_conta);
        }
    }

    public Boolean contaExiste(Conta conta) {
        if(contaRepository.findByNumAgenciaAndNumConta(conta.getNumAgencia(), conta.getNumConta()).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

//    public List<Pessoa> findAll() { return pessoaRepository.findAll(); }
//
//    public Boolean findByIdCliente(String idCliente) {
//        var _result = pessoaRepository.findByIdCliente(idCliente);
//        if(_result == null) { return false;} else { return true;}
//    }
//
//    public Pessoa insert(Pessoa pessoa){
//        return pessoaRepository.insert(pessoa);
//    }
}