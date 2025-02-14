package com.compass.microservicoB;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class ProgramaMicroservicoB 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(ProgramaMicroservicoB.class, args);
	}

	@Bean
	public CommandLineRunner helloWorldRunner()
	{
		return args -> System.out.println("Funcionou? Funcionou!");
	}
}