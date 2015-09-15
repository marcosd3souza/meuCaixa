package br.upe.poli.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.upe.poli.commons.Constants;
import br.upe.poli.model.Despesa;

public class DespesaRepositoryUtil {

	private static final String DESPESA_REPOSITORY = "C:\\Users\\Marcos\\Desktop\\POLI-UPE\\LPOO\\Projeto\\MeuCaixa\\Files\\DespesaRepository.txt";
	private static final Pattern REGEX_DESPESA = Pattern.compile("\\{(\\w|\\d|\\:|\\;|\\s)+\\}", Pattern.MULTILINE | Pattern.DOTALL);

	public static final Pattern REGEX_DESPESA_ID = Pattern.compile("id: (\\d)+");
	private static final Pattern REGEX_DESPESA_NOME = Pattern.compile("nome: (\\w|\\d|\\s)+");
	private static final Pattern REGEX_DESPESA_DIA = Pattern.compile("dia: (\\d)+");
	private static final Pattern REGEX_DESPESA_MES = Pattern.compile("mes: (\\d)+");
	private static final Pattern REGEX_DESPESA_ANO = Pattern.compile("ano: (\\d)+");
	private static final Pattern REGEX_DESPESA_TIPO = Pattern.compile("tipo: (\\w)+");
	private static final Pattern REGEX_DESPESA_VALOR = Pattern.compile("valor: (\\d)+");
	private static final Pattern REGEX_DESPESA_TOTAL_PARCELAS = Pattern.compile("total_parcelas: (\\d)+");
	private static final Pattern REGEX_DESPESA_PERIODO = Pattern.compile("periodo: (\\w)+");

	public static void removeFromFile(Despesa despesa) throws IOException {
		String file = new String(Files.readAllBytes(Paths.get(DESPESA_REPOSITORY)), StandardCharsets.UTF_8);
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DESPESA_REPOSITORY)));
		Matcher match = REGEX_DESPESA.matcher(file);
		String despesaMatched;

		while (match.find()) {
			despesaMatched = match.group();

			if (!despesa.equals(despesaMatched)) {

				writer.append(despesaMatched+"\n");
			}
		}

		writer.close();
	}

	public static List<Despesa> getDespesas() throws IOException{

		List<Despesa> despesas = new ArrayList();
		String file = new String(Files.readAllBytes(Paths.get(DESPESA_REPOSITORY)), StandardCharsets.UTF_8);
		Matcher match = REGEX_DESPESA.matcher(file);
		String matched;

		while (match.find()) {
			matched = match.group();

			despesas.add(new Despesa(
					getDespesa(matched).get("id").toString().replaceFirst("id: ", "").trim(),
					getDespesa(matched).get("nome").toString().replaceFirst("nome: ", "").trim(),
					getDespesa(matched).get("dia").toString().replaceFirst("dia: ", "").trim(),
					getDespesa(matched).get("mes").toString().replaceFirst("mes: ", "").trim(),
					getDespesa(matched).get("ano").toString().replaceFirst("ano: ", "").trim(),
					getDespesa(matched).get("tipo").toString().replaceFirst("tipo: ", "").trim(),
					getDespesa(matched).get("totalParcelas").toString().replaceFirst("total_parcelas: ", "").trim(),
					getDespesa(matched).get("valor").toString().replaceFirst("valor: ", "").trim(),
					getDespesa(matched).get("periodo").toString().replaceFirst("periodo: ", "").trim()
					));
		}

		return despesas;

	}

	private static HashMap<String, Object> getDespesa(String matched){

		HashMap<String, Object> retorno = new HashMap<String, Object>(); 

		Matcher matchId = REGEX_DESPESA_ID.matcher(matched);
		Matcher matchNome = REGEX_DESPESA_NOME.matcher(matched);
		Matcher matchDia = REGEX_DESPESA_DIA.matcher(matched);
		Matcher matchMes = REGEX_DESPESA_MES.matcher(matched);
		Matcher matchAno = REGEX_DESPESA_ANO.matcher(matched);
		Matcher matchTipo = REGEX_DESPESA_TIPO.matcher(matched);
		Matcher matchTotalParcelas = REGEX_DESPESA_TOTAL_PARCELAS.matcher(matched);
		Matcher matchValor = REGEX_DESPESA_VALOR.matcher(matched);
		Matcher matchPeriodo = REGEX_DESPESA_PERIODO.matcher(matched);

		retorno.put("id", matchId.find() ? matchId.group() : null );
		retorno.put("nome", matchNome.find() ? matchNome.group() : null);
		retorno.put("dia", matchDia.find() ? matchDia.group() : null );
		retorno.put("mes", matchMes.find() ? matchMes.group() : null );
		retorno.put("ano", matchAno.find() ? matchAno.group() : null );
		retorno.put("tipo", matchTipo.find() ? matchTipo.group() : null );
		retorno.put("totalParcelas", matchTotalParcelas.find() ? matchTotalParcelas.group() : null );
		retorno.put("valor", matchValor.find() ? matchValor.group() : null );
		retorno.put("periodo", matchPeriodo.find() ? matchPeriodo.group() : null );

		return retorno;

	}

}

