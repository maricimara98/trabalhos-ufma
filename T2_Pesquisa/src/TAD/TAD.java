package TAD;

import java.util.ArrayList;
import java.util.Iterator;

// Guarda a palavra lida do arquivo e a linha de sua ocorrencia
public class TAD {

	private String palavra;
	private ArrayList<Integer> posicao_linha = new ArrayList<Integer>();
	
	public TAD(){}
	public TAD(String palavra, int posicao_linha) {
		this.palavra = palavra;
		this.posicao_linha.add(posicao_linha);
	}

	public String linhaString(){
		String linha = "linha(s): ";
		Iterator<Integer> esta = posicao_linha.iterator();
		while(esta.hasNext()){
			linha = linha + esta.next().toString();
			linha = linha + "-";
		}
		return linha.substring(0,linha.length()-1);
	}

	// encapsulamento
	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public ArrayList<Integer> getPosicaoLinha() {
		return posicao_linha;
	}	

}
