package br.upe.poli.control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

import br.upe.poli.model.Despesa;
import br.upe.poli.model.Receita;
import br.upe.poli.util.DespesaRepositoryUtil;
import br.upe.poli.util.ReceitaRepositoryUtil;
import br.upe.poli.util.Util;

public class ReceitaController {

	private static String SIM = "S";

	public void criarReceita(Receita receita) {

		try {

			if (!existeReceita(receita.getNome())) {

				receita.setId(Util.getNextID(ReceitaRepositoryUtil.RECEITA_REPOSITORY, ReceitaRepositoryUtil.REGEX_RECEITA_ID));
				ReceitaRepositoryUtil.createReceita(receita);

			}else{

				System.out.println("\nAtenção já existe receita cadastrada com o nome: "+receita.getNome());
				System.out.println("\nDeseja cadastrar mesmo assim ?");
				System.out.println("\nS - Sim\nN - Não");

				if (new Scanner(System.in).next().equalsIgnoreCase(SIM)) {

					receita.setId(Util.getNextID(ReceitaRepositoryUtil.RECEITA_REPOSITORY, ReceitaRepositoryUtil.REGEX_RECEITA_ID));
					ReceitaRepositoryUtil.createReceita(receita);
				}
			}

		} catch (IOException e) {
			System.out.println("Atenção: Houve um problema ao cadastrar a despesa.");
		}
	}

	private boolean existeReceita(String nome) {
		return getReceitas().stream().anyMatch(f -> f.getNome().equalsIgnoreCase(nome));
	}

	public List<Receita> getReceitas(){

		List<Receita> receitas = new ArrayList();

		try{

			String file = new String(Files.readAllBytes(Paths.get(ReceitaRepositoryUtil.RECEITA_REPOSITORY)), StandardCharsets.UTF_8);
			Matcher match = ReceitaRepositoryUtil.REGEX_RECEITA.matcher(file);
			String matched;

			while (match.find()) {
				matched = match.group();

				receitas.add(new Receita(
						ReceitaRepositoryUtil.getReceita(matched).get("id").toString().replaceFirst("id: ", "").trim(),
						ReceitaRepositoryUtil.getReceita(matched).get("nome").toString().replaceFirst("nome: ", "").trim(),
						ReceitaRepositoryUtil.getReceita(matched).get("data_inicio").toString().replaceFirst("data_inicio: ", "").trim(),
						ReceitaRepositoryUtil.getReceita(matched).get("data_encerramento").toString().replaceFirst("data_encerramento: ", "").trim(),
						ReceitaRepositoryUtil.getReceita(matched).get("tipo").toString().replaceFirst("tipo: ", "").trim(),
						ReceitaRepositoryUtil.getReceita(matched).get("periodo").toString().replaceFirst("periodo: ", "").trim(),
						ReceitaRepositoryUtil.getReceita(matched).get("valor").toString().replaceFirst("valor: ", "").trim()
						));
			}

		}catch(IOException e){

			System.out.println("\nAtenção: houve um erro ao tentar ler o arquivo.");	
		}

		return receitas;
	}

}
