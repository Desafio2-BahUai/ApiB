package com.compass.microservicoB.dto;

import com.compass.microservicoB.entity.Post;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class PostDTO
{
  private String id;
  private String usuarioID;
  private String titulo;
  private String corpo;

  public PostDTO(Post post) 
  {
    this.id = post.getId();
    this.usuarioID = post.getUsuarioID();
    this.titulo = post.getTitulo();
    this.corpo = post.getCorpo();
  }
}