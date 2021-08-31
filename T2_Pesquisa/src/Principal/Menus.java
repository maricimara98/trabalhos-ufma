package Principal;

public class Menus {
	public static void menuPrincipal(){
        System.out.println("1-Buscar palavra no indice.");
        System.out.println("2-Imprimir indice.");
        System.out.println("0-Sair");
        System.out.println("Opcao:");
    }
	public static void menuBusca(){
        System.out.println("1-Buscar no Hashing.");
        System.out.println("2-Buscar na AVL.");
        System.out.println("3-Buscar na Lista");
        System.out.println("0-Sair");
        System.out.println("Opcao:");
    }
	public static void menuImprime(){
        System.out.println("Imprimir:");
        System.out.println("1-Tabela Hashing.");
        System.out.println("2-Arvore AVL.");
        System.out.println("3-Lista");
        System.out.println("0-Sair");
        System.out.println("Opcao:");
    }
    public static void exit() {
        System.out.println("Encerrando");
    }
    public static void opcao_invalida() {
        System.err.println("Opcao invalida!");
    }
    public static void palavra_nao_encontrada() {
        
    }
	
}
