"""Modelagem do Tweet."""
import csv
import json

from aquisicao import Tweeter
from modelo_vetorial import ModeloVetorial
from preprocessamento import Preparacao_Indexacao

def __main__():

    lista_documentos = []
    
    novos_docs = str(input('Selecionar novos documentos?'))
    if novos_docs == 's' or  novos_docs == 'S':
        tema = str(input('Escolha o tema: '))
        qtd = int(input('Quantidade de documentos: '))
        aquisicao = Tweeter(tema, qtd)
        print(aquisicao)

    with open("data/tweets.csv", encoding="utf-8") as file:
        lista = csv.reader(file, delimiter=":", lineterminator=",\n")
        for termo in lista:
            lista_documentos.append((termo[0]))   # minusculo

    delimiters = ",.!? "
    
    stopwords = []

    with open("docs/stopwords-pt.json") as json_file:
        words = json.load(json_file)
        stopwords.append(words)

    preparara_docs = Preparacao_Indexacao(delimiters, stopwords)
    modelo_vet = ModeloVetorial(preprocessor=preparara_docs, docs=lista_documentos)

    doc_q = str(input('Doc q: '))
    
    print(modelo_vet.consulta(doc_q))
    

if __name__ == "__main__":
    __main__()
