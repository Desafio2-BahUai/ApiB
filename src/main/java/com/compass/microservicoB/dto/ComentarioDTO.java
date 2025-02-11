package com.compass.microservicoB.dto;

import java.io.Serializable;
import java.util.Date;

public class ComentarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String text;
	private Date date;
	private AutorDTO author;
	
	public ComentarioDTO() {
	}

	public ComentarioDTO(String text, Date date, AutorDTO author) {
		super();
		this.text = text;
		this.date = date;
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public AutorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AutorDTO author) {
		this.author = author;
	}
}
