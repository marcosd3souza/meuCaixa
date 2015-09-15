package br.upe.poli.model;

import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.upe.poli.commons.Constants;
import br.upe.poli.commons.Constants.TIPO_DESPESA;
import br.upe.poli.exception.DateOutOfBoundsException;
import br.upe.poli.util.DespesaRepositoryUtil;

public class Despesa {
	
	private int id;
	private String nome;
	private int dia;
	private int mes;
	private int ano;	
	private TIPO_DESPESA type;	
	private float valor;
	private int totalParcela;
	private Constants.PERIODO period;
	
	public Despesa(String id, String nome, String dia, String mes, String ano,
			String tipo, String totalParcelas, String valor, String periodo) {
		
		this.id = Integer.parseInt(id);
		this.nome = nome;
		this.dia = Integer.parseInt(dia);
		this.mes = Integer.parseInt(mes);
		this.ano = Integer.parseInt(ano);
		this.type = Constants.TIPO_DESPESA.valueOf(tipo);
		this.totalParcela = Integer.parseInt(totalParcelas);
		this.valor = Float.parseFloat(valor);
		this.period = Constants.PERIODO.valueOf(periodo);
		
	}
	
	public Despesa() {
	}
	
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
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) throws DateOutOfBoundsException {

		if (dia>0 && dia<=31) {
			
			this.dia = dia;
		}else{
			throw new DateOutOfBoundsException("Dia inválido, por favor informe uma data entre 1 e 31");
		}
		
	}
	public int getMes() {
		return mes;
	}
	
	public void setMes(int mes) throws DateOutOfBoundsException {
		
		if (mes>0 && mes<=12) {
			
			this.mes = mes;
		}else{
			
			throw new DateOutOfBoundsException("mês inválido, por favor informe um mês entre 1 e 12");
		}
			
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) throws DateOutOfBoundsException {
		
		int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		
		if (ano >= anoAtual ) {
			
			this.ano = ano;
		}else{
			
			throw new DateOutOfBoundsException("ano inválido, por favor informe um ano a partir de "+anoAtual);
		}
	}
	public TIPO_DESPESA getTipo() {
		return type;
	}
	public void setTipo(TIPO_DESPESA type) {
		this.type = type;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getTotalParcela() {
		return totalParcela;
	}
	public void setTotalParcela(int totalParcela) {
		this.totalParcela = totalParcela;
	}
	public Constants.PERIODO getPeriod() {
		return period;
	}
	public void setPeriod(Constants.PERIODO period) {
		this.period = period;
	}
	
	public String toString(){
		return " Nome da despesa: "+nome+" tipo: "+type+" valor: "+valor;
	} 
	
	public void delete(){
		try {
			DespesaRepositoryUtil.removeFromFile(this);
		} catch (IOException e) {
			System.out.println("Atenção: Houve um erro ao tentar excluir a despesa."
					         + "\nMotivo: Problemas na leitura de arquivo.");
		}
	}
	
	public boolean equals(String despesaMatched){
		
		Matcher mat = DespesaRepositoryUtil.REGEX_DESPESA_ID.matcher(despesaMatched);
		boolean retorno = false;
		
		if (mat.find()) {
			
			retorno =  id == Integer.parseInt(mat.group().toString().replaceFirst("id: ", "").trim()); 
		}
		
		return retorno;
	}

}
