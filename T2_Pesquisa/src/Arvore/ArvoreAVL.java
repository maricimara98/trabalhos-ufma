package Arvore;

import Principal.Processamento;
import TAD.TAD;

public class ArvoreAVL {

	protected NoAVL raiz;

	public void inserir(TAD dados) {
		NoAVL n = new NoAVL(dados);
		inserirAVL(this.raiz, n);
	}

	private void inserirAVL(NoAVL comparar, NoAVL inserir) {
		if (comparar == null) {
			this.raiz = inserir;
		} else {
			if (inserir.getValor().getPalavra().compareToIgnoreCase(comparar.getValor().getPalavra()) < 0) {
				if (comparar.getEsquerdo() == null) {
					comparar.setEsquerdo(inserir);
					inserir.setPai(comparar);
					verificarBalanceamento(comparar);
				} else {
					inserirAVL(comparar.getEsquerdo(), inserir);
				}

			} else if (inserir.getValor().getPalavra().compareToIgnoreCase(comparar.getValor().getPalavra()) > 0) {
				if (comparar.getDireito() == null) {
					comparar.setDireito(inserir);
					inserir.setPai(comparar);
					verificarBalanceamento(comparar);
				} else {
					inserirAVL(comparar.getDireito(), inserir);
				}
			}
		}

	}

	// verificar que tipo de rotamento sera necessario
	private void verificarBalanceamento(NoAVL atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {
			if (altura(atual.getEsquerdo().getEsquerdo()) >= altura(atual.getEsquerdo().getDireito())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}
		} else if (balanceamento == 2) {
			if (altura(atual.getDireito().getDireito()) >= altura(atual.getDireito().getEsquerdo())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}
		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	private NoAVL rotacaoEsquerda(NoAVL inicial) {

		NoAVL direita = inicial.getDireito();
		direita.setPai(inicial.getPai());

		inicial.setDireito(direita.getEsquerdo());

		if (inicial.getDireito() != null) {
			inicial.getDireito().setPai(inicial);
		}

		direita.setEsquerdo(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireito() == inicial) {
				direita.getPai().setDireito(direita);

			} else if (direita.getPai().getEsquerdo() == inicial) {
				direita.getPai().setEsquerdo(direita);
			}
		}
		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	private NoAVL rotacaoDireita(NoAVL inicial) {

		NoAVL esquerda = inicial.getEsquerdo();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerdo(esquerda.getDireito());

		if (inicial.getEsquerdo() != null) {
			inicial.getEsquerdo().setPai(inicial);
		}

		esquerda.setDireito(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireito() == inicial) {
				esquerda.getPai().setDireito(esquerda);

			} else if (esquerda.getPai().getEsquerdo() == inicial) {
				esquerda.getPai().setEsquerdo(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	private NoAVL duplaRotacaoEsquerdaDireita(NoAVL inicial) {
		inicial.setEsquerdo(rotacaoEsquerda(inicial.getEsquerdo()));
		return rotacaoDireita(inicial);
	}

	private NoAVL duplaRotacaoDireitaEsquerda(NoAVL inicial) {
		inicial.setDireito(rotacaoDireita(inicial.getDireito()));
		return rotacaoEsquerda(inicial);
	}

	private int altura(NoAVL atual) {
		if (atual == null) {
			return -1;
		}
		if (atual.getEsquerdo() == null && atual.getDireito() == null) {
			return 0;

		} else if (atual.getEsquerdo() == null) {
			return 1 + altura(atual.getDireito());

		} else if (atual.getDireito() == null) {
			return 1 + altura(atual.getEsquerdo());

		} else {
			return 1 + Math.max(altura(atual.getEsquerdo()), altura(atual.getDireito()));
		}
	}

	private void setBalanceamento(NoAVL no) {
		no.setBalanceamento(altura(no.getDireito()) - altura(no.getEsquerdo()));
	}

	public NoAVL buscaAVL(String chave) {
		// a busca inicia sempre pelono raiz da arvore
		NoAVL atual = this.getRaiz();
		while (atual != null) {
			// verifica se e igual
			if (atual.getValor().getPalavra().compareToIgnoreCase(chave)!=0) {
				Processamento.passosAVL();
				// percorre a arvore verifican inicialmente se e menor
				if (atual.getValor().getPalavra().compareToIgnoreCase(chave) > 0) {
					// Vai para esquerda
					atual = atual.getEsquerdo();
				} else {
					// Vai para direita
					atual = atual.getDireito(); 
				}
			} else {
				// conta os passos dados ate encontrar a chave
				// ou acabar a arvore
				Processamento.passosAVL();
				break;
			}
		}
		// Verifica se encontrou
		if (atual == null) {
			// a chave nao esta na arvore
			return null;
		} else {
			// retorna a chave encontrada
			return atual;
		}
	}

	// retorna a raiz
	public NoAVL getRaiz() {
		return raiz;
	}
}
