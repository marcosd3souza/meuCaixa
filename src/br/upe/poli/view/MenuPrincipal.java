package br.upe.poli.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import br.upe.poli.util.Constants;

public class MenuPrincipal {

	private static Scanner sc;
	public static String login;
	public static String pass;
	
	public static void main(String[] args) {
		
		sc = new Scanner(System.in); 
		System.out.println("#################### Meu Caixa Sistema ####################");
		System.out.println("Informe o login: ");
		login = sc.next();
		
		System.out.println("Informe a senha: ");
		pass = sc.next();
		
		try {
			
			if (isLoginValidated(login, pass)) {
				
//				exibeMenuInicial();
			}else{
				
				System.out.println("Login incorreto");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

	private static boolean isLoginValidated(String login, String pass) throws IOException {
		
		BufferedReader fileBuffer = null;
		boolean isLoginValid = false; 
		String loginFile;
		String passFile;
			
			fileBuffer = new BufferedReader(new FileReader(new File(Constants.URL_REPOSITORY)));
			
			while (fileBuffer.ready()) {
				
				loginFile = fileBuffer.readLine();
				passFile = fileBuffer.readLine();
				
				if (loginFile.contains(login) && passFile.contains(pass)) {
					
				}
			}
			
		fileBuffer.close();
		
		return isLoginValid;
	}

}
