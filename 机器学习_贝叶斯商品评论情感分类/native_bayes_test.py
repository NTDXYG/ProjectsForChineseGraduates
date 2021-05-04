# -*- coding: utf-8 -*-
import re
import csv
import random


import numpy as np
import jieba
from sklearn.base import TransformerMixin

from sklearn.feature_extraction.text import CountVectorizer, TfidfTransformer
from sklearn.pipeline import Pipeline
from sklearn.naive_bayes import MultinomialNB,GaussianNB
from sklearn.svm import SVC
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier

jieba.load_userdict('./userdict.txt')
file_path_pos = './手机_positive.csv'
file_path_nag = './手机_nagetive.csv'
stopword_path = './stopwords.txt'


def load_corpus(file_path_pos,file_path_nag):
    with open(file_path_pos, 'r',encoding='UTF-8') as f:
        reader = csv.reader(f)
        rows = [row for row in reader]
    with open(file_path_nag, 'r',encoding='UTF-8') as f:
        reader = csv.reader(f)
        rows_2 = [row for row in reader]
    rows.extend(rows_2)
    # 将读取出来的语料转为list
    review_data = np.array(rows).tolist()
    # 打乱语料的顺序
    random.shuffle(review_data)
    review_list = []
    sentiment_list = []
    # 第一列为差评/好评， 第二列为评论
    for words in review_data:
        review_list.append(words[0].split("\t")[2])
        if(words[0].split("\t")[1][0] == '5'):
            sentiment_list.append(1)
        else:
            sentiment_list.append(0)
    return review_list, sentiment_list


def load_stopwords(file_path):
    stop_words = []
    with open(file_path, encoding='UTF-8') as words:
       stop_words.extend([i.strip() for i in words.readlines()])
    return stop_words


def review_to_text(review):
    stop_words = load_stopwords(stopword_path)
    # 去除英文
    review = re.sub("[^\u4e00-\u9fa5^a-z^A-Z]", '', review)
    review = jieba.cut(review)
    # 去掉停用词
    if stop_words:
        all_stop_words = set(stop_words)
        words = [w for w in review if w not in all_stop_words]
    #print(words)
    return words


# 加载语料
review_list, sentiment_list = load_corpus(file_path_pos,file_path_nag)

# 将全部语料按1:4分为测试集与训练集
n = len(review_list) // 5
train_review_list, train_sentiment_list = review_list[n:], sentiment_list[n:]
test_review_list, test_sentiment_list = review_list[:n], sentiment_list[:n]

print('训练集数量： {}'.format(str(len(train_review_list))))
print('测试集数量： {}'.format(str(len(test_review_list))))

# 用于训练的评论
review_train = [' '.join(review_to_text(review)) for review in train_review_list]
# 对于训练评论对应的好评/差评
sentiment_train = train_sentiment_list

# 用于测试的评论
review_test = [' '.join(review_to_text(review)) for review in test_review_list]
# 对于测试评论对应的好评/差评
sentiment_test = test_sentiment_list


count_vec = CountVectorizer(max_df=0.8, min_df=3)

tfidf_vec = TfidfTransformer()


# 定义Pipeline对全部步骤的流式化封装和管理，可以很方便地使参数集在新数据集（比如测试集）上被重复使用。
def MNB_Classifier():
    return Pipeline([
        ('count_vec', count_vec),
        ('tfidf_vec', tfidf_vec),
        ('mnb', MultinomialNB())
    ])


mnbc_clf = MNB_Classifier()

# 进行训练
mnbc_clf.fit(review_train, sentiment_train)
# 测试集准确率
print('测试集准确率： {}'.format(mnbc_clf.score(review_test, sentiment_test)))
