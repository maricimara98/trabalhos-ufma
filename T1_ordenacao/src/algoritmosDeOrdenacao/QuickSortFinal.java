package algoritmosDeOrdenacao;

import principal.*;
import java.util.List;

/*  
    QuickSort + Insercao Parcial: O QuickSort devera ser executado
    recursivamente (como padrao) e, quando voce obtiver uma
    particao de tamanho menor ou igual a L, essa particao devera
    ser ignorada e continuar particionando as particoes maiores.
    Uma vez que todas as particoes tenham comprimento menor ou
    igual a L, o algoritmo devera executar o algoritmo de ordenacao.

*/
public class QuickSortFinal {

    public static int nComparacoes = 0;
	public static int nMovimentacoes = 0;

	public static void sorteio(List<Edge> arestas) {
		long inicio;
		long fim;
		
		inicio = System.currentTimeMillis(); // para verificar o tempo
        arestas = QuicksortF(arestas,  0, arestas.size() - 1);
        // depois da ordenacao com o quick o insert e chamado
        arestas = InsertionSort.InsertSort(arestas);
        // e preciso atualizar os valores de calculo 
        // depois do uso do insert
        nComparacoes = nComparacoes + InsertionSort.nComparacoes;
        nMovimentacoes = nMovimentacoes + InsertionSort.nMovimentacoes;

        fim = System.currentTimeMillis();

        /*
        for(Edge e : lista) {
			System.out.println(e.v+", " + e.peso+", " + e.w);	// printar na tela os valores já ordenados	
        }
        */
		
		System.out.println("Comparacoes: " + nComparacoes);
		System.out.println("Movimentacoes: "+ nMovimentacoes);
		System.out.printf("Tempo de processamento QuickSortFinal: %.3f ms%n\n", (fim - inicio) / 1000d);
    }


    public static List<Edge> QuicksortF(List<Edge>  arestas, int p, int r) {
        // Para fins de teste o L foi predeterminado com 10
        if((r-p)>10) { // listas maiores que 10
            int q;
            int p1 = p;
            int r1 = r;

            nComparacoes++;
            Med3(arestas, p1,r1); // Chooses pivot
            nComparacoes++; // Med3 sempre vai somar mais um no calculo
            Edge x = arestas.get(p1);

            while(true) {
                
                do{
                    r1--;
                } while(arestas.get(r1).peso > x.peso);
                
                arestas.set(p1, arestas.get(r1));
                nMovimentacoes++;
            
                do{
                    p1++;
                    nComparacoes++;
                }while(arestas.get(p1).peso < x.peso);
                
                if(p1<r1){
                    arestas.set(r1, arestas.get(p1));
                    nMovimentacoes++;
                } else {
                    if(arestas.get(r1+1).peso <= x.peso)
                        r1++;
                        arestas.set(r1, x);
                        q = r1;
                        nMovimentacoes++;
                        break;
                }
            }
            QuicksortF(arestas, p, q-1);
            QuicksortF(arestas, q+1, r);
        }
        return arestas;
    }

    public static List<Edge> Med3(List<Edge>  arestas, int p, int r) {
    // Selects the median and sets the sentinels
        int mid = (p+r)/2;
        int largura;

        if(arestas.get(p).peso > arestas.get(mid).peso) {
            largura = p;
        } else{
            largura = mid;
        }

        if(arestas.get(largura).peso > arestas.get(r).peso) {
            Edge temp = arestas.get(r);
            arestas.set(r, arestas.get(largura));
            arestas.set(largura, temp);
        }

        if(arestas.get(mid).peso > arestas.get(p).peso) {
            Edge temp = arestas.get(p);
            arestas.set(p, arestas.get(mid));
            arestas.set(mid, temp);
        }
        return arestas;
    } 
       
}
