"""Aquisição - Seleção dos docs."""
import csv
import json

import tweepy


class Tweeter:
    """API do Tweeter.

    https://github.com/tweepy/tweepy

    Autenticação, Autorização e Inicialização da tweepy
    para extração de tweets
    """

    def __init__(self, palavra_chave, n_documentos):
        """
        Construtor para a class Tweeter.

        Parameters
        ----------
        palavra_chave : String

        n_documentos : int

        Returns
        -------
        lista de tweets

        """
        with open("docs/config.json") as json_file:
            self.__config = json.load(json_file)

        # Autenticação
        self.consumer_key = self.__config["CONSUMER_KEY"]
        self.consumer_secret = self.__config["CONSUMER_SECRET"]
        self.access_token = self.__config["ACCESS_TOKEN"]
        self.access_token_secret = self.__config["ACCESS_TOKEN_SECRET"]

        # Autorização e inicialização da tweepy
        usuario = tweepy.OAuthHandler(self.consumer_key, self.consumer_secret)
        usuario.set_access_token(self.access_token, self.access_token_secret)
        api = tweepy.API(
            usuario, wait_on_rate_limit_notify=True, wait_on_rate_limit=True
        )  # Twitter's em tempo real

        busca_palavra_chave = (
            palavra_chave + " -filter:retweets"
        )  # Não conta os compartilstopwords=hamentos
        search = tweepy.Cursor(
            api.search, q=busca_palavra_chave, lang="pt", tweet_mode="extended"
        ).items(n_documentos)

        # Transforma os tweets em uma lista que vai preencher o CSV
        saida_tweets = [
            [tweet.full_text] for tweet in search
        ]
        # Escreve o CSV
        with open("data/tweets.csv", "w", encoding="utf-8") as file:
            escritor = csv.writer(file, lineterminator=",\n")
            escritor.writerows(saida_tweets)
