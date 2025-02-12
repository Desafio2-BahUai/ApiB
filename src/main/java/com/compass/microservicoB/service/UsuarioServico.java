package com.compass.microservicoB.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.microservicoB.model.Usuario;
import com.compass.microservicoB.repository.UsuarioRepositorio;
 
@Service
public class UsuarioServico 
{
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
 
    public List<Usuario> buscarTodos() 
    {
        return usuarioRepositorio.findAll();
    }
}