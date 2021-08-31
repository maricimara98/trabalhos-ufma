import random


class Robo:
    def __init__(self, y, x, name):
        self.name = name
        self.x = x
        self.y = y
        self.conteudo = []

    def set(self, x, y, lixo):
        self.x = x
        self.y = y
        self.lixo = lixo
        return self

    
    def random_position(self):
        self.x = random.randint(1, 20)
        self.y = random.randint(1, 20)

    def __str__(self):
        return f"{self.name}"
