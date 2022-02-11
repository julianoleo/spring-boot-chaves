package com.juliano.apichaves.repository;

import com.juliano.apichaves.model.Movimentacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovimentacaoRepository extends MongoRepository<Movimentacao, String> { }
