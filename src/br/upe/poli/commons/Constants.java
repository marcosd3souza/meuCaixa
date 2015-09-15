package br.upe.poli.commons;

import java.util.regex.Pattern;

import br.upe.poli.commons.Constants.PERIODO;
import br.upe.poli.commons.Constants.TIPO_DESPESA;

public class Constants {
	
	public enum PERIODO {
		diaria(1), semanal(2), mensal(3), trimestral(4), semestral(5), anual(6), outro(7), nao_se_aplica(8);
		
		private int periodo;
		
		PERIODO(int p){
			periodo = p;
		}
		
		int getPeriodo(){
			
			return periodo;
		}

		public static PERIODO valueOf(int opt) {
			return PERIODO.values()[--opt];
		}
	}
	
	public enum TIPO_DESPESA{
		
		
		saque(1), conta_energia(2), conta_agua(3), conta_telefone_internet(4),
		condominio(5), aluguel(6), prest_carro(7), prest_imovel(8), imposto(9), 
		faculdade(10), alimentacao(11), combustivel(12), academia(13), seguro(14),
		compras(15), lazer(16), outros(17);
		
		
		private int tipo;
		
		TIPO_DESPESA(int t){
			tipo = t;
		}
		
		int getTipo(){
			
			return tipo;
		}
		
		public static TIPO_DESPESA valueOf(int opt){
			return TIPO_DESPESA.values()[--opt];
		}
		
	}
	
	public enum TIPO_RECEITA{
		
		salario(1), mesada(2), lucros(3), doacoes(4), premios(5), outros(6);
		
		private int tipo;
		
		TIPO_RECEITA(int t){
			tipo = t;
		}
		
		public static TIPO_RECEITA valueOf(int opt){
			return TIPO_RECEITA.values()[--opt];
		}
	}
	
	public static final String URL_LOGIN_REPOSITORY = "C:\\Users\\Marcos\\Desktop\\POLI-UPE\\LPOO\\Projeto\\MeuCaixa\\Files\\Repository.txt";
	public static final Pattern REGEX_LOGIN = Pattern.compile("[login: ]+(\\w|\\d)+\n[senha: ]+(\\w|\\d)+", Pattern.MULTILINE | Pattern.DOTALL);
	public static final Pattern REGEX_CREDENTIALS = Pattern.compile("[^login: ](\\w|\\d)+[^senha: ]+");
}
