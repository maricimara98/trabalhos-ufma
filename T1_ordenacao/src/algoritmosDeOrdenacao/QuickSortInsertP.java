package algoritmosDeOrdenacao;
import principal.*;
import java.util.List;

/*  
    QuickSort + Insercao Parcial: O QuickSort devera ser
    executado recursivamente (como padrao) e, quando voce
    obtiver uma particao de tamanho menor ou igual a L,
	essa particao devera ser imediatamente ordenada usando
	o algoritmo de insercao.
*/
public class QuickSortInsertP {
    public static int nComparacoes = 0;
	public static int nMovimentacoes = 0;  

	public static int cmpInsert = 0;
	public static int movInsert = 0;
    
    public static void sorteio(List<Edge> lista) {
		long inicio;
		long fim;

        inicio = System.currentTimeMillis();	// para verificar otempo execucao
        lista = partialQuickInsert(lista, 0, lista.size()-1);
		fim = System.currentTimeMillis();

		/*
        for(Edge e : lista) {
			System.out.println(e.v+", " + e.peso+", " + e.w);	// printar na tela os valores já ordenados	
        }
        */
		
		System.out.println("Comparacoes: " + nComparacoes);
		System.out.println("Movimentacoes: "+ nMovimentacoes);
		System.out.printf("Tempo de processamento do QuickSortP: %.3f ms%n\n", (fim - inicio) / 1000d);
	}

	public static int ParticionarQuick (List<Edge> arestas, int inicio, int fim) {
		// Escolha o elemento mais à direita como pivô da matriz
		Edge pivot = arestas.get(fim);

		// elementos menos que pivô será empurrado para a esquerda do pIndex
		// elementos mais do que pivô será empurrado para a direita do pIndex
		// elementos iguais podem ir de qualquer maneira
		int pIndex = inicio;

		// cada vez que encontramos um elemento menor ou igual ao pivô,
		// pIndex é incrementado e esse elemento seria colocado
		// antes do pivô.
		for (int i = inicio; i < fim; i++) {
			nComparacoes++;
			if (arestas.get(i).peso <= pivot.peso) {
				Edge temp = arestas.get(i);
				arestas.set(i, arestas.get(pIndex));
				arestas.set(pIndex,temp);
				pIndex++;

				nMovimentacoes++;
			}
		}
		// trocar pIndex com Pivô
		Edge temp = arestas.get(fim);
		arestas.set(fim,arestas.get(pIndex));
		arestas.set(pIndex,temp);

		nMovimentacoes++;

		return pIndex;
	}

	public static void QuickSort(List<Edge> arestas, int inicio, int fim) {
		if (inicio >= fim)
			return;

		// rearrange the elements across pivot
		int pivot = ParticionarQuick(arestas, inicio, fim);

		// recursar em sub-matriz contendo elementos menores que pivô
		QuickSort(arestas, inicio, pivot - 1);

		// recursar em sub-matriz contendo elementos mais do que pivô
		QuickSort(arestas, pivot + 1, fim);
	}

	public static List<Edge> partialQuickInsert(List<Edge> arestas, int inicio, int fim) {
		while (inicio < fim) {
			// A ordenacao por insercao e feita se menor que 10
			if (fim - inicio < 10) {
				InsertionSort.InsertSort(arestas);
				nComparacoes = nComparacoes + InsertionSort.nComparacoes;
				nMovimentacoes = nMovimentacoes + InsertionSort.nMovimentacoes;
				break;
			} else {
				int pivot = ParticionarQuick(arestas, inicio, fim);

				// otimizações de chamada de cauda - recur em sub array menor
				if (pivot - inicio < fim - pivot) {
					partialQuickInsert(arestas, inicio, pivot - 1);
					inicio = pivot + 1;
				} else {
					partialQuickInsert(arestas, pivot + 1, fim);
					inicio = pivot - 1;
				}
				nMovimentacoes++;
			}
        } 
        return arestas;
	}
}