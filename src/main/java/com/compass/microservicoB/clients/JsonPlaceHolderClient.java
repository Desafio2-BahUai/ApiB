package com.compass.microservicoB.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.compass.microservicoB.entity.Post;

import java.util.List;

@FeignClient(name = "jsonPlaceholderClient", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceHolderClient 
{
  @GetMapping("/posts")
  List<Post> getPosts();

  @GetMapping("/posts/{id}")
  Post buscarPostsPorID(@PathVariable Long id);

  @PostMapping("/posts")
  Post criarPost(@RequestBody Post post);
}