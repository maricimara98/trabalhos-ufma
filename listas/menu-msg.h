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
    void NDestruido();

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
    extern void NDestruido();

#endif /* _MENU MSG_C */
#endif /* _MENU MSG_H */
