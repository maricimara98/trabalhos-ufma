#include <stdio.h>
#include <stdlib.h>

/*                           Op��es                           */
void Display_Menu(){
    system("cls");
    puts("1   Criar lista");
    puts("2   Destruir lista");
    puts("3   Inserir Dados");
    puts("4   Consultar dados do usu�rio / CPF");
    puts("5   Remover dados do usu�rio / CPF");
    puts("6   Mostrar todos os dados");
    puts("0   Sair\n");
}

/*                           Mensagens                           */

void SucessoMaster(){
    puts("\n        (> � � <)");
    puts("        ( =�!�= )");
    puts("        (,,) (,,)         ");
    puts("Lista Criada com Sucesso!");
    puts("                          \n");
    system("pause && cls || clear");
}

void ErroMaster(){
    puts("\n     (> � � <)");
    puts("     ( =�!�= )");
    puts("     (,,) (,,)     ");
    puts("Lista j� Criada!");
    puts("                   \n");
     system("pause && cls || clear");
}

void ErroSupremo(){
    puts("\n     (> � � <)");
    puts("     ( =�!�= )");
    puts("     (,,) (,,)     ");
    puts("Lista n�o Criada!");
    puts("                   \n");
    system("pause && cls || clear");
}

void Erro(){
    puts("\nOp��o Inv�lida!\n");
    system("pause && cls || clear");
}

void Vazio(){
    puts("\nA lista est� vazia!\n");
    system("pause && cls || clear");
}

void ErroAlocacao(){
    puts("\nN�o adicionado, mem�ria cheia!\n");
    system("pause && cls || clear");
}

void Adicionado(){
    puts("\nAdicionado!\n");
    system("pause && cls || clear");
}

void NEncontrado(){
    puts("\n\tCPF Nao encontrado");
    system("pause && cls || clear");
}
void Removido(){
    puts("\nRemovido com sucesso!\n");
}

void Destruido(){
    puts("\n        (> � � <)");
    puts("        ( =x!x= )");
    puts("        (,,) (,,)         ");
    puts("Destru�do com Sucesso!");
    puts("                          ");
    system("pause && cls || clear");
}

void NDestruido(){
    puts("Remova o(s) cadastro(s) da Lista");
    system("pause && cls || clear");
}
