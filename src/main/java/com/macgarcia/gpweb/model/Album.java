package com.macgarcia.gpweb.model;

import java.io.Serializable;

public class Album implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String titulo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
