# -*- coding: utf-8 -*-
from sentiment_analysis.native_bayes_sentiment_analyzer import SentimentAnalyzer


model_path = './data/goods_bayes.pkl'
userdict_path = './data/userdict.txt'
stopword_path = './data/stopwords.txt'

analyzer = SentimentAnalyzer(model_path=model_path, stopword_path=stopword_path, userdict_path=userdict_path)
text = '整体的感觉还是不错的'
result = analyzer.analyze(text=text)
print(result)
