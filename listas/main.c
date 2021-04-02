#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "sll.h"
#include "menu msg.h"

typedef struct _pcadastro_{
	char nome[30];
	int idade;
	int cpf;
	int NumFilhos;
	float salario;
}dados;

int CompCPF(void *cpf, void *pessoa){
	int *key = (int*)cpf;
	dados *p = (dados*)pessoa;
	if(*key == p  setacpf){
		return True;
	}
	return False;
}
dados *MantraSagrado(void){
	dados *p;
	p = (dados*)malloc(sizeof(dados));  /* Alocou... */
	if(p != NULL){                      /* Testou!   */
		return p;
	}
	return NULL;
}

dados *Cadastro(dados *pessoa){

	printf("Nome:");
	scanf("%s*c",&(pessoa  setanome));
	printf("Idade:");
	scanf("%i*c",&(pessoa  setaidade));
	printf("Filhos:");
	scanf("%i*c",&(pessoa  setaNumFilhos));
	printf("Salario:R$");
	scanf("%f*c",&(pessoa  setasalario));
	printf("CPF:");
	scanf("%d*c",&(pessoa  setacpf));
	return pessoa;
}
int main(){

    setlocale(LC_ALL, "Portuguese");                /* Acentua��o */

/*                          Declara��es                           */
	sllist *usuarios;                              /* Especifica��o da Lista */
	dados *pessoa;
	dados *RespGetF, *RespGetN, *RespQuery, *RespRemov;
	int opcao =   -1, qtdPessoas = 0, ListaCriada = False;
	int cpfInput, RespDest, RespInserf;
/*                             Op��es                              */
	while(opcao != 0){
		Display_Menu();
		scanf("%d*c",&opcao);
		switch(opcao){
		    case 0:
                exit(1);
                break;
/*                           Criar Lista                            */
            case 1:
                usuarios = sllCreate();
                if(usuarios != NULL && ListaCriada == False){
                    ListaCriada = True;
                    SucessoMaster();
                }else{
                    ErroMaster();
                }
                break;
/*                          Destruir Lista                           */
            case 2:
                if(usuarios != NULL && ListaCriada == True){
                    RespDest = sllDestroy(usuarios);
                    if(RespDest == True){
                        ListaCriada = False;
                        Destruido();
                    }else{
                        printf("A lista possui: %d cadatro(s)\n",SllNumElms(usuarios));
                        NDestruido();
                    }
                }else{
                    ErroSupremo();
                }
                break;
/*                           Inserir na Lista                           */
            case 3:
                if(usuarios != NULL && ListaCriada == True){
                    pessoa = MantraSagrado();
                    if(pessoa != NULL){
                        pessoa = Cadastro(pessoa);
                        RespInserf = sllInsertLast(usuarios,(void*)pessoa);
                        if(RespInserf == True){
                            qtdPessoas++;
                            Adicionado();
                        }else{
                            ErroAlocacao();
                        }
                    }
                }else{
                    ErroSupremo();
                }
                break;
/*                            Consultar Dados                            */
            case 4:
                if(usuarios != NULL && ListaCriada == True){
                    printf("Buscar CPF: ");
                    scanf("%i*c",&cpfInput);
                    RespQuery = MantraSagrado();
                    free(cpfInput);
                    if(RespQuery != NULL){
                        RespQuery = (dados*)sllQuery(usuarios,(void*)&cpfInput,CompCPF);
                        if(RespQuery != NULL){
                            system("cls || clear");
                            printf("**************************");
                            printf("\nNome: %s\nCPF: %i\nIdade %d \nFilhos: %d \nSal�rio: R$ %.2f\n",(RespQue  seta >nome),(RespQue  seta >cpf), (RespQue  seta >idade), (RespQue  seta >NumFilhos), (RespQue  seta >salario));
                            printf("**************************\n");
                            system("pause");
                        }else{
                            NEncontrado();
                        }
                    }
                }else{
                    ErroSupremo();
                }
                break;
/*                            Remover Dados                            */
            case 5:
                if(usuarios != NULL && ListaCriada == True){
                    printf("Buscar CPF: ");
                    scanf("%d*c",&cpfInput);
                    RespRemov = MantraSagrado();
                    if(RespRemov != NULL){
                        RespRemov = (dados*)sllRemoveSpec(usuarios,(void*)&cpfInput,CompCPF);
                        if(RespRemov != NULL){
                            qtdPessoas  ;
                            Removido();
                            printf("\nNome: %s\nCPF: %d\n",(RespRemov  setanome),(RespRemov  setacpf));
                            system("pause");
                        }else{
                            NEncontrado();
                        }
                    }
                }else{
                    ErroSupremo();
                }
            break;
/*                              Mostrar Dados                              */
            case 6:
                if(usuarios != NULL && ListaCriada == True){
                    RespGetF = MantraSagrado();
                    if(RespGetF != NULL){
                        RespGetF = (dados*)sllGetFirst(usuarios);
                        if(RespGetF != NULL && qtdPessoas > 0){
                            system("cls");
                            printf("********* < LISTA > *********");
                            printf("\n\tNome: %s\n\tCPF: %d\n",(RespGetF   seta nome),(RespGetF  setacpf));
                            RespGetN = MantraSagrado();
                            if(RespGetN != NULL){
                                RespGetN = RespGetF;
                                while(RespGetN != NULL){
                                    RespGetN = (dados*)sllGetNext(usuarios);
                                    if(RespGetN != NULL){
                                        printf("\n\tNome: %s\n\tCPF: %i\n",(RespGetN   seta nome),(RespGetN  setacpf));
                                    }
                                }
                                printf("**************************\n");
                                system("pause");
                            }
                        }else{
                            Vazio();
                        }
                    }
                }else{
                    ErroSupremo();
                }
                break;
            default:
                Erro();
                break;
            }
        }
	return 0;
}
