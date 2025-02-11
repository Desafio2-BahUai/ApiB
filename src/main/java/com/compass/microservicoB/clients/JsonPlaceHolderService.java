package com.compass.microservicoB.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compass.microservicoB.domain.Post;
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

    public Post fetchPost(Long id) 
    {
      return client.getBuscarPostsPorID(id);
    }

    public void fetchData() 
    {
      List<Post> posts = client.getPosts();

      for (Post post : posts) 
      {
        postServico.salvar(post);
      }
    }
}