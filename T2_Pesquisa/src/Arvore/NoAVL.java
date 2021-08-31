package Arvore;
import TAD.TAD;

public class NoAVL {
	private NoAVL esquerdo;  //filho esquerdo
	private NoAVL pai;
	private NoAVL direito; //filho direito

	private TAD dados;
	private int balanceamento; // FB = direito - esquerdo
	
	public NoAVL(){}
	public NoAVL(TAD dados){
		setEsquerdo(null);
		setDireito(null);
		setPai(null);
		setBalanceamento(0);
		this.dados = dados;
		
	}

	//encapsulamento
	public TAD getValor() {
		return dados;
	}

	public void setValor(TAD dados) {
		this.dados = dados;
	}
	
	public NoAVL getEsquerdo() {
		return esquerdo;
	}

	public void setEsquerdo(NoAVL esquerdo) {
		this.esquerdo = esquerdo;
	}

	public NoAVL getDireito() {
		return direito;
	}

	public void setDireito(NoAVL direito) {
		this.direito = direito;
	}

	public NoAVL getPai() {
		return pai;
	}

	public void setPai(NoAVL pai) {
		this.pai = pai;
	}	

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}
	
}
