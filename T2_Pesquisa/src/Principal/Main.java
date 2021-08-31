package Principal;

import java.io.IOException;

public class Main {

	public static void main(String[] agvs) throws IOException {
		int opcao_principal, opcao_busca, opcao_imprime;
		String buscar;

		int ArquivoTweets = Processamento.lerArquivo();
		System.out.println("Processando arquivo...");
		// testa se o caminho do arquivo passado é valido
		while (ArquivoTweets == 0) {
			ArquivoTweets = Processamento.lerArquivo();
		}
		System.out.println("Arquivo carregado");
		
		
		do {
			Menus.menuPrincipal();
			opcao_principal = Ler.numero();
			switch (opcao_principal) {		

			// --------------------------------------------------------	
			case 1:
				// buscar palavra
				System.out.println("Digite a palavra: ");
				buscar = Ler.palavra();

				Menus.menuBusca();
				opcao_busca = Ler.numero();
				switch (opcao_busca) {
				case 1:
					// buscar no hashing
					Processamento.buscarHashing(buscar);
					break;
				case 2:	
					// buscar na AVL
					Processamento.buscaAvl(buscar);
					break;
				case 3:
					// buscar na Lista
					Processamento.buscarLista(buscar);
					break;
				case 0:
					// sair
					Menus.exit();
					break;
				default:
					Menus.opcao_invalida();
					break;
				}

				break;
			
			// --------------------------------------------------------

			case 2:
				Menus.menuImprime();
				opcao_imprime = Ler.numero();

				switch (opcao_imprime) {
				case 1:
					// imprime o hashing em um arquivo
					Processamento.arquivoHashing();
					break;
				case 2:
					// imprime a arvore avl em um arquivo
					Processamento.arquivoAVL();
					break;
				case 3:
					//imprime a lista em um arquivo
					Processamento.arquivoLista();
					break;
				case 0:
					Menus.exit();
					break;
				default:
					Menus.opcao_invalida();
					break;
				}
				break;
			case 0:
				Menus.exit();
				break;
			default:
				Menus.opcao_invalida();
				break;
			}
		} while (opcao_principal != 0);
	}
}
