package com.juliano.gerfin.repository;

import com.juliano.gerfin.model.Movimentacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MovimentacaoRepository extends MongoRepository<Movimentacao, String> { }
