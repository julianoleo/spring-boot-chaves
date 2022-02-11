package com.juliano.apichaves.repository;

import com.juliano.apichaves.model.Conta;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContaRepository extends MongoRepository<Conta, String> {
    Optional<Conta> findByNumAgenciaAndNumConta(String numAgencia, String numConta);
}
