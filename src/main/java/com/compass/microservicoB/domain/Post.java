package com.compass.microservicoB.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "posts")
public class Post 
{
    @Id
    @MongoId
    private String id;
    private String nome;
    private String email;
    private List<Comentario> comentarios;

    public Post() {}

    public Post(String id, String nome, String email, List<Comentario> comentarios) 
    {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.comentarios = comentarios;
    }

    public String getId() 
    {
        return id;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public List<Comentario> getComentarios() 
    {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comments) 
    {
        this.comentarios = comments;
    }
}