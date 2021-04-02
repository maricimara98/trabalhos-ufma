package principal;

import java.util.Scanner;

/*Classe para tratamento de entradas */

public class Ler {
	private static Scanner entrada = new Scanner(System.in);
	
	public static int digito(){
		while(!entrada.hasNextInt()){
			System.err.println("Digite um numero");
			entrada.next();
		}
		return entrada.nextInt();
	}
	
	public static String linha(){
		return entrada.next();
	}
}

