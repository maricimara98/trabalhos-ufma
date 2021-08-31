# -*- coding: utf-8 -*-
import sys
import csv
from time import time
import tweepy #https://github.com/tweepy/tweepy

# Autenticações
consumer_key = '3EnfmJo5rjIWQUycXHiKKX19s'
consumer_secret = 'FN1X0tNMXigHptrOr84WaAJXrTRk0dwIdwbb3heiym4HeaDwM4'
access_token = '1088915040483319808-1TcKZ9uPSAioC1cJqD2Rw9Bq2OKcKP'
access_token_secret = 'Yvhao8hwjuCJBqxBfMQPXY9ho2LQpDHsmOlyBA4iUbpwG'

# Autorização e inicialização da tweepy
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)
api = tweepy.API(auth, wait_on_rate_limit_notify=True, wait_on_rate_limit=True) # Twitter's em tempo real

busca_palavra = "Futebol, Flamengo, Mengão, Campeonato Brasileiro "      # Palavra para procurar
nova_busca = busca_palavra + " -filter:retweets"
resultados = tweepy.Cursor(api.search, q=nova_busca, lang="pt", tweet_mode='extended').items(1000)

# Transforma os tweets em uma lista que vai preencher o CSV    
saida_tweets = [['@'+tweet.user.screen_name, tweet.full_text]
                for tweet in resultados]

# Escreve o CSV       
with open('edii_tweets.csv', 'w', encoding='utf-8') as arquivo_saida:
    escritor = csv.writer(arquivo_saida, delimiter=":", lineterminator=':::\n') #o tweet termina com espacamento
    escritor.writerow(["autor","texto"])
    escritor.writerows(saida_tweets)