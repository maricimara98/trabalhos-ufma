package Principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;

import Arvore.*;
import Hashing.*;
import Lista.Lista;
import Lista.SllNode;
import TAD.TAD;

public class Processamento {
	private static ArvoreAVL arvoreAVL = new ArvoreAVL();
	private static int passosAVL;

	private static HashingEncadeado hashing = new HashingEncadeado();
	private static int passosHash;

	private static Lista listaTweets = new Lista();
	private static int passosLista;

	public static int lerArquivo() throws IOException {
		//principal arquivo de leitura
		String caminho = "src/Twitter/Arquivos/Entrada/10000_tweets.csv";
		File arquivo = new File(caminho);
		
		// condicao para saber se o arquivo existe
		if (!arquivo.exists()) {
			System.err.println("arquivo inexistente");
		} else {
			try {
				FileReader reader = new FileReader(arquivo);
				BufferedReader leitor = new BufferedReader(reader);
				String buffer;
				NoAVL tweet;
				String[] palavras;
				int i = 0;
				while ((buffer = leitor.readLine()) != null) {
					palavras = buffer.split(" ");
					i++;
					for (int j = 0; j < palavras.length; j++) {
						//evita as principais preposicoes
						if (palavras[j].length() > 3) {
							tweet = arvoreAVL.buscaAVL(palavras[j]);
							if (tweet != null) {
								if (!tweet.getValor().getPosicaoLinha().contains(i)) {
									tweet.getValor().getPosicaoLinha().add(i);
								}
							} else {
								TAD novo = new TAD(palavras[j], i);
								arvoreAVL.inserir(novo);
							}
						
							TAD tweetHash = hashing.busca(palavras[j]);
							if(tweetHash!=null){
								if (!tweetHash.getPosicaoLinha().contains(i)) {
									tweetHash.getPosicaoLinha().add(i);
								}
							} else {
								TAD novo = new TAD(palavras[j], i);
								hashing.add(novo);
							}

							TAD tweetLista = listaTweets.busca(palavras[j]);
							if(tweetLista!=null){
								if (!tweetLista.getPosicaoLinha().contains(i)) {
									tweetLista.getPosicaoLinha().add(i);
								}
							} else {
								TAD novo = new TAD(palavras[j], i);
								listaTweets.add(novo);
							}
						}
					}
				}
				leitor.close();
			} catch (IOException ex) {
				System.err.println("O programa se comportou de forma inesperada");
				return 0;
			}
		}
		return 1;
	}

	// imprime a aarvore avl em um arquivo
	public static void arquivoAVL() {
		
		ArrayList<TAD> lista_avl = new ArrayList<TAD>();
		
		String caminho = "src/Twitter/Arquivos/Saida/impressaoAVL.csv";
		File arquivo = new File(caminho);

		if (!arquivo.exists()) {
			try {
				if (arquivo.createNewFile()) {
					PrintWriter pw = new PrintWriter(arquivo);
					Processamento.converteAVLarray(arvoreAVL.getRaiz(), lista_avl);
					pw.println("---------------AVL---------------\n\n");
					for (int i = 0; i < lista_avl.size(); i++) {
						pw.println("palavra : " + lista_avl.get(i).getPalavra());
						pw.println("linha: " + lista_avl.get(i).getPosicaoLinha());
						pw.println("------------------------------\n\n");
					}
					System.out.println("Arquivo Gerado!");
					pw.close();
				}
			} catch (IOException ex) {
				System.out.println("Erro: o arquivo nao pode ser criado");
			}
		} else {
			arquivo.delete();
		}
	}

	// busca somente a primeira ocorrencia na arvore
	public static void buscaAvl(String chave) {
		passosAVL = 0;
		NoAVL retorno = arvoreAVL.buscaAVL(chave);
		if (retorno != null) {
			System.out.println("palavra encontrada em " + passosAVL + " passos!");
			System.out.println("palavra : " + retorno.getValor().getPalavra() + "\n"
					+ retorno.getValor().linhaString());
		} else {
			Menus.palavra_nao_encontrada();
		}
	}

	// para facilitar a escrita no arquivo
	private static void converteAVLarray(NoAVL no, ArrayList<TAD> lista_avl) {
		if (no != null) {
			converteAVLarray(no.getEsquerdo(), lista_avl);
			lista_avl.add(no.getValor());
			converteAVLarray(no.getDireito(), lista_avl);
		}
	}

	public static void arquivoHashing() {
		String caminho = "src/Twitter/Arquivos/Saida/ImpressaoHashing.csv";
		File arquivo = new File(caminho);

		if (!arquivo.exists()) {
			try {
				if (arquivo.createNewFile()) {
					PrintWriter pw = new PrintWriter(arquivo);
					pw.println("---------------HASHING---------------\n\n");
					for (int i = 0; i < hashing.getHTable().length; i++) {
						SllNode atual = hashing.getHTable()[i].getPrimeiro();
						while (atual != null) {
							pw.println("palavra : " + atual.getElemento().getPalavra());
							pw.println("linha: " + atual.getElemento().getPosicaoLinha());
							pw.println("------------------------------\n\n");
							atual = atual.getProximo();
						}
					}
					System.out.println("Arquivo Gerado!");
					pw.close();
				}
			} catch (IOException ex) {
				System.out.println("Erro: o arquivo nao pode ser criado");
			}
		} else {
			arquivo.delete();
		}
	}

	public static void buscarHashing(String chave) {
		passosHash = 0;
		TAD retorno = hashing.busca(chave);
		// procura no Hashing
		if (retorno != null) {
			System.out.println("palavra encontrada em " + passosHash + " passos!");
			System.out.println("palavra : " + retorno.getPalavra() + "\n" + retorno.linhaString());
		}else{
			Menus.palavra_nao_encontrada();
		}

	}
	public static void arquivoLista() {
		String caminho = "src/Twitter/Arquivos/Saida/ImpressaoLista.csv";
		File arquivo = new File(caminho);

		if (!arquivo.exists()) {
			try {
				if (arquivo.createNewFile()) {
					PrintWriter pw = new PrintWriter(arquivo);
					pw.println("---------------LISTA---------------\n\n");
					
					for (int i = 0; i < listaTweets.getTotal_elementos() ; i++) {
						SllNode atual = listaTweets.getPrimeiro();
						while (atual != null) {
							pw.println("palavra : " + atual.getElemento().getPalavra());
							pw.println("linha: " + atual.getElemento().getPosicaoLinha());
							pw.println("------------------------------\n\n");
							atual = atual.getProximo();
						}
					}
					System.out.println("Arquivo Gerado!");
					pw.close();
				}
			} catch (IOException ex) {
				System.out.println("Erro: o arquivo nao pode ser criado");
			}
		} else {
			arquivo.delete();
		}
	}

	public static void buscarLista(String chave) {
		passosLista = 0;
		TAD retorno = listaTweets.busca(chave);
		// procura na Lista
		if (retorno != null) {
			System.out.println("palavra encontrada em " + passosLista + " passos!");
			System.out.println("palavra : " + retorno.getPalavra() + "\n" + retorno.linhaString());
		}else{
			Menus.palavra_nao_encontrada();
		}

	}
	// verifica quantos passos foram
	// necessarios para chegar a chave
	public static void passosAVL() {
		passosAVL++;
	}

	public static void passosHash() {
		passosHash++;
	}

	public static void passosLista() {
		passosLista++;
	}
	
}
