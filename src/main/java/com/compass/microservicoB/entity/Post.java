package com.compass.microservicoB.entity;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@Document
public class Post implements Serializable
{
    @Id
    @MongoId
    private String id;
    private String usuarioID;
    private String titulo;
    private String corpo;
}