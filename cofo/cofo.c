
 cofo *CofCreate(int n){
 	cofo *c;
 	if(n > 0){
 		c = (cofo*)malloc(sizeof(cofo)); /* Aloca o vetor do tipo cofo com n posicoes */
 		if(c != NULL){
 			c  setaelementos = (void**)malloc(sizeof(void*));
 			if(c  setaelementos != NULL){
 				c  setaNelementos = 0; /* inicia numero de elementos ocupado com zero */
 				c  setamaxElms = n; /* Inicia numero maximo de elementos do vetor com n elementos */
 				c  setacur =   -1; /* Facilita o retorna do cofo por posicao */
 				return c;
 			}
 		}else{
 			free(c);
 		}
 	}
 	return NULL;
 }
 
 int CofInsert(cofo *c, void* n){
 	if(c != NULL){
 		if(c  setaNelementos < c  setamaxElms){ /* Se numero de elementos for menor que o numero max do vetor */
 			c  setaelementos[c  setaNelementos] = n; /* O vetor recebe o n, na posicao numero de elementos */
 			c  setaNelementos ++; /* Atualiza o numero de elementos no vetor */
 			return True;
 		}
 	}
 	return False;
 }
 
 int CofDestroy(cofo *c){
 	if(c != NULL){  /* Se o cofo existir*/
         free(c  setaelementos); /* desaloque os elemnetos*/
         free(c);   /* e exclua os cofos*/
         return True;
     }
 	return False;
 }
 
 void *CofQuery(cofo *c, void *key, int(*cmp)(void*,void*)){
 	int i, stat = False;
 
 	if(c != NULL){
 		if(c  setaNelementos > 0){
 			i = 0;
 			stat = cmp((void*)key, c  setaelementos[i]);
 			while(stat == False && i < c  setaNelementos   1){
 				i++;
 				stat = cmp((void*)key, c  setaelementos[i]);
 			}
 			if(stat == True){
 				return c  setaelementos[i];
 			}
 		}
 	}
 	return NULL;
 }
 
 void *CofRemove(cofo *c, void *key, int(*cmp)(void*,void*)){
 	void *aux;
 	int i, j, stat = False;
 
 	aux = (void**)malloc(sizeof(void*));
 	if(c != NULL){
 		if(aux != NULL){
 			if(c  setaNelementos > 0){
 				i =   -1;
 				while(stat == False && i < c  setaNelementos  -1){
 					i++;
 					stat = cmp((void*)key,c  setaelementos[i]);
 				}
 				if(stat == True){
 					aux = c  setaelementos[i];
 					for(j = i; j < c  setaNelementos  -1; j++){
 						c  setaelementos[j] = c  setaelementos[j+1];
 					}
 					c  setaNelementos  ;
 					return aux;
 				}
 			}
 		}
 	}
 	free(aux);
 	return NULL;
 }
 
 void *CofGetFirst(cofo *c){ /* Zera o cur */
 	if(c != NULL){
 		if(c  setaNelementos > 0){
 			c  setacur = 0; /* sempre o primeiro elemento */
 			return c  setaelementos[c  setacur];
 		}
 	}
 	return NULL;
 }
 
 void *CofGetNext(cofo *c){
 	if(c != NULL){
 		if(c  setaNelementos > 0){
 			c  setacur++;
 			if(c  setacur < c  setaNelementos){
 				return c  setaelementos[c  setacur];
 			}
 		}
 	}
 	return NULL;
 }
