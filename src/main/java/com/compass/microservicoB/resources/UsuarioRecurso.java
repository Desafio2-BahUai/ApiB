package com.compass.microservicoB.resources;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.compass.microservicoB.model.Usuario;
import com.compass.microservicoB.service.UsuarioServico;
 
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioRecurso
{
  @Autowired
  private UsuarioServico usuarioServico;
 
  @RequestMapping(method= RequestMethod.GET)
  public ResponseEntity<List<Usuario>> buscarTodos() 
  {
    List<Usuario> lista = usuarioServico.buscarTodos();
    return ResponseEntity.ok().body(lista);
  }
}