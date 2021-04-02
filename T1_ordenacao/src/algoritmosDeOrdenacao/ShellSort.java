package algoritmosDeOrdenacao;

import java.util.List;
import principal.*;

/*
O Shellort e uma extencao do algoritmo de ordenacao que
permite a troca de registros que estao distantes um do outro
E uma extensao do Insert
*/
public class ShellSort {
    static int nComparacoes = 0;
	static int nMovimentacoes = 0;

    public static void sorteio(List<Edge> lista) {
		long inicio;
		long fim;
		
		inicio = System.currentTimeMillis();
		lista = Shell(lista);
        fim = System.currentTimeMillis();

        System.out.println("Ordenado");
        /*
        for(Edge e : lista) {
			System.out.println(e.v+", " + e.peso+", " + e.w);	// printar na tela os valores já ordenados	
        }
        */
        System.out.println("Comparacoes: " + nComparacoes);
		System.out.println("Movimentacoes: "+ nMovimentacoes);
		System.out.printf("Tempo de processamento ShellSort: %.3f ms%n\n", (fim - inicio) / 1000d);
    }
  
    public static List<Edge> Shell(List<Edge> lista) {
        int n = lista.size(); 
        
        // Comece com um grande h, em seguida, reduzir o h
        for (int h = n / 2; h > 0; h /= 2) { 
            // Faça um tipo de insercao escancarada para este tamanho de lacuna. 
            // Os primeiros elementos de lacuna a[0..h-1] já estão 
            // em ordem. Continua adicionando mais um elemento 
            // até que toda a lista seja classificada 
            for (int i = h; i < n; i += 1) { 
                // adicionar a[i] aos elementos que foram h 
                //  classificado salvar um [i] em temperatura e fazer um buraco na posicao i
                Edge temp = lista.get(i);
                // mudar elementos anteriores classificados até o local
                // correto para a[i] ser encontrado 
                int j; 
                for (j = i; ((j >= h) && (lista.get(j - h).peso > temp.peso)); j -= h){ 
                    lista.set(j, lista.get(j - h));
                    nComparacoes+=2;
                     // coloca o temporario o a[i] original em sua localizacao correta
                }
                lista.set(j, temp);       
            } 
            nMovimentacoes++;
            nComparacoes++;
            
        } 
        return lista; 
    } 
}
