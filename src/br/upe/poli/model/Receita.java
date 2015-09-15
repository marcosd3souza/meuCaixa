package br.upe.poli.model;

import java.util.Date;

import br.upe.poli.commons.Constants;

public class Receita {

	private String nome;
	private Date dtInicio;
	private Constants.TIPO_RECEITA tipoReceita; 
	private Constants.PERIODO periodo;
	private float valor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDtInicio() {
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}
	public Constants.TIPO_RECEITA getTipoReceita() {
		return tipoReceita;
	}
	public void setTipoReceita(Constants.TIPO_RECEITA tipoReceita) {
		this.tipoReceita = tipoReceita;
	}
	public Constants.PERIODO getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Constants.PERIODO periodo) {
		this.periodo = periodo;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
}
