package Hashing;

import TAD.TAD;
import Lista.*;

// tabela hashing com encadeamento externo
public class HashingEncadeado {


	// cada posicao equivale a uma letra do alfabeto
	private Lista[] HTable = new Lista[26];

	public HashingEncadeado() {
		for (int i = 0; i < this.HTable.length; i++) {
			// cada posicao faz referencia para uma sll
			this.HTable[i] = new Lista();
		}
	}

	// calcula onde a palavra sera inserida
	private int funcaoHash(String palavra) {
		// leva em consideracao o primeiro caractere
		return palavra.toLowerCase().charAt(0) % 26;
	}

	// adicionar no Hashing
	public void add(TAD dados) {
		// calcula a posicao
		int posicao = funcaoHash(dados.getPalavra());
		HTable[posicao].add(dados);
	}

	// busca todas as ocorrencias
	public TAD busca(String palavra) {
		int posicao = funcaoHash(palavra);
		return this.HTable[posicao].buscaListaH(palavra);
	}

	// retorna a tabela já criada
	public Lista[] getHTable() {
		return HTable;
	}

}
