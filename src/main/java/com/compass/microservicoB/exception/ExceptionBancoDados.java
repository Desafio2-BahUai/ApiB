package com.compass.microservicoB.exception;

public class ExceptionBancoDados extends RuntimeException 
{
  public ExceptionBancoDados(String mensagem) 
  {
    super(mensagem);
  }
}