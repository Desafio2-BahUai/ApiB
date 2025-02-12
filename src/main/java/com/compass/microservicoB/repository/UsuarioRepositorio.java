package com.compass.microservicoB.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compass.microservicoB.model.Usuario;

@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {}