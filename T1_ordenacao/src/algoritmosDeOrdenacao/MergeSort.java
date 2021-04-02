package algoritmosDeOrdenacao;

import java.util.ArrayList;
import java.util.List;

import principal.*;

public class MergeSort {
	public static int nComparacoes = 0;
	public static int nAtribuicoes = 0;

	public static void sorteio(List<Edge> vertices) {
		long inicio;
		long fim;
		
		// System.out.println("Arquivo de teste carregado");
		inicio = System.currentTimeMillis(); // para verificar o tempo
		vertices = mergesort(vertices, 0, vertices.size()-1);
		fim = System.currentTimeMillis();

		System.out.println("Ordenado");
		for(Edge e : vertices) {
			System.out.println(e.v+", " + e.peso+", " + e.w);	// printar na tela os valores já ordenados	
		}
		
		System.out.println("Comparacoes: " + nComparacoes);
		System.out.println("Atribuicoes: "+ nAtribuicoes);
		System.out.printf("Tempo de processamento Mergesort: %.3f ms%n\n", (fim - inicio) / 1000d);
	}


	public static List<Edge> mergesort(List<Edge> vertices, int esquerda, int direita) {
		int meio;
		if (esquerda < direita) {
			nComparacoes++;
			meio = ((esquerda + direita) / 2);
			nAtribuicoes++;
			mergesort(vertices, esquerda, meio);	// divisao pela esquerda
			mergesort(vertices, meio + 1, direita);	// divisao pela direita
			Intercalar(vertices, esquerda, meio, direita);
		}
		nComparacoes++;
		return vertices;
	}

	public static List<Edge> Intercalar(List<Edge> vertices, int esquerda, int meio, int direita) {
		List<Edge> verticesCopia = new ArrayList<Edge>(vertices);
		
		int contEsquerda = esquerda;	// contador da esquerda | i
		int contDir = meio + 1;	// contador da direita | j
		int contCopia = esquerda;	//contador da copia da vertices

		nAtribuicoes += 4;

		while (contCopia <= direita) {
			if (contEsquerda > meio) {
				// vertices[contCopia]["peso"] = contCopia[contDir]["peso"];
				vertices.set(contCopia, verticesCopia.get(contDir));
			
				contDir +=1;
				nAtribuicoes+=2;
			} else if (contDir > direita) {
				vertices.set(contCopia, verticesCopia.get(contEsquerda));

				contEsquerda++;
				nAtribuicoes+=2;
			} else if ((verticesCopia.get(contEsquerda).peso) < (verticesCopia.get(contDir).peso)) {
				vertices.set(contCopia, verticesCopia.get(contEsquerda));
			
				contEsquerda++;
				nAtribuicoes+=2;
			} else {
				vertices.set(contCopia, vertices.get(contDir));
			
				contDir++;
				nAtribuicoes+=4;
			}
			contCopia++;
			nAtribuicoes++;
			nComparacoes++;
		}
		return vertices;
	}

}
