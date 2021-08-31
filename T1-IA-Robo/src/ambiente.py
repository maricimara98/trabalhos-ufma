import json
import os
import time
from random import *

from src.incinerador import Incinerador
from src.lixeira import Lixeira
from src.lixo import Lixo
from src.recicladora import Recicladora


class Ambiente:

#* INICIALIZACAO DO AMBIENTE
    def __init__(self, roboR1, roboR2):
        with open("configs/configs.json") as i:
            self.__config = json.load(i)

        self.lixeira_x = Lixeira(self.__config["lixo_x_y"], self.__config["lixo_x_x"], "X")
        self.lixeira_y = Lixeira(self.__config["lixo_y_y"], self.__config["lixo_y_x"], "Y")
        self.incinerador = Incinerador(self.__config["incinerador_y"], self.__config["incinerador_x"], "I")
        self.recicladora = Recicladora(self.__config["recicladora_y"], self.__config["recicladora_x"], "R")        
        self.lixo_quantidade = self.__config["lixo_quantidade"]
        self.SIZE = self.__config["SIZE"]
        self.size_x = self.__config["size_x"]
        self.size_y = self.__config["size_y"]
        self.roboR1 = roboR1
        self.roboR2 = roboR2
        self.lixo_list = []
        self.ambiente = [[]]
        self.qtd_i = 0
        self.qtd_r = 0

        self.ESQUERDA = self.__config["ESQUERDA"]
        self.DIREITA = self.__config["DIREITA"]
        self.EMCIMA = self.__config["EMCIMA"]
        self.EMBAIXO = self.__config["EMBAIXO"]

        self.LIMPO = self.__config["LIMPO"]

#* MAPEAMENTO DO ESPACO

    def construir(self):
        self.ambiente = [[self.LIMPO] * self.size_x for i in range(self.size_y)]

        for i in range(self.SIZE):
            for j in range(self.SIZE):
                if i == 0 or i == self.SIZE - 1:
                    if j ==0 or j== self.SIZE -1:
                        self.ambiente[i][j] = "*"    #CANTO
                    else:
                        self.ambiente[i][j] = "-"    #ANDAR
                elif j == 0 or j == self.size_x - 1:
                    self.ambiente[i][j] = "|"        #MURO

        self.ambiente[self.roboR1.x][self.roboR1.y] = self.roboR1
        self.ambiente[self.roboR2.y][self.roboR2.x] = self.roboR2

        self.ambiente[self.lixeira_x.y][self.lixeira_x.x] = self.lixeira_x
        self.ambiente[self.lixeira_y.y][self.lixeira_y.x] = self.lixeira_y

        self.ambiente[self.incinerador.y][self.incinerador.x] = self.incinerador

        self.ambiente[self.recicladora.y][self.recicladora.x] = self.recicladora

        lixo_list = self.lixo_gerador()
        for i in lixo_list:
            self.ambiente[i.y][i.x] = i
        return self.ambiente

#* GERACAO DE LIXO
    def lixo_gerador(self):
        coordenadas = []
        while len(coordenadas) < 40:
            rand = (randint(2, 19), randint(2, 19))
            if rand not in coordenadas:
                coordenadas.append(rand)

        for i in range(len(coordenadas)):
            self.lixo_list.append(
                Lixo(i, coordenadas[i][0], coordenadas[i][1],
                      choice(["i", "r"])))
        
        for i in self.lixo_list:
            if i.tipo == "i":
                self.qtd_i +=1
            else:
                self.qtd_r +=1

        return self.lixo_list

#* SENSORES
    def busca_lixo(self, direcao):

        if direcao == self.ESQUERDA:
            movimento = self.roboR1.x - 1

            if self.ambiente[self.roboR1.y][movimento] == self.LIMPO or self.ambiente[
                    self.roboR1.y][movimento] in self.lixo_list:
                self.roboR1.x -= 1

            return self.roboR1.y, self.roboR1.x

        if direcao == self.DIREITA:
            movimento = self.roboR1.x + 1

            if self.ambiente[self.roboR1.y][movimento] == self.LIMPO or self.ambiente[
                    self.roboR1.y][movimento] in self.lixo_list:
                self.roboR1.x += 1

            return self.roboR1.y, self.roboR1.x

        if direcao == self.EMCIMA:
            movimento = self.roboR1.y - 1

            if self.ambiente[movimento][self.roboR1.x] == self.LIMPO or self.ambiente[
                    movimento][self.roboR1.x] in self.lixo_list:
                self.roboR1.y -= 1

            return self.roboR1.y, self.roboR1.x

        if direcao == self.EMBAIXO:
            movimento = self.roboR1.y + 1

            if self.ambiente[movimento][self.roboR1.x] == self.LIMPO or self.ambiente[
                    movimento][self.roboR1.x] in self.lixo_list:
                self.roboR1.y += 1
            return self.roboR1.y, self.roboR1.x

#* ROBO R1 COLOCAR NAS LIXEIRAS
    def busca_lixeira(self, direcao):
        lixeira = False

        if direcao == self.ESQUERDA:
            movimento = self.roboR1.x - 1

            if self.ambiente[self.roboR1.y][movimento] == self.lixeira_x:
                lixeira = self.lixeira_x
            elif self.ambiente[self.roboR1.y][movimento] == self.lixeira_y:
                lixeira = self.lixeira_y
            elif self.ambiente[self.roboR1.y][movimento] == self.LIMPO:
                self.roboR1.x -= 1

            return self.roboR1.y, self.roboR1.x, lixeira

        if direcao == self.DIREITA:
            movimento = self.roboR1.x + 1

            if self.ambiente[self.roboR1.y][movimento] == self.lixeira_x:
                lixeira = self.lixeira_x
            elif self.ambiente[self.roboR1.y][movimento] == self.lixeira_y:
                lixeira = self.lixeira_y
            elif self.ambiente[self.roboR1.y][movimento] == self.LIMPO:
                self.roboR1.x += 1

            return self.roboR1.y, self.roboR1.x, lixeira

        if direcao == self.EMCIMA:
            movimento = self.roboR1.y - 1

            if self.ambiente[movimento][self.roboR1.x] == self.lixeira_x:
                lixeira = self.lixeira_x
            elif self.ambiente[movimento][self.roboR1.x] == self.lixeira_y:
                lixeira = self.lixeira_y
            elif self.ambiente[movimento][self.roboR1.x] == self.LIMPO:
                self.roboR1.y -= 1

            return self.roboR1.y, self.roboR1.x, lixeira

        if direcao == self.EMBAIXO:
            movimento = self.roboR1.y + 1

            if self.ambiente[movimento][self.roboR1.x] == self.lixeira_x:
                lixeira = self.lixeira_x
            elif self.ambiente[movimento][self.roboR1.x] == self.lixeira_y:
                lixeira = self.lixeira_y
            elif self.ambiente[movimento][self.roboR1.x] == self.LIMPO:
                self.roboR1.y += 1
            return self.roboR1.y, self.roboR1.x, lixeira

#* ROBO R2 REMOVER DAS LIXEIRAS
    def busca_lixeira_r2(self, direcao):
        lixeira = False

        if direcao == self.ESQUERDA:
            movimento = self.roboR2.x - 1

            if self.ambiente[self.roboR2.y][movimento] == self.lixeira_x:
                lixeira = self.lixeira_x
            elif self.ambiente[self.roboR2.y][movimento] == self.lixeira_y:
                lixeira = self.lixeira_y
            elif self.ambiente[self.roboR2.y][movimento] == self.LIMPO:
                self.roboR2.x -= 1

            return self.roboR2.y, self.roboR2.x, lixeira

        if direcao == self.DIREITA:
            movimento = self.roboR2.x + 1

            if self.ambiente[self.roboR2.y][movimento] == self.lixeira_x:
                lixeira = self.lixeira_x
            elif self.ambiente[self.roboR2.y][movimento] == self.lixeira_y:
                lixeira = self.lixeira_y
            elif self.ambiente[self.roboR2.y][movimento] == self.LIMPO:
                self.roboR2.x += 1

            return self.roboR2.y, self.roboR2.x, lixeira

        if direcao == self.EMCIMA:
            movimento = self.roboR2.y - 1

            if self.ambiente[movimento][self.roboR2.x] == self.lixeira_x:
                lixeira = self.lixeira_x
            elif self.ambiente[movimento][self.roboR2.x] == self.lixeira_y:
                lixeira = self.lixeira_y
            elif self.ambiente[movimento][self.roboR2.x] == self.LIMPO:
                self.roboR2.y -= 1

            return self.roboR2.y, self.roboR2.x, lixeira

        if direcao == self.EMBAIXO:
            movimento = self.roboR2.y + 1

            if self.ambiente[movimento][self.roboR2.x] == self.lixeira_x:
                lixeira = self.lixeira_x
            elif self.ambiente[movimento][self.roboR2.x] == self.lixeira_y:
                lixeira = self.lixeira_y
            elif self.ambiente[movimento][self.roboR2.x] == self.LIMPO:
                self.roboR2.y += 1
            return self.roboR2.y, self.roboR2.x, lixeira

#* ROBO R2 ENCONTRAR INCINERADOR
    def busca_incinerador(self, direcao):
        incinerador = False

        if direcao == self.ESQUERDA:
            movimento = self.roboR2.x - 1

            if self.ambiente[self.roboR2.y][movimento] == self.incinerador:
                incinerador = self.incinerador
            elif self.ambiente[self.roboR2.y][movimento] == self.LIMPO:
                self.roboR2.x -= 1

            return self.roboR2.y, self.roboR2.x, incinerador

        if direcao == self.DIREITA:
            movimento = self.roboR2.x + 1

            if self.ambiente[self.roboR2.y][movimento] == self.incinerador:
                incinerador = self.incinerador
            elif self.ambiente[self.roboR2.y][movimento] == self.LIMPO:
                self.roboR2.x += 1

            return self.roboR2.y, self.roboR2.x, incinerador

        if direcao == self.EMCIMA:
            movimento = self.roboR2.y - 1

            if self.ambiente[movimento][self.roboR2.x] == self.incinerador:
                incinerador = self.incinerador
            elif self.ambiente[movimento][self.roboR2.x] == self.LIMPO:
                self.roboR2.y -= 1

            return self.roboR2.y, self.roboR2.x, incinerador

        if direcao == self.EMBAIXO:
            movimento = self.roboR2.y + 1

            if self.ambiente[movimento][self.roboR2.x] == self.incinerador:
                incinerador = self.incinerador
            elif self.ambiente[movimento][self.roboR2.x] == self.LIMPO:
                self.roboR2.y += 1
            return self.roboR2.y, self.roboR2.x, incinerador

#* ROBO R2 ENCONTRAR RECICLADOR
    def busca_reciclador(self, direcao):
        reciclador = False

        if direcao == self.ESQUERDA:
            movimento = self.roboR2.x - 1

            if self.ambiente[self.roboR2.y][movimento] == self.recicladora:
                reciclador = self.recicladora
            elif self.ambiente[self.roboR2.y][movimento] == self.LIMPO:
                self.roboR2.x -= 1

            return self.roboR2.y, self.roboR2.x, reciclador

        if direcao == self.DIREITA:
            movimento = self.roboR2.x + 1

            if self.ambiente[self.roboR2.y][movimento] == self.recicladora:
                reciclador = self.recicladora
            elif self.ambiente[self.roboR2.y][movimento] == self.LIMPO:
                self.roboR2.x += 1

            return self.roboR2.y, self.roboR2.x, reciclador

        if direcao == self.EMCIMA:
            movimento = self.roboR2.y - 1

            if self.ambiente[movimento][self.roboR2.x] == self.recicladora:
                reciclador = self.recicladora
            elif self.ambiente[movimento][self.roboR2.x] == self.LIMPO:
                self.roboR2.y -= 1

            return self.roboR2.y, self.roboR2.x, reciclador

        if direcao == self.EMBAIXO:
            movimento = self.roboR2.y + 1

            if self.ambiente[movimento][self.roboR2.x] == self.recicladora:
                reciclador = self.recicladora
            elif self.ambiente[movimento][self.roboR2.x] == self.LIMPO:
                self.roboR2.y += 1
            return self.roboR2.y, self.roboR2.x, reciclador

#* VERIFICACAO DO AMBIENTE

    def verificaSucesso(self):
        for i in range(0, self.size_x):
            for j in range(0, self.size_y):
                if self.ambiente[i][j] == "i" or self.ambiente[i][j] == "r" :
                    return False
        return True

#* VISUALIZACAO DO AMBIENTE
    def mostrarAmbiente(self):

        clear = lambda: os.system('clear')
        clear()
        for i in range(self.SIZE):
            print("\n", end="")
            for j in range(self.SIZE):
                print(self.ambiente[i][j], end="")

        print("\n")
        print("roboR1 ({},{}),\nroboR1_CONT:{}".
              format(self.roboR1.x, self.roboR1.y, len(self.roboR1.conteudo)))
        print(" ")
        tipo = 0
        if self.roboR2.conteudo:
            tipo = self.roboR2.conteudo[0].tipo
        
        print("LIXEIRA_X: {},\nLIXEIRA_Y:{}\n".format(len(self.lixeira_x.conteudo),
            len(self.lixeira_y.conteudo),) )

        print("roboR2 ({},{}),\nroboR2_CONT:{}, TIPO:{},\n\nQTDR:{} RECICLADOR:{},\nQTDI:{}, INCINERADOR:{}".
              format(self.roboR2.x, self.roboR2.y, len(self.roboR2.conteudo), tipo, self.qtd_r,
                     len(self.recicladora.conteudo), self.qtd_i, len(self.incinerador.conteudo)))
        print(" ")
        
        print("LIXO:{}".format(len(self.lixo_list)))
