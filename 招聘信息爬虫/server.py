import jieba
import jieba.analyse
import numpy as np
from flask import Flask,request,url_for, jsonify
import pymysql
from flask_cors import *
import pandas as pd
from collections import Counter
import pickle

app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False
CORS(app, supports_credentials=True)

from flask.json import JSONEncoder as _JSONEncoder

class JSONEncoder(_JSONEncoder):
    def default(self, o):
        import decimal
        if isinstance(o, decimal.Decimal):

            return float(o)

        super(JSONEncoder, self).default(o)
app.json_encoder = JSONEncoder

@app.route('/xueli',methods=['GET'])
def xueli():
    conn = pymysql.connect(host='192.168.85.134', user='root', password='123456', port=3306, db='lagou',
                           charset='utf8mb4')
    cursor = conn.cursor()
    cursor.execute("SELECT DISTINCT(education) from demo");
    result = cursor.fetchall()
    education = []
    education_data = []
    color_list = ['#459AF0','#38C3B0','#86CA5A','#BFD44F']
    for field in result:
        education.append(field[0])
    for i in range(len(education)):
        cursor.execute("SELECT count(*) from demo where education = '" + education[i] + "'");
        count = cursor.fetchall()
        education_data.append({'value': count[0][0],'itemStyle': {'color': color_list[i]}})
    cursor.execute("SELECT DISTINCT(workYear) from demo");
    result = cursor.fetchall()
    workYear = []
    workYear_data = []
    for field in result:
        workYear.append(field[0])
    for i in workYear:
        cursor.execute("SELECT count(*) from demo where workYear = '" + i + "'");
        count = cursor.fetchall()
        workYear_data.append({'value': count[0][0], 'name': i})
    return jsonify({"education":education,"education_data":education_data,"workYear_data":workYear_data})

@app.route('/fuli',methods=['GET'])
def fuli():
    conn = pymysql.connect(host='192.168.85.134', user='root', password='123456', port=3306, db='lagou',
                           charset='utf8mb4')
    cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
    cursor.execute("select positionAdvantage from `demo`");
    data_dict = []
    result = cursor.fetchall()
    for field in result:
        data_dict.append(field['positionAdvantage'])
    content = ''.join(data_dict)
    positionAdvantage = []
    jieba.analyse.set_stop_words('./stopwords.txt')
    tags = jieba.analyse.extract_tags(content, topK=100, withWeight=True)
    for v, n in tags:
        mydict = {}
        mydict["name"] = v
        mydict["value"] = str(int(n * 10000))
        positionAdvantage.append(mydict)
    cursor.execute("select companyLabelList from `demo`");
    data_dict = []
    result = cursor.fetchall()
    for field in result:
        data_dict.append(field['companyLabelList'])
    content = ''.join(data_dict)
    companyLabelList = []
    jieba.analyse.set_stop_words('./stopwords.txt')
    tags = jieba.analyse.extract_tags(content, topK=100, withWeight=True)
    for v, n in tags:
        mydict = {}
        mydict["name"] = v
        mydict["value"] = str(int(n * 10000))
        companyLabelList.append(mydict)
    cursor.close()
    return jsonify({"zwfl":positionAdvantage,"gsfl":companyLabelList})

@app.route('/qiye',methods=['GET'])
def qiye():
    conn = pymysql.connect(host='192.168.85.134', user='root', password='123456', port=3306, db='lagou',
                           charset='utf8mb4')
    cursor = conn.cursor()
    cursor.execute("SELECT DISTINCT(city) from demo");
    result = cursor.fetchall()
    city = []
    companySize = []
    companySizeResult = []
    city_result = []
    selected = {}
    for field in result:
        city.append(field[0])
    if (len(request.args) == 0):
        for i in city:
            cursor.execute("SELECT count(*) from demo where city = '" + i + "'");
            count = cursor.fetchall()
            dict = {'value': count[0][0], 'name': i}
            city_result.append(dict)
        for i in city[7:]:
            selected[i] = False
        cursor.execute("SELECT DISTINCT(companySize) from demo");
        company = cursor.fetchall()
        for field in company:
            companySize.append(field[0])
            cursor.execute("SELECT count(*) from demo where companySize = '" + field[0] + "'");
            count = cursor.fetchall()
            companySizeResult.append(count[0][0])
    else:
        positionName = str(request.args['positionName'])
        for i in city:
            cursor.execute("SELECT count(*) from demo where city = '" + i + "' and positionName like '%"+positionName+"%'");
            count = cursor.fetchall()
            dict = {'value': count[0][0], 'name': i}
            city_result.append(dict)
        for i in city[7:]:
            selected[i] = False
        cursor.execute("SELECT DISTINCT(companySize) from demo");
        company = cursor.fetchall()
        for field in company:
            companySize.append(field[0])
            cursor.execute("SELECT count(*) from demo where companySize = '" + field[0] + "' and positionName like '%"+positionName+"%'");
            count = cursor.fetchall()
            companySizeResult.append(count[0][0])
    result = {"city": city,"city_result":city_result,"selected":selected,"companySize":companySize,"companySizeResult":companySizeResult}
    return jsonify(result)

@app.route('/xinzi',methods=['GET'])
def xinzi():
    conn = pymysql.connect(host='192.168.85.134', user='root', password='123456', port=3306, db='lagou',
                           charset='utf8mb4')
    cursor = conn.cursor()
    positionName = ['java','python','php']
    #柱状图返回列表
    zzt_list = []
    zzt_list.append(['product', 'Java', 'Python', 'PHP'])
    if (len(request.args) == 0 or str(request.args['education'])=='不限'):
        temp_list = []
        for i in positionName:
            cursor.execute("SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) like '%K%' and positionName like '%"+i+"%';");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['0—10K', temp_list[0], temp_list[1], temp_list[2]])
        temp_list = []
        for i in positionName:
            cursor.execute("SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) BETWEEN 10 AND 20 and positionName like '%"+i+"%';");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['10—20K', temp_list[0], temp_list[1], temp_list[2]])
        temp_list = []
        for i in positionName:
            cursor.execute("SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) BETWEEN 20 AND 30 and positionName like '%"+i+"%';");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['20—30K', temp_list[0], temp_list[1], temp_list[2]])
        temp_list = []
        for i in positionName:
            cursor.execute(
                "SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) BETWEEN 30 AND 40 and positionName like '%" + i + "%';");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['30—40K', temp_list[0], temp_list[1], temp_list[2]])
        temp_list = []
        for i in positionName:
            cursor.execute(
                "SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) > 40 and positionName like '%" + i + "%';");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['40以上', temp_list[0], temp_list[1], temp_list[2]])
    else:
        education = str(request.args['education'])
        temp_list = []
        for i in positionName:
            cursor.execute(
                "SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) like '%K%' and positionName like '%" + i + "%' and education = '"+education+"'");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['0—10K', temp_list[0], temp_list[1], temp_list[2]])
        temp_list = []
        for i in positionName:
            cursor.execute(
                "SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) BETWEEN 10 AND 20 and positionName like '%" + i + "%' and education = '"+education+"'");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['10—20K', temp_list[0], temp_list[1], temp_list[2]])
        temp_list = []
        for i in positionName:
            cursor.execute(
                "SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) BETWEEN 20 AND 30 and positionName like '%" + i + "%' and education = '"+education+"'");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['20—30K', temp_list[0], temp_list[1], temp_list[2]])
        temp_list = []
        for i in positionName:
            cursor.execute(
                "SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) BETWEEN 30 AND 40 and positionName like '%" + i + "%' and education = '"+education+"'");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['30—40K', temp_list[0], temp_list[1], temp_list[2]])
        temp_list = []
        for i in positionName:
            cursor.execute(
                "SELECT COUNT(*) FROM demo WHERE SUBSTR(salary,1,2) > 40 and positionName like '%" + i + "%' and education = '"+education+"'");
            count = cursor.fetchall()
            temp_list += count[0]
        zzt_list.append(['40以上', temp_list[0], temp_list[1], temp_list[2]])
    result = {"zzt": zzt_list}
    return jsonify(result)

@app.route('/data',methods=['GET'])
def data():
    limit = int(request.args['limit'])
    page = int(request.args['page'])
    page = (page-1)*limit
    conn = pymysql.connect(host='192.168.85.134', user='root', password='123456', port=3306, db='lagou',
                           charset='utf8mb4')

    cursor = conn.cursor()
    if (len(request.args) == 2):
        cursor.execute("select count(*) from demo");
        count = cursor.fetchall()
        cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
        cursor.execute("select * from demo limit "+str(page)+","+str(limit));
        data_dict = []
        result = cursor.fetchall()
        for field in result:
            data_dict.append(field)
    else:
        education = str(request.args['education'])
        positionName = str(request.args['positionName']).lower()
        if(education=='不限'):
            cursor.execute("select count(*) from demo where positionName like '%"+positionName+"%'");
            count = cursor.fetchall()
            cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
            cursor.execute("select * from demo where positionName like '%"+positionName+"%' limit " + str(page) + "," + str(limit));
            data_dict = []
            result = cursor.fetchall()
            for field in result:
                data_dict.append(field)
        else:
            cursor.execute("select count(*) from demo where positionName like '%"+positionName+"%' and education = '"+education+"'");
            count = cursor.fetchall()
            cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
            cursor.execute("select * from demo where positionName like '%"+positionName+"%' and education = '"+education+"' limit " + str(page) + "," + str(limit));
            data_dict = []
            result = cursor.fetchall()
            for field in result:
                data_dict.append(field)
    table_result = {"code": 0, "msg": None, "count": count[0], "data": data_dict}
    cursor.close()
    conn.close()
    return jsonify(table_result)

@app.route('/addUser',methods=['POST'])
def addUser():
    get_json = request.get_json()
    name = get_json['name']
    password = get_json['password']
    conn = pymysql.connect(host='192.168.85.134', user='root', password='123456', port=3306, db='lagou',
                           charset='utf8mb4')
    cursor = conn.cursor()
    sql = "insert into `user` values(null,'"+name+"','"+password+"')"
    cursor.execute(sql);
    conn.commit()
    table_result = {"code": 200, "msg": "成功"}
    cursor.close()
    conn.close()
    return jsonify(table_result)

@app.route('/updatePass',methods=['GET'])
def updatePass():
    name = str(request.args['name'])
    password = str(request.args['pass'])
    conn = pymysql.connect(host='192.168.85.134', user='root', password='123456', port=3306, db='lagou',
                           charset='utf8mb4')
    cursor = conn.cursor()
    sql = "update `user` set password = '"+password+"' where username = '"+ name +"'"
    cursor.execute(sql);
    conn.commit()
    table_result = {"code": 200, "password": password}
    cursor.close()
    conn.close()
    return jsonify(table_result)

@app.route('/loginByPassword',methods=['POST'])
def loginByPassword():
    get_json = request.get_json()
    name = get_json['name']
    password = get_json['password']
    conn = pymysql.connect(host='192.168.85.134', user='root', password='123456', port=3306, db='lagou',
                           charset='utf8mb4')
    cursor = conn.cursor()
    cursor.execute("select count(*) from `user` where `username` = '" + name +"' and password = '" + password+"'");
    count = cursor.fetchall()
    if(count[0][0] != 0):
        table_result = {"code": 200, "msg": name}
    else:
        table_result = {"code": 500, "msg": "失败"}
    cursor.close()
    conn.close()
    return jsonify(table_result)

@app.route('/predict',methods=['GET'])
def predict():
    y_data = ['0—10K', '10—20K', '20—30K', '30—40K', '40K以上']
    positionName = str(request.args['positionName'])
    model = str(request.args['model'])
    with open(positionName+'_'+model+'.model', 'rb') as fr:
        selected_model = pickle.load(fr)
    companySize = int(request.args['companySize'])
    workYear = int(request.args['workYear'])
    education = int(request.args['education'])
    city = int(request.args['city'])
    x = [companySize,workYear,education,city]
    x = np.array(x)
    y = selected_model.predict(x.reshape(1, -1))
    return jsonify(y_data[y[0]])

if __name__ == "__main__":
   app.run(port=5000)