package com.compass.microservicoB.resources;
 
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compass.microservicoB.domain.Post;
import com.compass.microservicoB.domain.Usuario;
import com.compass.microservicoB.dto.UserDTO;
import com.compass.microservicoB.service.UsuarioServico;
 
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioRecurso
{
  @Autowired
	private UsuarioServico service;
	
	@GetMapping
 	public ResponseEntity<List<UserDTO>> findAll() {
		List<Usuario> list = service.buscarTodos();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping("/{id}")
 	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	@PostMapping
 	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		Usuario obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
 	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		Usuario obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/posts")
 	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}
}