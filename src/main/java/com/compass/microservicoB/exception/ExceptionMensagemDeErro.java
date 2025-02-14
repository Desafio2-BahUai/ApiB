package com.compass.microservicoB.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class ExceptionMensagemDeErro 
{
    private String caminho;
    private String metodo;
    private int status;
    private String statusTexto;
    private String mensagem;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> erro;

    public ExceptionMensagemDeErro(HttpServletRequest request, HttpStatus status, String mensagem) 
    {
        this.caminho = request.getRequestURI();
        this.metodo = request.getMethod();
        this.status = status.value();
        this.statusTexto = status.getReasonPhrase();
        this.mensagem = mensagem;

    }

    public ExceptionMensagemDeErro(HttpServletRequest request, HttpStatus status, String mensagem, BindingResult resultado) 
    {
        this.caminho = request.getRequestURI();
        this.metodo = request.getMethod();
        this.status = status.value();
        this.statusTexto = status.getReasonPhrase();
        this.mensagem = mensagem;
        erros(resultado);
    }

    public ExceptionMensagemDeErro(HttpServletRequest request, HttpStatus status, String invalidField, BindingResult resultado, MessageSource mensagemFonte) 
    {
        this.caminho = request.getRequestURI();
        this.metodo = request.getMethod();
        this.status = status.value();
        this.statusTexto = status.getReasonPhrase();
        erros(resultado, mensagemFonte, request.getLocale());
    }

    private void erros(BindingResult resultado, MessageSource messageFonte, Locale locale) 
    {
        this.erro = new HashMap<>();
        for(FieldError fieldError : resultado.getFieldErrors()) 
        {
            String codigo = fieldError.getCodes()[0];
            String mensagem = messageFonte.getMessage(codigo, fieldError.getArguments(), locale);
            this.erro.put(fieldError.getField(), mensagem);
        }
    }

    private void erros(BindingResult resultado) 
    {
        this.erro = new HashMap<>();
        for(FieldError fieldError : resultado.getFieldErrors()) 
        {
            this.erro.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}