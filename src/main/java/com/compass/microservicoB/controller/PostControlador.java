package com.compass.microservicoB.controller;

import com.compass.microservicoB.model.Comentario;
import com.compass.microservicoB.model.Post;
import com.compass.microservicoB.service.PostServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostControlador 
{
    @Autowired
    private PostServico postServico;

    @PostMapping
    public ResponseEntity<Post> criarPost(@RequestBody Post post) 
    {
        Post postCriado = postServico.criarPost(post);
        return ResponseEntity.status(201).body(postCriado);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getTodosPosts() 
    {
        List<Post> posts = postServico.buscarTodos();
        if (posts.isEmpty()) 
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getBuscarPostsPorID(@PathVariable String id) 
    {
        Optional<Post> post = postServico.buscarPostPorID(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<Comentario>> getComentarioPorPostID(@PathVariable String id) 
    {
        Optional<Post> post = postServico.buscarPostPorID(id);
        if (post.isPresent()) 
        {
            List<Comentario> comentarios = post.get().getComentarios();
            if (comentarios.isEmpty()) 
            {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(comentarios);
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPost(@PathVariable String id, @RequestBody Post postDetails) 
    {
        Optional<Post> updatedPost = postServico.atualizarPost(id, postDetails);
        if (updatedPost.isPresent()) 
        {
            return ResponseEntity.ok(updatedPost.get());
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) 
    {
        Optional<Post> post = postServico.buscarPostPorID(id);
        if (post.isPresent()) 
        {
            postServico.deletarPost(id);
            return ResponseEntity.noContent().build();
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }
}