package com.compass.microservicoB.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.microservicoB.entity.Post;
import com.compass.microservicoB.service.PostServico;

import java.util.List;

@Service
public class JsonPlaceHolderService 
{
    @Autowired
    private PostServico postServico;

    private final JsonPlaceHolderClient client;

    public JsonPlaceHolderService(JsonPlaceHolderClient client) 
    {
      this.client = client;
    }

    public Post buscarPost(Long id) 
    {
      return client.buscarPostsPorID(id);
    }

    public void buscarDados() 
    {
      List<Post> posts = client.getPosts();

      for (Post post : posts) 
      {
        postServico.salvar(post);
      }
    }
}