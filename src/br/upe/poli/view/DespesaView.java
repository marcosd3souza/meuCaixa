package br.upe.poli.view;

import java.util.List;
import java.util.Scanner;

import br.upe.poli.commons.Constants;
import br.upe.poli.commons.Constants.PERIODO;
import br.upe.poli.control.DespesaController;
import br.upe.poli.exception.DateOutOfBoundsException;
import br.upe.poli.model.Despesa;
import br.upe.poli.util.Util;

public class DespesaView{

	static DespesaController controller = new DespesaController();

	protected static void menuDespesa(Scanner sc) {
		
		System.out.println("\nEscolha uma opção");
		System.out.println("\n1 - Cadastrar nova despesa");
		System.out.println("\n2 - Listar despesas por período");
		System.out.println("\n3 - Listar despesas por tipo");
		System.out.println("\n4 - Listar despesas por valor");
		System.out.println("\n5 - excluir despesa");

		switch (sc.nextInt()) {
		case 1:		
			cadastraDespesa(sc);
			break;

		case 2:
			pesquisarDespesaPorPeriodo(sc);
			break;

		case 3:
			pesquisarDespesaPorTipo(sc);
			break;

		case 4:
			pesquisarDespesaPorValor(sc);
			break;
			
		case 5:
			excluiDespesa(sc);
			break;
		
		}

	}

	private static void cadastraDespesa(Scanner sc){
		
		Despesa expense = new Despesa();
		
		try{
			
		
		System.out.println("\nInforme o nome da despesa: ");
		expense.setNome(sc.next());
		
		System.out.println("\nInforme o dia da despesa: ");
		expense.setDia(sc.nextInt());
		
		System.out.println("\nInforme o mês da despesa: ");
		expense.setMes(sc.nextInt());
		
		System.out.println("\nInforme o ano da despesa: ");
		expense.setAno(sc.nextInt());
		
		System.out.println("\nInforme o tipo da despesa: ");
		exibeMenuDespesaPorTipo();
		expense.setTipo(Constants.TIPO_DESPESA.valueOf(sc.nextInt()));
		
		System.out.println("\nInforme o valor da despesa: ");
		expense.setValor(sc.nextFloat());
		
		System.out.println("\nInforme o número de parcelas da despesa: ");
		expense.setTotalParcela(sc.nextInt());
		
		System.out.println("\nInforme a periodicidade da despesa: ");
		Util.exibeMenuDespesaPorPeriodo();
		expense.setPeriod(PERIODO.valueOf(sc.nextInt()));
		
		controller.criarDespesa(expense);
		
		System.out.println("\nDespesa cadastrada com sucesso!");
		
		}catch(DateOutOfBoundsException e){
			
			System.out.println(e.getMessage());
		}
		
	}

	private static void exibeMenuDespesaPorTipo() {
		System.out.println("\n1 - saque");
		System.out.println("\n2 - conta de energia");
		System.out.println("\n3 - conta_agua");
		System.out.println("\n4 - conta de telefone/internet");
		System.out.println("\n5 - condominio");
		System.out.println("\n6 - aluguel");
		System.out.println("\n7 - prestacao do carro");
		System.out.println("\n8 - prestacao do imovel");
		System.out.println("\n9 - imposto");
		System.out.println("\n10 - faculdade");
		System.out.println("\n11 - alimentacao");
		System.out.println("\n12 - combustivel");
		System.out.println("\n13 - academia");
		System.out.println("\n14 - seguro");
		System.out.println("\n15 - compras");
		System.out.println("\n16 - lazer");
		System.out.println("\n17 - outros");
	}


	private static void pesquisarDespesaPorPeriodo(Scanner sc) {
		
		System.out.println("\nPor favor escolha uma opção de período abaixo: ");
		Util.exibeMenuDespesaPorPeriodo();
		
		listarDespesas(controller.pesquisaPorPeriodo(Constants.PERIODO.valueOf(sc.nextInt())));
	}
		
	private static void pesquisarDespesaPorTipo(Scanner sc) {
	
		System.out.println("\nPor favor escolha uma opção de tipo abaixo: ");
		exibeMenuDespesaPorTipo();
		
		listarDespesas(controller.pesquisaPorTipo(Constants.TIPO_DESPESA.valueOf(sc.nextInt())));
	}
	
	private static void pesquisarDespesaPorValor(Scanner sc) {
		
		System.out.println("\nPor favor escolha uma opção");
		System.out.println("\n1- Maior que (>=) ");
		System.out.println("\n2- Menor que (<=) ");
		int option = sc.nextInt();
		
		System.out.println("\nPor favor informe um valor: ");
		float value = sc.nextFloat();
		
		listarDespesas(controller.pesquisaPorValor(option, value));
	}
	
	private static void excluiDespesa(Scanner sc) {
		System.out.println("\nPor favor informe o nome da despesa");

		List<Despesa> despesas = controller.getDespesasPorNome(sc.next());

		if (despesas != null) {
			
			listarDespesaPorNome(despesas);
			despesas.get(sc.nextInt()).delete();
		}
		
	}

	private static void listarDespesaPorNome(List<Despesa> despesas) {

		System.out.println("\nPor favor escolha uma despesa");
		for (int i = 0; i < despesas.size(); i++) {
			System.out.println("\n"+ i +" - "+despesas.get(i));
		}
	}
	
	private static void listarDespesas(List<Despesa> despesas){
		
		if (despesas != null) {
			
			for (int i = 0; i < despesas.size(); i++) {
				System.out.println("\n"+despesas.get(i));
			}
		}
	}

	
}
