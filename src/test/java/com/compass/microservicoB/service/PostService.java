package com.compass.microservicoB.service;

import com.compass.microservicoB.model.Post;
import com.compass.microservicoB.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;


    public Post createPost(Post post) {
        return postRepository.save(post);
    }


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }


    public Optional<Post> updatePost(String id, Post postDetails) {
        return postRepository.findById(id).map(post -> {
            post.setNome(postDetails.getNome());
            post.setEmail(postDetails.getEmail());
            return postRepository.save(post);
        });
    }


    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}
