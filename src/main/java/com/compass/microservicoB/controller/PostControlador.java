package com.compass.microservicoB.controller;

import com.compass.microservicoB.model.Comment;
import com.compass.microservicoB.model.Post;
import com.compass.microservicoB.service.PostServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
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
    public ResponseEntity<Post> getBuscarPostsPorID(@PathVariable String id) {
        Optional<Post> post = postServico.getBuscarPostsPorID(id);
        return post.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable String id) {
        Optional<Post> post = postService.getBuscarPostsPorID(id);
        if (post.isPresent()) {
            List<Comment> comments = post.get().getComments();
            if (comments.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(comments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post postDetails) {
        Optional<Post> updatedPost = postService.updatePost(id, postDetails);
        if (updatedPost.isPresent()) {
            return ResponseEntity.ok(updatedPost.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        Optional<Post> post = postService.getBuscarPostsPorID(id);
        if (post.isPresent()) {
            postService.deletePost(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
