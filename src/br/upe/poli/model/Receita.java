package br.upe.poli.model;

import java.util.Date;

import br.upe.poli.commons.Constants;
import br.upe.poli.util.Util;

public class Receita {

	private int id;
	private String nome;
	private Date dtInicio;
	private Date dtEncerramento;	
	private Constants.TIPO_RECEITA tipoReceita; 
	private Constants.PERIODO periodo;
	private float valor;
	
	public Receita(String id, String nome, String dtInicio, String dtEncerramento,
			String tipo, String periodo, String valor) {
		
		this.id = Integer.parseInt(id);
		this.nome = nome;
		this.dtInicio = Util.converteParaData(dtInicio);
		this.dtEncerramento = Util.converteParaData(dtEncerramento);
		this.tipoReceita = Constants.TIPO_RECEITA.valueOf(tipo);
		this.periodo = Constants.PERIODO.valueOf(periodo);
		this.valor = Float.parseFloat(valor);
	}
	public Receita() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	public Date getDtEncerramento() {
		return dtEncerramento;
	}
	public void setDtEncerramento(Date dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
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
	
	public String toString(){
		return " Nome da receita: "+nome+" tipo: "+tipoReceita+" valor: "+valor;
	} 
	
}
