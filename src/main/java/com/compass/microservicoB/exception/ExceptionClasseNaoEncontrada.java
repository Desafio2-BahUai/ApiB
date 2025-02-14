package com.compass.microservicoB.exception;

public class ExceptionClasseNaoEncontrada extends RuntimeException 
{
  public ExceptionClasseNaoEncontrada(String mensagem)
  {
    super(mensagem);
  }
}