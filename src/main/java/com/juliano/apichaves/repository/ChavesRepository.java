package com.juliano.apichaves.repository;

import com.juliano.apichaves.model.Chaves;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChavesRepository extends MongoRepository<Chaves, String> {
    Optional<Chaves> findByIdCliente(String idCliente);
}
