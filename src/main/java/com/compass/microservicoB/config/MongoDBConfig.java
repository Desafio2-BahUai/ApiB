package com.compass.microservicoB.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoDBConfig 
{
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "microservicoB";

    @Bean
    public MongoClient mongoClient() 
    {
        return MongoClients.create(CONNECTION_STRING);
    }

    @Bean
    public MongoTemplate mongoTemplate() 
    {
        return new MongoTemplate(mongoClient(), DATABASE_NAME);
    }
}
