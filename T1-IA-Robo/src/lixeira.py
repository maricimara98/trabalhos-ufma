class Lixeira:
    def __init__(self, y, x, name):
        self.name = name
        self.x = x
        self.y = y
        self.conteudo = []

    def __str__(self):
        return f"{self.name}"
