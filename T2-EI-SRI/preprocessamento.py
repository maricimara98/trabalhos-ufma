"""
Preparação e Indexação dos documentos
"""
#TODO: bibioteca nltk
class Preparacao_Indexacao:
    """Preperacao dos documentos"""

    def __init__(self, delims=" ", stopwords=[]):
        """Construtor para PreProcessamento.

        Parameters
        ----------
        delims : String, optional

        stopwords : list, optional
        """
        self.__delims = str.maketrans(delims, " " * len(delims))
        self.__stopwords = stopwords

    def __tokenize__(self, text):
        """Quebra a string inteira (texto de entrada) em palavras únicas."""
        return [
            string for string in text.translate(self.__delims).split() if string != " "
        ]

    def __normalize__(self, text):
        """Remoção das Stopwords."""
        return [
            string.lower() for string in text if string.lower() not in self.__stopwords
        ]
