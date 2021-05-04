# -*- coding: utf-8 -*-
import re
import csv
import random
import pymysql

conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                       charset='utf8mb4')
with open('手机_nagetive.csv', 'r', encoding='UTF-8') as f:
    reader = csv.reader(f)
    for row in reader:
        r = ''.join(row)
        cursor = conn.cursor()
        cursor.execute("insert into `comment` values(null, %s, '差评')",
                       (r.split()[2]));
        conn.commit()
        cursor.close()
with open('手机_positive.csv', 'r', encoding='UTF-8') as f:
    reader = csv.reader(f)
    for row in reader:
        r = ''.join(row)
        cursor = conn.cursor()
        cursor.execute("insert into `comment` values(null, %s, '好评')",
                       (r.split()[2]));
        conn.commit()
        cursor.close()
conn.close()