package com.compass.microservicoB.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mongodb.MongoTimeoutException;
import com.mongodb.MongoSocketReadException;
import com.mongodb.MongoWriteException;
import com.mongodb.MongoSocketOpenException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionApiHandler 
{
    @ExceptionHandler(ExceptionClasseNaoEncontrada.class)
    public ResponseEntity<ExceptionMensagemDeErro> entityNotFound(RuntimeException exception, HttpServletRequest request) 
    {
        log.error("Api Erro: ", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(new ExceptionMensagemDeErro(request, HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(ExceptionBancoDados.class)
    public ResponseEntity<ExceptionMensagemDeErro> dataBaseException(RuntimeException exception, HttpServletRequest request) 
    {
        log.error("Api Erro: ", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(new ExceptionMensagemDeErro(request, HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler({MongoTimeoutException.class, MongoSocketReadException.class, MongoWriteException.class, MongoSocketOpenException.class})
    public ResponseEntity<ExceptionMensagemDeErro> handleMongoTimeout(Exception ex, HttpServletRequest request) 
    {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).contentType(MediaType.APPLICATION_JSON).body(new ExceptionMensagemDeErro(request, HttpStatus.GATEWAY_TIMEOUT, "Erro ao conectar com o banco de dados!"));
    }
}