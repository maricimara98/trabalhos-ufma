package algoritmosDeOrdenacao;

import principal.*;
import java.util.List;

/* classe que trabalha com o quicksort*/

public class QuickSort {
	public static int nComparacoes = 0;
	public static int nModificacoes = 0;

	public static void sorteio(List<Edge> lista) {
		long inicio;
		long fim;
 
		// ordenacao em ordem crescente e pivo como o primeiro da esquerda
		inicio = System.currentTimeMillis(); // auxilia o calculo do tempo de execucao do metodo
		//System.out.println("Aplicar metodo Quicksort");
		lista = quicksort(lista, 0, lista.size() - 1);
		//System.out.println("Aplicado metodo Quicksort");
		fim = System.currentTimeMillis();
		/*
		System.out.println("Ordenado");
		for(Edge e : lista) {
			System.out.println(e.v+", " + e.peso+", " + e.w);	// printar na tela os valores já ordenados	
		}
		*/
		
		System.out.println("Comparacoes: " + nComparacoes);
		System.out.println("Modificacoes: "+ (nModificacoes-2));
		System.out.printf("Tempo de processamento do QuickSort: %.3f ms%n\n", (fim - inicio) / 1000d);

	}

	public static List<Edge> quicksort(List<Edge> lista, int inicio, int fim) {
		int pos;
		
		nComparacoes++; 
		if (inicio < fim) {
			
			pos = particionar(lista, inicio, fim);
			quicksort(lista, inicio, pos - 1);
			quicksort(lista, pos + 1, fim);
			
		} 
		return lista;
	}

	// pivo como elemento mais a esquerda
	public static int particionar(List<Edge> lista, int esquerda, int direita){
		int i = esquerda + 1;
		int j = direita;
		Edge pivo = lista.get(esquerda);

		
		while(i <= j){
			
			if((int)lista.get(i).peso <= pivo.peso) {
				i++;
			} else if(pivo.peso <= (int)lista.get(j).peso) {
				j--;
			} else {
				trocar(lista, i, j);
				i++;
				j--;
			}
		}
		trocar(lista, esquerda, j);
		
		return j;
	}

	public static void trocar(List<Edge> lista, int i, int j){
		Edge aux = lista.get(i);
		lista.set(i, lista.get(j));
		lista.set(j, aux);

		nModificacoes++;
	}

}
