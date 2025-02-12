package com.compass.microservicoB.controller;

import com.compass.microservicoB.model.Post;
import com.compass.microservicoB.service.PostServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController 
{
    @Autowired
    private PostServico postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) 
    {
        Post createdPost = postService.criarPost(post);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping
    public ResponseEntity<List<Post>> buscarTodos() 
    {
        List<Post> posts = postService.buscarTodos();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> buscarPostPorID(@PathVariable String id) 
    {
        Optional<Post> post = postService.buscarPostPorID(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> atualizarPost(@PathVariable String id, @RequestBody Post postDetails) 
    {
        Optional<Post> updatedPost = postService.atualizarPost(id, postDetails);
        return updatedPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable String id) 
    {
        postService.deletarPost(id);
        return ResponseEntity.noContent().build();
    }
}