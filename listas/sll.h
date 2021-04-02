#ifndef _sll_h
#define _sll_h
#define True 1
#define False 0

typedef struct _sllnode_;
typedef struct _sllist_ sllist;

#ifdef _sll_c
	sllist *sllCreate();
	int sllInsertLast(sllist *l, void *data);
	int sllDestroy(sllist *l);
	int sllNumElms(sllist *l);
	void *sllQuery(sllist *l, void *key, int(*cmp)(void *,void *));
	void *sllRemoveSpec(sllist *l, void *key, int(*cmp)(void*,void*));
	void *sllGetNext(sllist *l);
	void *sllGetFirst(sllist *l);
#else
	extern sllist *sllCreate();
	extern int sllInsertLast(sllist *l, void *data);
	extern int sllDestroy(sllist *l);
	extern int sllNumElms(sllist *l);
	extern void *sllQuery(sllist *l, void *key, int(*cmp)(void *,void *));
	extern void *sllRemoveSpec(sllist *l, void *key, int(*cmp)(void*,void*));
	extern void *sllGetNext(sllist *l);
	extern void *sllGetFirst(sllist *l);
#endif
#endif
