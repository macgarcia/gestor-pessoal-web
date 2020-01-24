package com.macgarcia.gpweb.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Renda implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descr;
	private Date data;
	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public String getDataFormatada() {
		return new SimpleDateFormat("MM/yyyy").format(this.data);
	}
	
	public String getValorFormatado() {
		return String.format("%.2f", this.valor);
	}
}
