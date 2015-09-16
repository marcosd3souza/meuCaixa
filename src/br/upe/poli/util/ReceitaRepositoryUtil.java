package br.upe.poli.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.upe.poli.model.Receita;

public class ReceitaRepositoryUtil {

	public static final String RECEITA_REPOSITORY = System.getProperty("user.dir") + "\\Files\\ReceitaRepository.txt";
	public static final Pattern REGEX_RECEITA = Pattern.compile("\\{(\\w|\\d|\\:|\\;|\\s|\\.)+\\}", Pattern.MULTILINE | Pattern.DOTALL);
	
	public static final Pattern REGEX_RECEITA_ID = Pattern.compile("id: (\\d)+");
	private static final Pattern REGEX_RECEITA_NOME = Pattern.compile("nome: (\\w|\\d|\\s)+");
	private static final Pattern REGEX_RECEITA_DATA_INICIO = Pattern.compile("data_inicio: (\\d|\\)+");
	private static final Pattern REGEX_RECEITA_DATA_ENCERRAMENTO = Pattern.compile("data_encerramento: (\\d|\\)+");
	private static final Pattern REGEX_RECEITA_TIPO = Pattern.compile("tipo: (\\w)+");
	private static final Pattern REGEX_RECEITA_PERIODO = Pattern.compile("periodo: (\\w)+");
	private static final Pattern REGEX_RECEITA_VALOR = Pattern.compile("valor: (\\d|\\.)+");
	
	public static void createReceita(Receita receita) throws IOException {

		String file = new String(Files.readAllBytes(Paths.get(RECEITA_REPOSITORY)), StandardCharsets.UTF_8);
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(RECEITA_REPOSITORY)));

			StringBuilder builder = new StringBuilder();
			
			builder.append(file).append("\n")
			.append("{id: "+ receita.getId()+"; ")
			.append("nome: "+receita.getNome()+"; ")
			.append("data_inicio: "+receita.getDtInicio()+"; ")
			.append("data_encerramento: "+receita.getDtEncerramento()+"; ")
			.append("tipo: "+receita.getTipoReceita()+"; ")
			.append("periodo"+receita.getPeriodo() +"; " )
			.append("valor: "+receita.getValor()+"}");
			
			writer.append(builder.toString());
			
			writer.flush();
			writer.close();
	}

	public static HashMap<String, Object> getReceita(String matched) {
		
		HashMap<String, Object> retorno = new HashMap<String, Object>(); 

		Matcher matchId = REGEX_RECEITA_ID.matcher(matched);
		Matcher matchNome = REGEX_RECEITA_NOME.matcher(matched);
		Matcher matchDtInicio = REGEX_RECEITA_DATA_INICIO.matcher(matched);
		Matcher matchDtEncerramento = REGEX_RECEITA_DATA_ENCERRAMENTO.matcher(matched);
		Matcher matchTipo = REGEX_RECEITA_TIPO.matcher(matched);
		Matcher matchPeriodo = REGEX_RECEITA_PERIODO.matcher(matched);
		Matcher matchValor = REGEX_RECEITA_VALOR.matcher(matched);

		retorno.put("id", matchId.find() ? matchId.group() : null );
		retorno.put("nome", matchNome.find() ? matchNome.group() : null);
		retorno.put("data_inicio", matchDtInicio.find() ? matchDtInicio.group() : null );
		retorno.put("data_encerramento", matchDtEncerramento.find() ? matchDtEncerramento.group() : null );
		retorno.put("tipo", matchTipo.find() ? matchTipo.group() : null );
		retorno.put("periodo", matchPeriodo.find() ? matchPeriodo.group() : null );
		retorno.put("valor", matchValor.find() ? matchValor.group() : null );

		return retorno;
	}
	
	

}
