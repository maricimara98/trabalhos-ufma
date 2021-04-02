#ifndef _MENU_H_INCLUDED
#define _MENU_C_INCLUDED

#ifdef _menu_h
    void Display_Menu();
    void SucessoMaster();
    void ErroMaster();
    void ErroSupremo();
    void Erro();
    void Vazio();
    void ErroAlocacao();
    void Adicionado();
    void Removido();
    void Destruido();

#else
    extern void Display_Menu();
    extern void SucessoMaster();
    extern void ErroMaster();
    extern void ErroSupremo();
    extern void Erro();
    extern void Vazio();
    extern void ErroAlocacao();
    extern void Adicionado();
    extern void Removido();
    extern void Destruido();

#endif /* _MENU-MSG_C */
#endif /* _MENU-MSG_H */