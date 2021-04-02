#include <stdio.h>
#include <stdlib.h>

/*-------------------------- Opções --------------------------*/
void Display_Menu(){
    system("cls");
    puts("1 - Criar cofo");
    puts("2 - Inserir no cofo");
    puts("3 - Consultar Dados");
    puts("4 - Remover dados do usuário");
    puts("5 - Mostrar todos os dados");
    puts("6 - Destruir cofo");
    puts("0 - Sair\n");
}

/*-------------------------- Mensagens --------------------------*/

void SucessoMaster(){
    puts("\n        (> ” ” <)");
    puts("        ( =’!’= )");
    puts("--------(,,)-(,,)---------");
    puts("Cofo Criado com Sucesso!");
    puts("--------------------------\n");
    system("pause && cls || clear");
}

void ErroMaster(){
    puts("\n     (> ” ” <)");
    puts("     ( =’!’= )");
    puts("-----(,,)-(,,)-----");
    puts("Cofo já Criado!");
    puts("-------------------\n");
     system("pause && cls || clear");
}

void ErroSupremo(){
    puts("\n     (> ” ” <)");
    puts("     ( =’!’= )");
    puts("-----(,,)-(,,)-----");
    puts("Cofo não Criado!");
    puts("-------------------\n");
    system("pause && cls || clear");
}

void Erro(){
    puts("\nOpção Inválida!\n");
    system("pause && cls || clear");
}

void Vazio(){
    puts("\nO cofo está vazio!\n");
    system("pause && cls || clear");
}

void ErroAlocacao(){
    puts("\nMemória cheia!\n");
    system("pause && cls || clear");
}

void Adicionado(){
    puts("\nAdicionado!\n");
    system("pause && cls || clear");
}

void Removido(){
    puts("\nRemovido com sucesso!\n");
}

void Destruido(){
    puts("\n        (> ” ” <)");
    puts("        ( =x!x= )");
    puts("--------(,,)-(,,)---------");
    puts("Destruido com Sucesso!");
    puts("--------------------------");
    system("pause && cls || clear");
}

