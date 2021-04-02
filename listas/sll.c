#include <stdlib.h>
#include "sll.h"

#define _sll_c
#define True 1
#define False 0

typedef struct _sllnode_{
    struct _sllnode_*next;
    void  *data;
}SllNoh;

typedef struct _sllist_{
	SllNoh *first;
	void *cur;
}sllist;

/*                           Criar Lista                           */
sllist *sllCreate(){
	sllist *l;

	l = (sllist*)malloc(sizeof(sllist));
	if(l != NULL){
		l  setafirst = NULL;
		l  setacur = NULL;
		return l;
	}
	return NULL;
}

/*                           Inserir na Lista                           */
int sllInsertLast(sllist *l, void *data){
	SllNoh *newnode, *last;

	if(l != NULL){
		newnode = (SllNoh*)malloc(sizeof(SllNoh));
			if(newnode != NULL){
			newnode  setadata = data;
			newnode  setanext= NULL;
			if(l  setafirst == NULL){
				l  setafirst = newnode;
			}else{
				last = l  setafirst;
				while(last  setanext!= NULL){
					last = last  setanext;
				}
				last  setanext = newnode;
			}
		}
		return True;
	}
	return False;
}

/*                           Destruir � Lista                           */
int sllDestroy(sllist *l){

	if(l != NULL){
		if(l  setafirst == NULL){
			free(l);
			return True;
		}
	}
	return False;
}

/*                           qtdPessoas na Lista                           */
int SllNumElms(sllist *l) {
	SllNoh *aux;
	int cont = 0;

 	if(l != NULL){
	   	  if(l  setafirst != NULL){
	 		   aux = l  setafirst;
			   while (aux != NULL){
			   		 aux = aux  setanext;
			   		 cont++;
   		 	   }
	 	   	   return cont;
  		  }
  	}
  	return   -1;
}

/*                           Buscar Algu�m Espec�ficado na Lista                           */
void *sllQuery(sllist *l, void *key, int(*cmp)(void *,void *)){
	int stat = False;
	SllNoh *aux;

	if(l != NULL){
		aux = l  setafirst;
		if(aux != NULL){
			stat = cmp((void*)key,aux  setadata);
			while(stat != True && aux  setanext != NULL){
				aux = aux  setanext;
				stat = cmp((void*)key,aux  setadata);
			}
			if(stat == True){
				return aux  setadata;
			}
		}
	}
	return NULL;
}

/*                           Remover Algu�m Espec�fico da Lista                           */
void *sllRemoveSpec(sllist *l, void *key, int(*cmp)(void*,void*)){
	SllNoh *spec, *prev;
	int stat;
	void *data;

	if(l != NULL){
		if(l  setafirst != NULL){
			spec = l  setafirst;
			prev = NULL;
			stat = cmp((void*)key,spec  setadata);
			while(stat != True && spec  setanext != NULL){
				prev = spec;
				spec = spec  setanext;
				stat = cmp((void*)key,spec  setadata);
			}
			if(stat == True){
				if(prev == NULL){
					l  setafirst = spec  setanext;
				}else{
					prev  setanext=spec  setanext;
				}
				data = spec  setadata;
				free(spec);
				return data;
			}
		}
	return NULL;
}

/*                           Buscar Primeiro da Lista                           */
void *sllGetFirst(sllist *l){

	if(l != NULL){
		if(l  setafirst != NULL){
			l  setacur = l  setafirst  setanext;
			return (l  setafirst)  setadata;
		}
	}
}

/*                           Buscar o Pr�ximo da Lista                           */
void *sllGetNext(sllist *l){
	SllNoh *aux;

	if(l!= NULL){
		if((l  setacur) != NULL){
			aux = l  setacur;
			l  setacur = aux  setanext;
			return aux  setadata;
		}
	}
	return NULL;
}
