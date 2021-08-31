"""Modelo vetorial ."""
import numpy as np


class ModeloVetorial:
    """
    Modelo Vetorial.
    
    Os pesos são utilizados para calcular o grau de similaridade
    """

    def __init__(self, preprocessor, docs):
        """
        Construtor para a class VetorModel.

        Parameters
        ----------
        preprocessor : preprocessamento.Preparacao_Indexacao

        docs : list

        """
        self.__preprocessor = preprocessor
        self.__documentos = docs
        self.__tokens = np.unique(
            np.hstack(
                [
                    preprocessor.__normalize__(preprocessor.__tokenize__(doc))
                    for doc in docs
                ]
            )
        )
        self.__arquivo_invertido = self.__criar_indice__()
        self.__tf_idf = self.__calcular_peso_documentos__(
            self.__tokens,
            lambda x: 0 if x == 0 else 1 + np.log2(x),
            lambda x: np.log2(x),
        )

    def __criar_indice__(self):
        """Enumera todos os documento"""

        return {
            token: np.char.count([doc.lower() for doc in self.__documentos], token)
            for token in self.__tokens
        }

    def __calcular_peso_documentos__(self, tokens, calc_tf, calc_idf):
        """Calculo do TF e do IDF."""

        def arquivo_invertido_iteravel():
            for token in self.__tokens:
                yield self.__arquivo_invertido.get(token, [])

        return {
            id_doc: [
                calc_tf(token_freqs[id_doc])
                * calc_idf(len(self.__documentos) / len(token_freqs.nonzero()[0]))
                for token_freqs in arquivo_invertido_iteravel()
            ]
            for id_doc, doc in enumerate(self.__documentos)
        }

    def __pesos_consultas__(self, query, q_tokens, calc_tf, calc_idf):
        """Calculo dos pesos das consultas do TF e do IDF."""
        return {
            id_doc: [
                calc_tf(token, query)
                * calc_idf(
                    len(self.__documentos),
                    len(self.__arquivo_invertido.get(token, []).nonzero()[0]),
                )
                for token in self.__tokens
            ]
            for id_doc, doc in enumerate(self.__documentos)
        }

    def consulta(self, query):
        """Consulta para casamento."""
        q_tokens = self.__preprocessor.__normalize__(
            self.__preprocessor.__tokenize__(query)
        )
        
        tf_idf_q = self.__pesos_consultas__(
            query,
            q_tokens,
            lambda x, y: 0 if y.count(x) == 0 else 1 + np.log2(y.count(x)),
            lambda x, y: 0 if y == 0 else np.log2(x / y),
        )

        def __pesos_iteraveis__(id_doc):
            """Pesos Iteraveis."""
            for id_token, i in enumerate(self.__tokens):
                yield (
                    self.__tf_idf[id_doc][id_token],
                    tf_idf_q[id_doc][id_token],
                )

        def __documentos_iteraveis__():
            """Documentos iteraveis."""
            for id_doc, i in enumerate(self.__documentos):
                produto = np.sum(
                    [
                        documento_peso * consulta_peso
                        for documento_peso, consulta_peso in __pesos_iteraveis__(id_doc)
                    ]
                )
                norma_documento = np.sqrt(
                    np.sum(
                        [
                            documento_peso ** 2
                            for documento_peso, i in __pesos_iteraveis__(id_doc)
                        ]
                    )
                )
                norma_consulta = np.sqrt(
                    np.sum(
                        [
                            consulta_peso ** 2
                            for _, consulta_peso in __pesos_iteraveis__(id_doc)
                        ]
                    )
                )

                yield (id_doc, produto, norma_documento, norma_consulta)

        rank = [
            (
                id_doc,
                produto / (norma_documento * norma_consulta)
                if norma_documento * norma_consulta != 0
                else 0,
            )
            for id_doc, produto, norma_documento, norma_consulta in __documentos_iteraveis__()
        ]

        rank.sort(reverse=True, key=lambda x: x[1])
        return rank
