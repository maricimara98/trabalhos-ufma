"""Modelagem do Produto."""

class Produto(): # pylint: disable=too-few-public-methods
    """Produtos possuem: nome, necessidade, preco."""
    def __init__(self, nome, peso, valor):
        self.nome = nome
        self.peso = peso
        self.valor = valor
        