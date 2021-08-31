# -*- coding: utf-8 -*-
import sys
import csv
import tweepy #https://github.com/tweepy/tweepy

# Autenticações
# coloque seus dados do app criado no twitter.developer
consumer_key = 'CONSUMER_KET'
consumer_secret = 'CONSUMER_SECRET'
access_token = 'ACCESS-TOKEN'
access_token_secret = 'ACESS_TOKEN_SECRET'

# Autorização e inicialização da tweepy
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)
api = tweepy.API(auth, wait_on_rate_limit_notify=True, wait_on_rate_limit=True) # Twitter's em tempo real

busca_palavra = "Futebol, Flamengo, Mengão, Campeonato Brasileiro "      # Palavra para procurar
nova_busca = busca_palavra + " -filter:retweets"
resultados = tweepy.Cursor(api.search, q=nova_busca, lang="pt",tweet_mode='extended', since_id=0).items(10000)

# Transforma os tweets em uma lista que vai preencher o CSV    
saida_tweets = [['@'+tweet.user.screen_name, tweet.full_text]
                for tweet in resultados]

# Escreve o CSV    
with open('10000_tweets.csv', 'w', encoding='utf-8') as arquivo_saida:
    escritor = csv.writer(arquivo_saida, delimiter=":", lineterminator='\n\n\n') #o tweet termina com espacamento de 3
    # escritor.writerow(["user","text"])
    escritor.writerows(saida_tweets)