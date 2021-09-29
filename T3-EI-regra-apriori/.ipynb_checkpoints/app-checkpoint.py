#!/usr/bin/env python
"""
a simple implementation of Apriori algorithm by Python.
"""
import numpy as np
import pandas as pd
from mlxtend.preprocessing import TransactionEncoder
from mlxtend.frequent_patterns import association_rules
from mlxtend.frequent_patterns import apriori, association_rules

#https://www.kaggle.com/irfanasrullah/groceries
df = pd.read_csv('groceries - groceries.csv', usecols = [1,2,3,4,5,6,7,8,9,10,11,12,
                                                         13,14,15,16,17,18,19,20,21,
                                                         22,23,24,25,26,27,28,29,30,
                                                         31,32])
df.replace(np.nan, 0, inplace = True)
df.head

def removeOcorrenciasLista(_list, value):
    return list(filter(lambda x: x != value, _list))


listar_transacoes = []

for index, row in df.iterrows():
    transacao = row.values.tolist()
    transacao = removeOcorrenciasLista(transacao, 0)
    listar_transacoes.append(transacao)

# primeiras 5 transações
print(listar_transacoes[0:5])

te = TransactionEncoder()

te_ary = te.fit(listar_transacoes).transform(listar_transacoes)
df = pd.DataFrame(te_ary, columns=te.columns_)

frequencia_itens_conjunto = apriori(df, min_support = 0.01, use_colnames = True)

frequencia_itens_conjunto.sort_values(by=['support'], ascending = False).head(1)

frequencia_itens_conjunto.sort_values(by=['support'], ascending = True).head(1)

frequencia_itens_conjunto = apriori(df, min_support = 0.01, use_colnames = True)

regras = association_rules(frequencia_itens_conjunto, metric="confidence", min_threshold=0.3)
len(regras.index)
regras.sort_values(by=['confidence'], ascending = False).drop(['antecedent support', 'consequent support', 'leverage', 'conviction'], axis=1).head(5)
regras.sort_values(by=['confidence'], ascending = True).drop(['antecedent support', 'consequent support', 'leverage', 'conviction'], axis=1).head(5)

regras = association_rules(frequencia_itens_conjunto, metric="confidence", min_threshold=0.5)
len(regras.index)
regras.sort_values(by=['confidence'], ascending = False).drop(['antecedent support', 'consequent support', 'leverage', 'conviction'], axis=1).head(5)
regras.sort_values(by=['confidence'], ascending = True).drop(['antecedent support', 'consequent support', 'leverage', 'conviction'], axis=1).head(5)

regras = association_rules(frequencia_itens_conjunto, metric="confidence", min_threshold=0.7)
len(regras.index)
regras.sort_values(by=['confidence'], ascending = False).drop(['antecedent support', 'consequent support', 'leverage', 'conviction'], axis=1).head(5)
regras.sort_values(by=['confidence'], ascending = True).drop(['antecedent support', 'consequent support', 'leverage', 'conviction'], axis=1).head(5)

regras = association_rules(frequencia_itens_conjunto, metric="lift", min_threshold=1)
