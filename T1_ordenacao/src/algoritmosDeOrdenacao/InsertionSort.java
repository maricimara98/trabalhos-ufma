package algoritmosDeOrdenacao;
import java.util.List;

import principal.*;

/*classe que trabalha com o insertsort*/
public class InsertionSort {
	public static int nComparacoes = 0;
	public static int nMovimentacoes = 0;
	
	public static void sorteio(List<Edge> lista) {
		long inicio;
		long fim;
		// System.out.println("Arquivo de teste carregado");
		inicio = System.currentTimeMillis();	// para verificar otempo execucao
		lista = InsertSort(lista);	// lista sera ordenado em ordem crescente
		// System.out.println("Aplicado metodo InsertSort");
		fim = System.currentTimeMillis();

		
		System.out.println("Ordenado");
		for(Edge e : lista) {
			System.out.println(e.v+", " + e.peso+", " + e.w);	// printar na tela os valores já ordenados	
		}
		
		System.out.println("Comparacoes: " + nComparacoes);
		System.out.println("Movimentacoes: "+ nMovimentacoes);
		System.out.printf("Tempo de processamento do InsertSort: %.3f ms%n\n", (fim - inicio) / 1000d);
	}

	// ordena usando o metodo de insercao
	public static List<Edge> InsertSort(List<Edge> lista) {
		int i;
		int j;
		// o primeiro  elemento e dito ja ordenado
		for (i = 1; i < lista.size(); i++) {
			Edge chave = lista.get(i);		// inicializar o auxiliar
			j = i;
			
			while((j > 0) && (lista.get(j - 1).peso > chave.peso)) {
				nComparacoes++;
				lista.set(j,lista.get(j - 1));
				j--;	
				nMovimentacoes++;
			}
			lista.set(j, chave);
			nComparacoes+=2; 
		}
		return lista;
	}
}