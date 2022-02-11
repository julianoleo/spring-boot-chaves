package com.juliano.gerfin.repository;

import com.juliano.gerfin.model.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContaRepository extends MongoRepository<Conta, String> {
    Optional<Conta> findByNumAgenciaAndNumConta(String numAgencia, String numConta);
}
