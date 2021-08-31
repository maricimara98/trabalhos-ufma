"""Indivíduo de um Algoritmo Genético."""

from random import random


class Individuo:
    """
    Employee class is used to hold employee object data.

    Methods:
        __init__(self, valores, pesos, limite_valores, geracao=0)
        __fitness__(self)
        __crossover__(self, outro_individuo)
        __mutacao__(self, taxa_mutacao)
    """

    def __init__(self, valores, pesos, limite_valores, geracao=0):

        self.valores = valores
        self.pesos = pesos
        self.limite_valores = limite_valores
        self.geracao = geracao
        self.nota_fitness = 0
        self.valor_usado = 0
        self.cromossomo = []

        for i in range(len(valores)):
            
            i += 1
            if random() < 0.5:
                self.cromossomo.append("0")
            else:
                self.cromossomo.append("1")

    def __fitness__(self):
        nota = 0
        soma_valores = 0

        # pylint: disable=consider-using-enumerate
        for i in range(len(self.cromossomo)):
            if self.cromossomo[i] == '1':
                nota += self.pesos[i]
                soma_valores += self.valores[i]
        if soma_valores > self.limite_valores:
            nota = 1
            
        self.nota_fitness = nota
        self.valor_usado = soma_valores

    def __crossover__(self, outro_individuo):
        corte = round(random() * len(self.cromossomo))

        filho1 = outro_individuo.cromossomo[0:corte] + self.cromossomo[corte::]
        filho2 = self.cromossomo[0:corte] + outro_individuo.cromossomo[corte::]

        filhos = [
            Individuo(self.valores, self.pesos, self.limite_valores, self.geracao + 1),
            Individuo(self.valores, self.pesos, self.limite_valores, self.geracao + 1),
        ]
        filhos[0].cromossomo = filho1
        filhos[1].cromossomo = filho2
        
        return filhos

    def __mutacao__(self, taxa_mutacao):
        # pylint: disable=consider-using-enumerate
        for i in range(len(self.cromossomo)):
            if random() < taxa_mutacao:
                if self.cromossomo[i] == "1":
                    self.cromossomo[i] = "0"
                else:
                    self.cromossomo[i] = "1"
        return self
