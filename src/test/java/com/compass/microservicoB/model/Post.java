package com.compass.microservicoB.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Post 
{
    @Id
    private String id;
    private String nome;
    private String email;

    public Post() {}

    public Post(String nome, String email) 
    {
        this.nome = nome;
        this.email = email;
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

    @Override
    public String toString() 
    {
        return "Post{" +
                       "id='" + id + '\'' +
                       ", nome='" + nome + '\'' +
                       ", email='" + email + '\'' +
                       '}';
    }
}