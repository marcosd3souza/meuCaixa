package br.upe.poli.control;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.upe.poli.commons.Constants.PERIODO;
import br.upe.poli.commons.Constants.TIPO_DESPESA;
import br.upe.poli.model.Despesa;
import br.upe.poli.util.DespesaRepositoryUtil;
import br.upe.poli.util.Util;

public class DespesaController {

	private static String SIM = "S";

	public void criarDespesa(Despesa despesa) {

		try {

			if (!existeDespesa(despesa.getNome())) {

				despesa.setId(Util.getNextID(DespesaRepositoryUtil.DESPESA_REPOSITORY, DespesaRepositoryUtil.REGEX_DESPESA_ID));
				DespesaRepositoryUtil.createDespesa(despesa);

			}else{

				System.out.println("\nAtenção já existe despesa cadastrada com o nome: "+despesa.getNome());
				System.out.println("\nDeseja cadastrar mesmo assim ?");
				System.out.println("\nS - Sim\nN - Não");
				
				if (new Scanner(System.in).next().equalsIgnoreCase(SIM)) {
					
					despesa.setId(Util.getNextID(DespesaRepositoryUtil.DESPESA_REPOSITORY, DespesaRepositoryUtil.REGEX_DESPESA_ID));
					DespesaRepositoryUtil.createDespesa(despesa);
				}
			}

		} catch (IOException e) {
			System.out.println("Atenção: Houve um problema ao cadastrar a despesa.");
		}

	}

	private boolean existeDespesa(String nome) throws IOException {
		return DespesaRepositoryUtil.getDespesas().stream().anyMatch(f -> f.getNome().equalsIgnoreCase(nome));
	}

	public List<Despesa> pesquisaPorPeriodo(PERIODO periodo) {

		List<Despesa> despesas = null;
		try {
			
			despesas = DespesaRepositoryUtil.getDespesas().stream().filter(f -> f.getPeriodo().equals(periodo)).collect(Collectors.toList());
			
			if (despesas == null || despesas.size()<= 0) {
			
				System.out.println("\nNenhuma despesa encontrada");
			}
			
		} catch (IOException e) {
			
			System.out.println("\nAtenção: Houve um erro ao tentar buscar as despesas.");
		}
		
		return despesas;

	}

	public List<Despesa> pesquisaPorTipo(TIPO_DESPESA tipo) {

		List<Despesa> despesas = null;
		try {
			
			despesas = DespesaRepositoryUtil.getDespesas().stream().filter(f -> f.getTipo().equals(tipo)).collect(Collectors.toList());
			
			if (despesas == null || despesas.size()<= 0) {
			
				System.out.println("\nNenhuma despesa encontrada");
			}
			
		} catch (IOException e) {
			
			System.out.println("\nAtenção: Houve um erro ao tentar buscar as despesas.");
		}
		
		return despesas;
	}

	public List<Despesa> pesquisaPorValor(int option, float value) {
		
		List<Despesa> despesas = null;
		try {
			
			switch (option) {
			case 1: despesas = DespesaRepositoryUtil.getDespesas().stream().filter(f -> f.getValor() >= value).collect(Collectors.toList());
				break;

			case 2: despesas = DespesaRepositoryUtil.getDespesas().stream().filter(f -> f.getValor() <= value).collect(Collectors.toList());
				break;
			}
			
			if (despesas == null || despesas.size()<= 0) {
			
				System.out.println("\nNenhuma despesa encontrada");
			}
			
		} catch (IOException e) {
			
			System.out.println("\nAtenção: Houve um erro ao tentar buscar as despesas.");
		}
		
		return despesas;

	}

	public List<Despesa> getDespesasPorNome(String nome) {

		List<Despesa> despesas = null;

		try {

			if (nome == null || nome.equalsIgnoreCase("nome") || nome.trim().length() <= 0) {

				System.out.println("\npor favor informe um nome de despesa válido!");
			}else {

				despesas = DespesaRepositoryUtil.getDespesas().stream().filter(f -> f.getNome().equalsIgnoreCase(nome) || f.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());

				if (despesas == null || despesas.size()<=0) {

					System.out.println("\nNenhuma despesa encontrada.");
				}
			}

		} catch (IOException e) {

			System.out.println("\nAtenção: Houve um erro ao buscar as despesas pelo nome.");
		}

		return despesas;
	}

}
