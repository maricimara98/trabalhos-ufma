#!/usr/bin/env python3
# -*- coding: utf-8 -*-
""" Algoritmo Genético."""

from random import random

from individuo import Individuo


class AlgoritmoGenetico:
    """
    Employee class is used to hold employee object data.

    Methods:Peso
        __init__(self, valores, pesos, limite_valores, geracao=0)
        __inicializa_populacao__(self, valores, pesos, limite_valores)
        __ordena_populacao__(self)
        __mutacao__(self, taxa_mutacao)
        __melhor_indiPesoviduo__(self, individuo)
        __soma_avaliacoes__(self)
        __selecao__(self, soma_fitness)
        __visualiza_geracao__(self)
        __solucao__(self, taxa_mutacao, numero_geracoes, valores, pesos, limite_valores)
    """

    def __init__(self, tamanho_populacao):
        self.tamanho_populacao = tamanho_populacao
        self.populacao = []
        self.geracao = 0
        self.melhor_solucao = 0
        self.lista_solucoes = []

    def __inicializa_populacao__(self, valores, pesos, limite_valores):
        for i in range(self.tamanho_populacao):
            i += 1
            self.populacao.append(Individuo(valores, pesos, limite_valores))
        self.melhor_solucao = self.populacao[0]

    def __ordena_populacao__(self):
        self.populacao = sorted(
            self.populacao, key=lambda populacao: populacao.nota_fitness, reverse=True
        )

    def __melhor_individuo__(self, individuo):
        if individuo.nota_fitness > self.melhor_solucao.nota_fitness:
            self.melhor_solucao = individuo

    def __soma_avaliacoes__(self):
        soma = 0
        for individuo in self.populacao:
            soma += individuo.nota_fitness
        return soma

    def __selecao__(self, soma_fitness):
        pai = -1
        valor_sorteado = random() * soma_fitness
        soma = 0
        i = 0
        while i < len(self.populacao) and soma < valor_sorteado:
            soma += self.populacao[i].nota_fitness
            pai += 1
            i += 1
        return pai

    def __visualiza_geracao__(self):
        melhor = self.populacao[0]
        print(
            "Geração:%s -> Avaliação: %s - Valor: %s  - Cromossomo: %s"
            % (
                self.populacao[0].geracao,
                melhor.nota_fitness,
                melhor.valor_usado,
                melhor.cromossomo,
            )
        )

    # pylint: disable=too-many-arguments
    def __solucao__(self, taxa_mutacao, numero_geracoes, valores, pesos, limite_valores):
        self.__inicializa_populacao__(valores, pesos, limite_valores)

        for individuo in self.populacao:
            individuo.__fitness__()

        self.__ordena_populacao__()
        self.melhor_solucao = self.populacao[0]
        self.lista_solucoes.append(self.melhor_solucao.nota_fitness)

        self.__visualiza_geracao__()

        for geracao in range(numero_geracoes):
            soma_fitness = self.__soma_avaliacoes__()
            nova_populacao = []

            for individuos_gerados in range(0, self.tamanho_populacao, 2):
                genitor_1 = self.__selecao__(soma_fitness)
                genitor_2 = self.__selecao__(soma_fitness)

                filhos = self.populacao[genitor_1].__crossover__(
                    self.populacao[genitor_2])

                nova_populacao.append(filhos[0].__mutacao__(taxa_mutacao))
                nova_populacao.append(filhos[1].__mutacao__(taxa_mutacao))

            self.populacao = list(nova_populacao)

            for individuo in self.populacao:
                individuo.__fitness__()

            self.__ordena_populacao__()

            self.__visualiza_geracao__()

            melhor = self.populacao[0]
            self.lista_solucoes.append(melhor.nota_fitness)
            self.__melhor_individuo__(melhor)

        print("-------------------------------------------------------")
        print("\nMelhor solução -> Geração: %s - Avaliação: %s  - Valor: %s \nCromossomo: %s" %
              (self.melhor_solucao.geracao,
               self.melhor_solucao.nota_fitness,
               self.melhor_solucao.valor_usado,
               self.melhor_solucao.cromossomo))
        print("-------------------------------------------------------")
        pago = self.melhor_solucao.valor_usado
        troco = limite_valores - pago

        print("\nValor pago: R$ %s \nTroco: R$ %s \n" % (pago, troco))

        return self.melhor_solucao.cromossomo
