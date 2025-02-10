package com.compass.microservicoB.repository;

import com.compass.microservicoB.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositorio extends MongoRepository<Post, String> {}