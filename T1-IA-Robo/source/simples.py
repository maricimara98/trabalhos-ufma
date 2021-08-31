import json
import os
import time
from random import *

from src.incinerador import Incinerador
from src.lixeira import Lixeira
from src.lixo import Lixo


class Agente:
#* AGENTE REATIVO SIMPLES
    def reativo_simples(self):
        while self.lixo_list or self.roboR1.conteudo or self.lixeira_x.conteudo or self.lixeira_y.conteudo or self.roboR2.conteudo:

            if not self.roboR1.conteudo:
                direcao = choice([self.ESQUERDA, self.DIREITA, self.EMCIMA, self.EMBAIXO,])

                old_x = self.roboR1.x
                old_y = self.roboR1.y

                y, x = self.busca_lixo(direcao)
                
                if self.ambiente[y][x] in self.lixo_list:
                    lixo = self.ambiente[y][x]
                    self.roboR1.conteudo.append(lixo)
                    self.lixo_list.remove(lixo)
                self.ambiente[y][x] = self.roboR1
                self.ambiente[old_y][old_x] = " "
                self.mostrarAmbiente()

            elif self.roboR1.conteudo:
                direcao = choice([self.ESQUERDA, self.DIREITA, self.EMCIMA, self.EMBAIXO,])

                old_x = self.roboR1.x
                old_y = self.roboR1.y

                y, x, lixeira = self.busca_lixeira(direcao)

                if lixeira:
                    lixo = self.roboR1.conteudo[0]
                    lixeira.conteudo.append(lixo)
                    self.roboR1.conteudo.remove(lixo)
                self.ambiente[y][x] = self.roboR1
                self.ambiente[old_y][old_x] = " "
                self.mostrarAmbiente()
                
            if not self.roboR2.conteudo:
                direcao = choice([self.ESQUERDA, self.DIREITA, self.EMCIMA, self.EMBAIXO,])

                old_x = self.roboR2.x
                old_y = self.roboR2.y

                y, x, lixeira = self.busca_lixeira_r2(direcao)

                if lixeira:
                    if lixeira.conteudo:
                        lixo = lixeira.conteudo[0]
                        self.roboR2.conteudo.append(lixo)
                        lixeira.conteudo.remove(lixo)
                self.ambiente[y][x] = self.roboR2
                self.ambiente[old_y][old_x] = " "
                self.mostrarAmbiente()            

            if self.roboR2.conteudo and self.roboR2.conteudo[0].tipo == "i":
                direcao = choice([self.ESQUERDA, self.DIREITA, self.EMCIMA, self.EMBAIXO,])

                old_x = self.roboR2.x
                old_y = self.roboR2.y

                y, x, incinerador = self.busca_incinerador(direcao)

                if incinerador:
                    lixo = self.roboR2.conteudo[0]
                    self.incinerador.conteudo.append(lixo)
                    self.roboR2.conteudo.remove(lixo)
                    self.qtd_i -=1
                self.ambiente[y][x] = self.roboR2
                self.ambiente[old_y][old_x] = " "          
                self.mostrarAmbiente()

            if self.roboR2.conteudo and self.roboR2.conteudo[0].tipo == "r":
    
                direcao = choice([self.ESQUERDA, self.DIREITA, self.EMCIMA, self.EMBAIXO,])

                old_x = self.roboR2.x
                old_y = self.roboR2.y
                
                y, x, reciclador = self.busca_reciclador(direcao)

                if reciclador:
                    lixo = self.roboR2.conteudo[0]
                    self.recicladora.conteudo.append(lixo)
                    self.roboR2.conteudo.remove(lixo)
                    self.qtd_r -=1
                self.ambiente[y][x] = self.roboR2
                self.ambiente[old_y][old_x] = " "            
                self.mostrarAmbiente()
                
            self.mostrarAmbiente()

#* AGENTE REATIVO SIMPLES LIXEIRA
    def reativo_simples_lixeira(self):
        self.roboR1.conteudo = self.lixo_list
        while self.roboR1.conteudo:
            direcao = choice([self.ESQUERDA, self.DIREITA, self.EMCIMA, self.EMBAIXO,])

            old_x = self.roboR1.x
            old_y = self.roboR1.y

            y, x, lixeira = self.busca_lixeira(direcao)

            if lixeira:
                lixo = self.roboR1.conteudo[-1]
                lixeira.conteudo.append(lixo)
                self.roboR1.conteudo.remove(lixo)
                return " "
            self.ambiente[y][x] = self.roboR1
            self.ambiente[old_y][old_x] = " "

            self.mostrarAmbiente()

#* AGENTE REATIVO SIMPLES INCINERADOR
    def reativo_simples_incinerador_r2(self):
        while self.roboR2.conteudo and self.roboR2.conteudo[0].tipo == "i":
            direcao = choice([self.ESQUERDA, self.DIREITA, self.EMCIMA, self.EMBAIXO,])

            old_x = self.roboR2.x
            old_y = self.roboR2.y

            y, x, incinerador = self.busca_incinerador(direcao)

            if incinerador:
                lixo = self.roboR2.conteudo[0]
                self.incinerador.conteudo.append(lixo)
                self.roboR2.conteudo.remove(lixo)
                self.qtd_i -=1
            self.ambiente[y][x] = self.roboR2
            self.ambiente[old_y][old_x] = " "            

            self.mostrarAmbiente()

#* AGENTE REATIVO SIMPLES RECICLADOR
    def reativo_simples_reciclador_r2(self):
            
        while self.roboR2.conteudo and self.roboR2.conteudo[0].tipo == "r":
                
            direcao = choice([self.ESQUERDA, self.DIREITA, self.EMCIMA, self.EMBAIXO,])
            old_x = self.roboR2.x
            old_y = self.roboR2.y
            y, x, reciclador = self.busca_reciclador(direcao)

            if reciclador:
                lixo = self.roboR2.conteudo[0]
                self.recicladora.conteudo.append(lixo)
                self.roboR2.conteudo.remove(lixo)
                self.qtd_r -=1
            self.ambiente[y][x] = self.roboR2
            self.ambiente[old_y][old_x] = " "            

            self.mostrarAmbiente()
