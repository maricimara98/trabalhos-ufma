package Principal;

import java.util.Scanner;

public class Ler {
private static Scanner entrada = new Scanner(System.in);
	
	public static int numero(){
		while(!entrada.hasNextInt()){
			System.err.println("Digite um numero");
			entrada.next();
		}
		return entrada.nextInt();
	}
	
	public static String palavra(){
		return entrada.next();
	}
}
