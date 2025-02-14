package com.compass.microservicoB.service;

import com.compass.microservicoB.dto.PostDTO;
import com.compass.microservicoB.entity.Post;
import com.compass.microservicoB.exception.ExceptionBancoDados;
import com.compass.microservicoB.exception.ExceptionClasseNaoEncontrada;
import com.compass.microservicoB.repository.PostRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServico 
{
    @Autowired
    private PostRepositorio postRepositorio;

    public List<Post> buscarTodos() 
    {
        try 
        {
            return postRepositorio.findAll();
        }
        catch(DataAccessException e)
        {
            throw new ExceptionBancoDados("Erro ao conectar com o banco de dados!");
        }
    }

    public Post buscarPostPorID(String id) 
    {
        Optional<Post> post = postRepositorio.findById(id);
        return post.orElseThrow(() -> new ExceptionClasseNaoEncontrada("Post não encontrado!"));
    }

    public Optional<Post> atualizarPost(String id, Post postAtualizado) 
    {
        return postRepositorio.findById(id).map(post -> 
        {
            post.setTitulo(postAtualizado.getTitulo());
            post.setCorpo(postAtualizado.getCorpo());
            return postRepositorio.save(post);
        });
    }

    public void deletarPost(String id) 
    {
        if (!postRepositorio.existsById(id)) 
        {
            throw new ExceptionClasseNaoEncontrada("Post não encontrado!");
        }

        try 
        {
            postRepositorio.deleteById(id);
        } 
        catch (DataAccessException e) 
        {
            throw new ExceptionBancoDados("Erro ao conectar com o banco de dados!");
        }
    }

    public Post salvar(Post post)
    {
        try 
        {
            return postRepositorio.save(post);
        }
        catch (DataAccessException e)
        {
            throw new ExceptionBancoDados("Erro ao conectar com o banco de dados!");
        }
    }

    public Post paraPostDTO(PostDTO postDTO)
    {
        return new Post(postDTO.getId(), postDTO.getUsuarioID(), postDTO.getTitulo(), postDTO.getCorpo());
    }
}