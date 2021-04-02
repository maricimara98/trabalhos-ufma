package algoritmosDeOrdenacao;

import principal.*;

import java.util.List;

public class HeapSort {
	static int nComparacoes = 0;
	static int nMovimentacoes = 0;
	static List<Edge> chave;

	public static void sorteio(List<Edge> arestas) {
		long inicio;
		long fim;
		
		inicio = System.currentTimeMillis();
		heapSort(arestas);
		fim = System.currentTimeMillis();

		/*
		System.out.println("Ordenado");
		for (Edge e : arestas) {
			System.out.println(e.v + ", " + e.peso + ", " + e.w); // printar na tela os valores já ordenados
		}
		*/

		System.out.println("Comparacoes: " + nComparacoes);
		System.out.println("Movimentacoes: " + nMovimentacoes);
		System.out.printf("Tempo de processamento HeapSort: %.3f ms%n\n", (fim - inicio) / 1000d);

	}

	public static List<Edge> heapSort(List<Edge> arestas) { 

        int n = arestas.size();
    
        for (int i = n/2 - 1; i >= 0; i--) { // Constroi heap (reorganizar array)
        	heapify(arestas, n, i);
            nMovimentacoes++;
        	
        }
        for (int i = n - 1; i >= 0; i--) { // Extrai um elemento do heap um por um
        	// Move a raiz atual para o fim
        	Edge aux = arestas.get(0); 
            arestas.set(0, arestas.get(i)); 
            arestas.set(i, aux); 
            heapify(arestas, i, 0);
            nMovimentacoes++;
		}
		
		return arestas;

    }
	
	public static void heapify(List<Edge> arestas, int n, int i) {
		int largura = i;
        int esquerda = 2*i + 1;
        int direita = 2*i + 2;
        
        if (esquerda < n && arestas.get(esquerda).peso > arestas.get(largura).peso) { // Se o filho esquerdo for maior que a raiz
        	nComparacoes++;
        	largura = esquerda; 
        }
        
        if (direita < n && arestas.get(direita).peso > arestas.get(largura).peso) { // Se o filho direito for maior do que o maior até agora
        	nComparacoes++;
        	largura = direita; 
        }
        
        if (largura != i) { // Se o maior não for raiz
        	nComparacoes++;
            Edge aux = arestas.get(i); 
            arestas.set(i, arestas.get(largura)); 
            arestas.set(largura, aux);
            nMovimentacoes++;
            heapify(arestas, n, largura); 
        } 
	}
}