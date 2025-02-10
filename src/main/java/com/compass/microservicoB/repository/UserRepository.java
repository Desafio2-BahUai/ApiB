package com.compass.microservicoB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compass.microservicoB.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}