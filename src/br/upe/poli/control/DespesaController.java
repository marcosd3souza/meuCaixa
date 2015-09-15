package br.upe.poli.control;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.upe.poli.commons.Constants.PERIODO;
import br.upe.poli.commons.Constants.TIPO_DESPESA;
import br.upe.poli.model.Despesa;
import br.upe.poli.util.DespesaRepositoryUtil;

public class DespesaController {

	public void criarDespesa(Despesa despesa) {
		// TODO
	}

	public List<Despesa> pesquisaPorPeriodo(PERIODO periodo) {

		List<Despesa> despesas = null;
		try {
			
			despesas = DespesaRepositoryUtil.getDespesas().stream().filter(f -> f.getPeriod().equals(periodo)).collect(Collectors.toList());
			
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
