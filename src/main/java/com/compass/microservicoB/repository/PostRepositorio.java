package com.compass.microservicoB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compass.microservicoB.entity.Post;

@Repository
public interface PostRepositorio extends MongoRepository <Post, String> {}