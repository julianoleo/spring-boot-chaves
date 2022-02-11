package com.juliano.apichaves.repository;

import com.juliano.apichaves.model.Chaves;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChavesRepository extends MongoRepository<Chaves, String> {
}
