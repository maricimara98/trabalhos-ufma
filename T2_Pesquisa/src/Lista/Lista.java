package Lista;
import Principal.Processamento;
import TAD.TAD;

public class Lista {
	private SllNode primeiro;
	private SllNode ultimo;
	private int total_elementos;
	
	// inserir na lista
	public void add(TAD elemento){
		// se a lista estiver fazia
		if(this.total_elementos == 0){
			// insira no inicio
			this.addInicio(elemento);
		}else{
			// crie um novo no e ensira na frente do ultimo
			SllNode nova = new SllNode(elemento);
			this.ultimo.setProximo(nova);
			this.ultimo = nova;
			this.total_elementos++;
		}
	}

	// adicionar no inicio de uma lista vazia
	public void addInicio(TAD elemento){
		SllNode nova = new SllNode(this.primeiro, elemento);
		this.primeiro = nova;
		
		if(this.total_elementos == 0){
			this.ultimo = nova;
		}
		this.total_elementos ++;
	}

	public TAD busca(String palavra) {
		return buscaLista(palavra);
	}

	// metodo de busca na lista
	public TAD buscaLista(String chave) {
			// comeca a procurar do primeiro
		SllNode atual = this.primeiro;
		while(atual!=null){
			Processamento.passosLista();
			if(atual.getElemento().getPalavra().compareToIgnoreCase(chave)==0){
				return atual.getElemento();
			}
			atual = atual.getProximo();
		}
		return null;
	}


	// busca linear na lista para o Hashing
	public TAD buscaListaH(String key){
		// comeca a procurar do primeiro
		SllNode atual = this.primeiro;
		while(atual!=null){
			Processamento.passosHash();
			if(atual.getElemento().getPalavra().compareToIgnoreCase(key)==0){
				return atual.getElemento();
			}
			atual = atual.getProximo();
		}
		return null;
	}

	// encapsulamento
	public SllNode getPrimeiro() {
		return primeiro;
	}
	public void setPrimeiro(SllNode primeiro) {
		this.primeiro = primeiro;
	}
	public SllNode getUltimo() {
		return ultimo;
	}
	public void setUltimo(SllNode ultimo) {
		this.ultimo = ultimo;
	}
	public int getTotal_elementos() {
		return total_elementos;
	}
	public void setTotal_elementos(int total_elementos) {
		this.total_elementos = total_elementos;
	}
	
}
