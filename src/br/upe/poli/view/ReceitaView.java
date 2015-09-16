package br.upe.poli.view;

import java.util.List;
import java.util.Scanner;

import br.upe.poli.commons.Constants;
import br.upe.poli.control.ReceitaController;
import br.upe.poli.model.Receita;
import br.upe.poli.util.Util;

public class ReceitaView{

	static ReceitaController controller = new ReceitaController();

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

		Receita receita = new Receita(); 

		System.out.println("\nPor favor, informe o nome da receita: ");
		receita.setNome(sc.next());

		while(receita.getDtInicio() == null || receita.getDtEncerramento() == null){

			System.out.println("\nPor favor, informe a data de início da receita: ");
			receita.setDtInicio(Util.converteParaData(sc.next()));
			System.out.println("\nPor favor, informe a data de encerramento: ");
			receita.setDtEncerramento(Util.converteParaData(sc.next()));

			System.out.println("\nObs: Por favor informe a data no formato: dd/mm/aaaa ");
		}

		System.out.println("\nPor favor, informe o tipo da receita: ");
		exibeMenuReceitaPorTipo();
		receita.setTipoReceita(Constants.TIPO_RECEITA.valueOf(sc.nextInt()));
		System.out.println("\nPor favor, informe a periodicidade da receita: ");
		Util.exibeMenuReceitaPorPeriodo();
		receita.setPeriodo(Constants.PERIODO.valueOf(sc.nextInt()));
		System.out.println("\nPor favor, informe o valor da receita: ");
		receita.setValor(sc.nextFloat());

		controller.criarReceita(receita);

	}

	private static void exibeMenuReceitaPorTipo() {
		System.out.println("\n1 - salário");
		System.out.println("\n2 - mesada");
		System.out.println("\n3 - lucros");
		System.out.println("\n4 - doações");
		System.out.println("\n5 - premios");
		System.out.println("\n6 - outro");
	}

	private static void pesquisarReceitas(Scanner sc) {

		listarReceitas(controller.getReceitas());
	}

	private static void listarReceitas(List<Receita> receitas) {
		
		if (receitas != null) {

			for (int i = 0; i < receitas.size(); i++) {
				System.out.println("\n"+receitas.get(i));
			}
		}		
	}

	private static void alteraReceita(Scanner sc) {
		// TODO 

	}

	private static void excluirReceita(Scanner sc){
		//TODO
	}

}
