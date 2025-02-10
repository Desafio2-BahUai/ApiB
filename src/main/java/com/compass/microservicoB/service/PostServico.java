package com.compass.microservicoB.service;

import com.compass.microservicoB.model.Post;
import com.compass.microservicoB.repository.PostRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServico 
{
    @Autowired
    private PostRepositorio postRepositorio;

    public Post criarPost(Post post) 
    {
        return postRepositorio.save(post);
    }

    public List<Post> buscarTodos() 
    {
        return postRepositorio.findAll();
    }

    public Optional<Post> buscarPostPorID(String id) 
    {
        return postRepositorio.findById(id);
    }

    public Optional<Post> atualizarPost(String id, Post postDetails) 
    {
        return postRepositorio.findById(id).map(post -> 
        {
            post.setNome(postDetails.getNome());
            post.setEmail(postDetails.getEmail());
            if (postDetails.getComentarios() != null) 
            {
                post.setComentarios(postDetails.getComentarios());
            }
            return postRepositorio.save(post);
        });
    }

    public void deletarPost(String id) 
    {
        postRepositorio.deleteById(id);
    }

    public Post salvar(Post post)
    {
        try 
        {
            return postRepositorio.save(post);
        }
        catch (RuntimeException e)
        {
            throw new RuntimeException("Erro no banco de dados.");
        }
    }
}