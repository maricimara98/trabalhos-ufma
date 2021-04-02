package principal;

public class Menus {
	//menu principal
	public static void menu(){
        System.out.println("============================================");
        System.out.println("Escolha o algoritmo de ordenacao:");
        System.out.println("============================================");
        System.out.println("1 - QuickSort");
        System.out.println("2 - MergeSort");
        System.out.println("3 - InserctionSort"); 
        System.out.println("4 - ShellSort"); 
        System.out.println("5 - HeapSort");
        System.out.println("6 - QuickSort + Insercao Parcial");
        System.out.println("7 - QuickSort + Insercao Final");
        msg_final();
    }
    // menu para evitar aviso de duplicacao de codigo
    public static void msg_final() { 
        System.out.println("0 - Sair");
        System.out.println("Opcao:");
    }
	//menu para escolha do numero de elementos da pasta teste_vertices
	public static void menuElementos(){
        System.out.println("============================================");
        System.out.println("Escolha a quantidade de elementos no arquivo");
        System.out.println("============================================");
        System.out.println("1 - 7 elementos");
        System.out.println("2 - 100 elementos");
        System.out.println("3 - 1.000 elementos");
        System.out.println("4 - 10.000 elementos");
        System.out.println("5 - 100.000 elementos");
        msg_final();
    }       
    
    public static void msg_Exit() {
        System.out.println("\nPrograma encerrado");
    }

}
