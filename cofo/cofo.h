#ifndef COFO_H_INCLUDED
#define COFO_H_INCLUDED
#define True 1
#define False 0
#define _cofo_c

typedef struct _colecao_{ /* tipo de estrutura que sera usado para adc as pessoas */
	void** elementos; /* elemento do vetor */
	int maxElms; /* numero maximo de elementos no vetor. */
	int Nelementos; /* numero de elementos ocupado no max. */
	int cur; /* apontador */
}cofo;

#ifdef _cofo_c

	cofo *CofCreate(int n);
	int CofInsert(cofo *c, void* n);
	int CofDestroy(cofo *c);
	void *CofQuery(cofo *c, void *key, int(*cmp)(void *,void *));
	void *CofRemove(cofo *c, void *key, int(*cmp)(void *,void *));
	void *CofGetNext(cofo *c);
	void *CofGetFirst(cofo *c);

#else

	extern cofo *CofCreate(int n);
	extern int CofInsert(cofo *c, void* n);
	extern int CofDestroy(cofo *c);
	extern void *CofQuery(cofo *c, void *key, int(*cmp)(void *, void *));
	extern void *CofRemove(cofo *c, void *key, int(*cmp)(void *,void *));
	extern void *CofGetNext(cofo *c);
	extern void *CofGetFirst(cofo *c);
#endif

#endif /* COFO_H_INCLUDED */

