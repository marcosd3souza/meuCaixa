package br.upe.poli.util;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.ConsoleHandler;

import br.upe.poli.commons.Constants;

public class Util {
	
	private static File file = new File(Constants.URL_LOGIN_REPOSITORY);
	private static BufferedWriter writer = null;
	
	public static BufferedWriter getWriterInstance() throws IOException{
		
		if (writer == null) {
			
			writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			return writer;
		}else{
			
			return writer;
		}
		
	}
	
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
	
}
