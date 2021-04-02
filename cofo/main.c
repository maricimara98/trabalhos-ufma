#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>
#include "cofo.h"
#include "menu-msg.h"

typedef struct _Pessoa_{
	char *nome;
	char *cpf;
	int idade;
	int NumFilhos;
	float salario;
}Pessoa;

int CompCPF(void *cpf, void *pessoa){
	char *key = (char*)cpf;
	Pessoa *p = (Pessoa*)pessoa;
	if(strcmp(key,p setacpf)==0){
		return True;
	}
	return False;
}

int CompNome(void *nome, void *pessoa){
	char *key = (char*)nome;
	Pessoa *p = (Pessoa*)pessoa;
	if(strcmp(key,p setanome)== 0){
		return True;
	}
	return False;
}

Pessoa *AlocouTestou(int n){
  Pessoa *p;
  p = (Pessoa*)malloc(sizeof(Pessoa));
  if(p!=NULL){
      return p;
  }
  return NULL;
}

Pessoa *cadastro(Pessoa *NovaPessoa){
	printf("Nome: ");
	NovaPessoa setanome = (char *)malloc(sizeof(char)*100 );
    fflush(stdin);
    fgets((NovaPessoa setanome), 100, stdin);     /* Para receber nome completo*/

    printf("CPF: ");
    NovaPessoa setacpf = (char *)malloc(sizeof(char)*100 );
	fflush(stdin);
    fgets(NovaPessoa setacpf, 100, stdin);

	printf("Idade: ");
 	scanf("%d",&(NovaPessoa  seta idade));

	printf("Número de Filhos: ");
	scanf("%d",&(NovaPessoa  seta NumFilhos));

	printf("Salário: R$ ");
	scanf("%f",&(NovaPessoa  seta salario));

	return NovaPessoa;
}

    void printfCadastro(Pessoa *pessoa){
    printf(" -- -- -- -- -- -- -- -- -- -- ---\n");
    printf("Nome: %s\n", pessoa setanome);
    printf("CPF: %s\n", pessoa setacpf);
    printf("Idade: %d\n", pessoa setaidade);
    printf("Filhos: %d\n", pessoa setaNumFilhos);
    printf("Salário: %.2f\n", pessoa setasalario);
}


int main(){
	cofo *usuarios;                               /* Especificação do Cofo */
	usuarios = NULL;
/* -- -- -- -- -- -- -- -- -- -- -- --- Declarações  -- -- -- -- -- -- -- -- -- -- -- --- */
    int qtdMax, qtdPessoas = 0;
    int aux, opcao1, opcao2;
	char *CpfInput, *NomeInput;                     /* Entradas do que o usuario quer procurar/apagar */
    Pessoa *NovaPessoa, *PessoaAux;
    int CofGerado = False;

    setlocale(LC_ALL, "Portuguese");                /* Acentuação */

/* -- -- -- -- -- -- -- -- -- -- -- -- -- -- Opções  -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
	while(opcao1 != 0){
    	Display_Menu();
        printf("Opção: ");
		scanf("%d",&opcao1);
/* -- -- -- -- -- -- -- -- -- -- -- -- -- Criar cofo  -- -- -- -- -- -- -- -- -- -- -- -- -- */
		switch(opcao1){
		    case 0:
              exit(1);
              break;
		    case 1:
		        if(CofGerado == False){
                  printf("Capacidade máxima de Usuários: ");
                  scanf("%d",&qtdMax);
                  usuarios = CofCreate(qtdMax);
                  if(usuarios != NULL){
                      CofGerado = True;
                      SucessoMaster();
                  }
		        } else {
                  ErroMaster();
		        }
              break;
/*  -- -- -- -- -- -- -- -- -- -- -- --- Inserir no cofo  -- -- -- -- -- -- -- -- -- -- -- --- */
          case 2:
              if(usuarios != NULL){
                  if(qtdPessoas < qtdMax ){
                      NovaPessoa = (Pessoa*)malloc(sizeof(Pessoa));
                      cadastro(NovaPessoa);
                      aux = CofInsert(usuarios, (void*)NovaPessoa);
                      if(aux == True){
                          Adicionado();
                          qtdPessoas++;
                      }
                  }else{
                      ErroAlocacao();
                  }
              }else{
                  ErroSupremo();
              }
              break;
/*  -- -- -- -- -- -- -- -- -- -- -- -- -- Consultar Dados  -- -- -- -- -- -- -- -- -- -- -- -- -- */
          case 3:
				if(usuarios != NULL){
                  if(qtdPessoas > 0){
                  PessoaAux = AlocouTestou(1);
                    opcao2 =  -1;
                    while(opcao2 != 1 && opcao2 != 2 && opcao2 != 0){
                          system("cls");
                          Display_Menu();
                          printf("Digite o modelo de busca: \n1- Nome\n2- CPF\n0- Sair\nopção: ");
                          scanf("%d",&opcao2);
                              if(opcao2 == 1){
                                  NomeInput = (char *)malloc(sizeof(char)*100);
                                  printf("\nDigite o Nome: ");
                                  fflush(stdin);
                                  fgets(NomeInput, 100, stdin);
                                  PessoaAux = (Pessoa*)CofQuery(usuarios,(void*)NomeInput,CompNome);

                                  if(PessoaAux != NULL){
                                      printf("Elemento Encontrado\n");
                                      printfCadastro(PessoaAux);
                                      system("pause");
                                  }else{
                                      printf("Elemento não encontrado\n");
                                      system("pause && cls || clear");
                                  }
                                  free(NomeInput);
                              }
                              if(opcao2 == 2){
                                  CpfInput = (char *)malloc(sizeof(char)*100);
                                  printf("\nDigite o CPF: ");
                                  fflush(stdin);
                                  fgets(CpfInput, 100, stdin);
                                  PessoaAux = (Pessoa*)CofQuery(usuarios,(void*)CpfInput,CompCPF);

                                  if(PessoaAux != NULL){
                                      printf("Elemento Encontrado\n");
                                      printfCadastro(PessoaAux);
                                      system("pause");
                                  }else{
                                      printf("Elemento não encontrado\n");
                                      system("pause && cls || clear");
                                  }
                                  free(CpfInput);
                              }
                          if(opcao2 == 0){
                              system("pause");
                          }
                      }
                  }else{
                      Vazio();
                  }
              }else{
                  ErroSupremo();
              }
              break;
/*  -- -- -- -- -- -- -- -- -- -- -- -- -- Remover Dados  -- -- -- -- -- -- -- -- -- -- -- -- -- */
          case 4:
				if(usuarios != NULL){
                  if(qtdPessoas > 0){
                  PessoaAux = AlocouTestou(1);
                    do{
                      system("cls");
                      Display_Menu();
                      printf("Digite o modelo de busca: \n1- Nome\n2- CPF\nopçao: ");
                      scanf("%d",&opcao2);
                          if(opcao2 == 1){
                              NomeInput = (char *)malloc(sizeof(char)*100);
                              printf("\nDigite o Nome: ");
                              fflush(stdin);
                              fgets(NomeInput, 100, stdin);

                              PessoaAux = (Pessoa*)CofRemove(usuarios,(void*)NomeInput,CompNome);
                              if(PessoaAux != NULL){
                                  qtdPessoas --;
                                  printf("Elemento Removido:\n");
                                  printfCadastro(PessoaAux);
                                  system("pause");
                                  free(PessoaAux);
                              }else{
                                  printf("Elemento não encontrado\n");
                                  system("pause && cls || clear");
                              }
                              free(NomeInput);
                          }
                          if(opcao2 == 2){
                              CpfInput = (char *)malloc(sizeof(char)*100);
                              printf("\nDigite o CPF: ");
                              fflush(stdin);
                              fgets(CpfInput, 100, stdin);

                              PessoaAux = (Pessoa*)CofRemove(usuarios,(void*)CpfInput,CompCPF);
                              if(PessoaAux != NULL){
                                  qtdPessoas --;
                                  printf("Elemento Removido:\n");
                                  printfCadastro(PessoaAux);
                                  system("pause");
                                  free(PessoaAux);
                              }else{
                                  printf("Elemento não encontrado\n");
                                  system("pause && cls || clear");
                              }
                              free(CpfInput);
                          }
                          if(opcao2 == 0){
                              system("pause");
                          }
                      }while(opcao2 != 1 && opcao2 != 2 && opcao2 != 0);
                  }else{
                      Vazio();
                  }
              }else{
                  ErroSupremo();
              }
              break;
/*  -- -- -- -- -- -- -- -- -- -- -- -- -- -- Mostrar Dados  -- -- -- -- -- -- -- -- -- -- -- -- -- -- */
          case 5:
				if(usuarios != NULL){
					if(qtdPessoas > 0){
						PessoaAux = (Pessoa*)CofGetFirst(usuarios); /* Pede o primeiro dado, para zerar o cur do cofo */
                      printf("Quantidade de pessoas: %d\n", qtdPessoas);
                      while(PessoaAux != NULL){
                          printfCadastro(PessoaAux);
                          PessoaAux = (Pessoa *)CofGetNext(usuarios);
                      }
                      system("pause");
					}else{
                      Vazio();
					}
              }else{
                  ErroSupremo();
              }
              break;
/*  -- -- -- -- -- -- -- -- -- -- -- --- Destruir cofo  -- -- -- -- -- -- -- -- -- -- -- --- */
          case 6:
              if(usuarios != NULL){
                  if(qtdPessoas > 0){
                      opcao2 = 0;
                      do{
                          system("cls || clear");
                          Display_Menu();
                          printf("O cofo possui elementos\nDestruí-lo mesmo assim?\n1 - Sim\n2 - Não\nopção: ");
                          scanf("%d", &opcao2);
                      }while(opcao2 != 1 && opcao2 != 2);
                  }
                  if(opcao2 == 1 || qtdPessoas == 0){
                      aux = CofDestroy(usuarios);
                      if(aux == True){
                          usuarios = NULL;
                          CofGerado = False;
                          Destruido();
                      }
					}else{
                      printf("Cofo não destruido\n\n");
                      system("pause");
                  }
              }else{
                  ErroSupremo();
              }
              break;
/*  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --- */
          default:
              Erro();
              break;
      }
	}
/*  -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --- */
	return 0;
}