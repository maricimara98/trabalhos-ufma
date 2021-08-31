class Lixo:
    def __init__(self, id_quantidade, y, x, tipo):
        self.id_quantidade = id_quantidade
        self.x = x
        self.y = y
        self.tipo = tipo

    def __str__(self):
        return f"{self.tipo}"