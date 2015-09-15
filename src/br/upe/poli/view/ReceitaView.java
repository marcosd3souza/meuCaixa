package br.upe.poli.view;

import java.util.Scanner;

import br.upe.poli.util.Util;

public class ReceitaView{

	public static void menuReceita(Scanner sc) {
		
		System.out.println("\nMenu Receita");
		System.out.println("\nPor favor escolha uma opção: ");
		System.out.println("\n1 - Cadastrar Nova Despesa");
		System.out.println("\n2 - Listar Receitas");
		System.out.println("\n3 - Alterar receita");
		System.out.println("\n4 - Excluir Receita");
		
		int option = sc.nextInt();
		
		switch (option) {
		case 1: cadastraReceita(sc);
			break;

		case 2: pesquisarReceitas(sc);
			break;
			
		case 3: alteraReceita(sc);
			break;
			
		case 4: excluirReceita(sc);
			break;
		}
	}

	private static void cadastraReceita(Scanner sc) {
		System.out.println("\nPor favor, informe o nome da receita: ");
		System.out.println("\nPor favor, informe a data de início da receita: ");
		System.out.println("\nPor favor, informe a data de encerramento: ");
		System.out.println("\nPor favor, informe o tipo da receita: ");
		System.out.println("\nPor favor, informe a periodicidade da receita: ");
		Util.exibeMenuReceitaPorPeriodo();
		System.out.println("\nPor favor, informe o valor da receita: ");
		
		//TODO
		
	}
	
	private static void pesquisarReceitas(Scanner sc) {
		// TODO 
		
	}
	
	private static void alteraReceita(Scanner sc) {
		// TODO 
		
	}
	
	private static void excluirReceita(Scanner sc){
		//TODO
	}

}
