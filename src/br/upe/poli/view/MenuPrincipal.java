package br.upe.poli.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;

import br.upe.poli.commons.Constants;
import br.upe.poli.control.DespesaController;
import br.upe.poli.model.Login;

public class MenuPrincipal {

	private static Scanner sc = new Scanner(System.in);	
	private static BufferedWriter writer;

	public static void main(String[] args) {
		int option = -1;
		try{

			while (option != 0) {

				System.out.println("Selecione uma Opção");
				System.out.println("\n1 - Cadastrar-se");
				System.out.println("\n2 - Realizar Login");
				System.out.println("\n0 - sair");


				option = sc.nextInt();

				switch (option) {
				case 1:
					createLogin();
					break;

				case 2:
					login();
					break;
				}
			}

		}catch(InputMismatchException e){

			System.out.println("Opcao invalida, por favor escolha outra opcao");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void createLogin() throws IOException {
		
		Login login = new Login();
	
		writer = new BufferedWriter(new FileWriter(new File(Constants.URL_LOGIN_REPOSITORY)));

		System.out.println("#################### Meu Caixa Sistema ####################");
		System.out.println("Informe o login: ");
		login.setUserName(sc.next());

		System.out.println("Informe a senha: ");
		login.setPassword(sc.next());

		writer.write(login.toString());

		writer.close();
		System.out.println("Cadastro efetuado com sucesso!");

		return;
	}

	private static void login() throws IOException{

		Login login = new Login();
		
		System.out.println("#################### Meu Caixa Sistema ####################");
		System.out.println("Informe o login: ");
		login.setUserName(sc.next());

		System.out.println("Informe a senha: ");
		login.setPassword(sc.next());

		try {

			if (isLoginValidated(login)) {

				exibeMenuInicial();
			}else{

				System.out.println("Login incorreto");
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static boolean isLoginValidated(Login login) throws IOException {

		boolean isLoginValid = false; 

		String file = new String (Files.readAllBytes(Paths.get(Constants.URL_LOGIN_REPOSITORY)), StandardCharsets.UTF_8);

		Matcher	matcherLogin = Constants.REGEX_LOGIN.matcher(file);
		Matcher matcher;

		while (matcherLogin.find()) {

			matcher = Constants.REGEX_CREDENTIALS.matcher(matcherLogin.group());

			if (matcher.find() && matcher.group().trim().equalsIgnoreCase(login.getUserName())) {					

				if (matcher.find() && matcher.group().trim().equalsIgnoreCase(login.getPassword())) {

					isLoginValid = true;
				}
			}

		}			

		return isLoginValid;
	}
	
	private static void exibeMenuInicial() throws IOException {
		
		int option = 0;
		
		while(option != 4){
		
		System.out.println("#################### Meu Caixa Sistema ####################");
		System.out.println("\n####################  Menu Principal   ####################");
		System.out.println("\n1 - Cadastro de Despesa");
		System.out.println("\n2 - Cadastro de Receita");
		System.out.println("\n3 - Relatorio");
		System.out.println("\n4 - Sair");
		
		 option = sc.nextInt();
		
		switch (option) {
		case 1:
			DespesaView.menuDespesa(sc);
			break;
			
		case 2:
			ReceitaView.menuReceita(sc);
			break;
			
		case 3:
			menuRelatorio();
			break;
		}
		
		}
	}
	
	private static void menuRelatorio() {
		//TODO
	}


}
