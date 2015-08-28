package br.upe.poli.util;

import java.util.regex.Pattern;

public class Constants {
	
	public static final String URL_REPOSITORY = "C:\\Users\\Marcos\\Desktop\\POLI-UPE\\LPOO\\Projeto\\MeuCaixa\\Files\\Repository.txt";
	public static final Pattern REGEX_PASSWORD = Pattern.compile("[^senha: ]+[\\w+\\d+]*", Pattern.MULTILINE | Pattern.DOTALL);
	public static final Pattern REGEX_LOGIN = Pattern.compile("[^login: ]+[\\w+\\d+]*", Pattern.MULTILINE | Pattern.DOTALL);
	

}
