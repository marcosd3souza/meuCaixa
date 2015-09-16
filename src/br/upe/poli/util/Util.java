package br.upe.poli.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Util {
	
	public static void exibeMenuDespesaPorPeriodo(){
		
		exibeMenuPorPeriodo(true);
	}
	
	public static void exibeMenuReceitaPorPeriodo(){
		
		exibeMenuPorPeriodo(false);
	}
	
	private static void exibeMenuPorPeriodo(Boolean isDespesa){
		
		System.out.println("\n1 - diária");
		System.out.println("\n2 - semanal");
		System.out.println("\n3 - mensal");
		System.out.println("\n4 - trimestral");
		System.out.println("\n5 - semestral");
		System.out.println("\n6 - anual");
		
		if (isDespesa) {
	
			System.out.println("\n7 - outros");
		}else{
			
			System.out.println("\n7 - não se aplica");
		}
		
	}

	public static int getNextID(String repositorio, Pattern regex) throws IOException {
		
		Matcher match;
		int id = 0, current = 0;

		for (String line : Files.lines(Paths.get(repositorio)).collect(Collectors.toList())) {
			match = regex.matcher(line);

			if (match.find()) {

				current = Integer.parseInt(match.group().replace("id: ", "").trim());
				id = current > id ? current : id;
			}
		}
		
		return ++id;
	}

	public static Date converteParaData(String date) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		try {
			
			return dateFormat.parse(date);
		} catch (ParseException e) {
			
			System.out.println("Data informada inválida!");
		}
		
		return null;
	}
	
}
