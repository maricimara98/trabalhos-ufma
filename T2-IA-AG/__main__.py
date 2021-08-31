#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""Mistura de tristeza, angustia e revolta de Thiago."""

import csv

from algoritmo_genetico import AlgoritmoGenetico
from produto import Produto


def __main__():

    lista_produtos = []

    with open("produtos.csv", encoding="utf-8") as file:
        lista = csv.reader(file, delimiter=",")
        for produto in lista:
            lista_produtos.append(
                Produto(produto[0], (int(produto[1])), (int(produto[2])))
            )

    nomes = []
    valores = []
    pesos = []

    for produto in lista_produtos:
        nomes.append(produto.nome)
        valores.append(produto.valor)
        pesos.append(produto.peso)

    limite = 80
    tamanho_populacao = len(lista_produtos)
    taxa_mutacao = 0.01
    numero_geracoes = 10 * tamanho_populacao

    algoritmo_genetico = AlgoritmoGenetico(tamanho_populacao)
    resultado = algoritmo_genetico.__solucao__(
        taxa_mutacao, numero_geracoes, valores, pesos, limite
    )

    for i, item in enumerate(lista_produtos):
        if resultado[i] == "1":
            print(
                "Produto: %s R$ %s " % (
                    item.nome, item.valor)
            )
    print("-----------------")
    print("\nThiago ficou sem:\n")
    for i, item in enumerate(lista_produtos):
        if resultado[i] == "0":
            print(
                "Produto: %s R$ %s " % (
                    item.nome, item.valor)
            )


if __name__ == "__main__":
    __main__()
