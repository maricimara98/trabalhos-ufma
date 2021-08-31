import json
import time
from random import *

from src.ambiente import Ambiente
from src.robo import Robo
from source.simples import Agente


def main():

    with open("configs/configs.json") as i:
        __config = json.load(i)

    robo1 = Robo(__config["robo_1_y"], __config["robo_1_x"], "1")
    robo2 = Robo(__config["robo_2_y"], __config["robo_2_x"], "2")

    ambiente = Ambiente(robo1, robo2)
    ambiente.construir()
    ambiente.mostrarAmbiente()

    inicio = time.time()
    while(ambiente.verificaSucesso()):
        acao = Agente.reativo_simples(ambiente)
        acao.mostrarAmbiente()
    fim = time.time()
    print(f"Tempo de execução: {fim - inicio}")

if __name__ == '__main__':
    main()
