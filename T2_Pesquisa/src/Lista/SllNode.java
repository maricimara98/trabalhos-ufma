package Lista;
import TAD.TAD;

// no da lista simplesmente encadeada
public class SllNode {
	private TAD elemento; 
	private SllNode proximo; 
	
	public SllNode(SllNode proximo,TAD elemento){
		this.proximo = proximo;
		this.elemento = elemento;
	}
	public SllNode(TAD elemento){
		this.elemento = elemento;
	}
	
	//encapsulamento
	public TAD getElemento() {
		return elemento;
	}
	public void setElemento(TAD elemento) {
		this.elemento = elemento;
	}
	public SllNode getProximo() {
		return proximo;
	}
	public void setProximo(SllNode proximo) {
		this.proximo = proximo;
	}
	
}
