/*	Atividade Pratica I - 2020.1
 *	Universidade Federal do Maranhao - UFMA
 *	Departamento de Informatica - DEINF
 *	Estrutura de Dados II - EDII
 *	Discente: Maria Jucimara Pereira Ferreira
 */

package principal;

import algoritmosDeOrdenacao.*;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;


public class Main {

	public static void main(String[] args) {
		
		int opcao;
		int nVertices = 0;
		List<Edge> arestas; 

		Menus.menuElementos();
		opcao = Ler.digito();

		if (opcao == 1) {
			nVertices = 7;
		}  else if(opcao == 2) {
			nVertices = 100;
		}  else if(opcao == 3) {
			nVertices = 1000;
		}  else if(opcao == 4) {
			nVertices = 10000;
		}  else if(opcao == 5) {
			nVertices = 100000;
		} else if(opcao == 0){
			Menus.msg_Exit();
			System.exit(opcao);
		} else {
			System.out.println("opcao invalida");
		}      
	
		arestas = readCSVfile("../TRABALHO/src/teste_vertices/"+nVertices+"_vertices.csv");
		
		Graph graph = new Graph(arestas); // inicializa o grafo com edges
		List<Edge> mst= graph.Kruskal(arestas);

		
		for(Edge e : mst) {
			System.out.println(e.v+", " + e.peso+", " + e.w);	// printar na tela os valores		
		}		
		
		Menus.menu();
		int opcaoMetodo;
		opcaoMetodo = Ler.digito();

		if(opcaoMetodo == 1){
			//chama o metodo da classe QuickSort
			QuickSort.sorteio(mst);	
		} else if(opcaoMetodo == 2){
			//chama o metodo da classe MergeSort
			MergeSort.sorteio(mst);	
		} else if(opcaoMetodo == 3){
			//chama o metodo da classe InsertionSort
			InsertionSort.sorteio(mst);	
		} else if(opcaoMetodo == 4){
			//chama o metodo da classe Shellsort
			ShellSort.sorteio(mst);			
		} else if(opcaoMetodo == 5){
			//chama o metodo da classe HeapSort
			HeapSort.sorteio(mst);		
		} else if(opcaoMetodo == 6){
			//chama o metodo da classe QuickSort Parcial
			QuickSortInsertP.sorteio(mst);	
		} else if(opcaoMetodo == 7){
			//chama o metódo da classe QuickSort Final	
			QuickSortFinal.sorteio(mst);	
		} else if(opcaoMetodo == 0){
			// opcao para fechar o programa
			Menus.msg_Exit();
		} else if(opcaoMetodo < 0 || opcaoMetodo > 7) {
			// caso nao seja digitado nenhuma das opcoes acima
			System.out.println("opcao invalida");
		}
	
	}
		
	public static List<Edge> readCSVfile(String path){
		String Str;
		List<Edge> tableLine = new ArrayList<Edge>();

		//A estrutura try-catch he usada pois o objeto BufferedWriter exige que as
		//excessoes sejam tratadas
		try {
		//Criacao de um buffer para a ler de uma stream
		BufferedReader strR = new BufferedReader(new FileReader(path));
		
		//Essa estrutura do looping while he classica para ler cada linha
		//do arquivo 
		while((Str = strR.readLine())!= null){
		//Aqui usamos o metodo split que divide a linha lida em um array de String
		//passando como parametro o divisor ";".
			String[] row = Str.split(",");
			tableLine.add(new Edge(Integer.parseInt(row[0]),Integer.parseInt(row[1]),Integer.parseInt(row[2])));
		}
		strR.close();
		} catch (FileNotFoundException e) {
			System.out.println("Erro: Arquivo nao encontrado \n");
			e.printStackTrace();
		} catch (IOException ex){
			System.out.println("Erro: Arquivo invalido \n");
			ex.printStackTrace();
		}
		return tableLine;
	}
}