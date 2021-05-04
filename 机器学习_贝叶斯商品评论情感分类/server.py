import base64
import os
import time

from flask import Flask,request,url_for, jsonify
import pymysql
import jieba
from flask_cors import *
import jieba.analyse
from werkzeug.utils import secure_filename

app = Flask(__name__)
app.config['JSON_AS_ASCII'] = False
CORS(app, supports_credentials=True)
from native_bayes_sentiment_analyzer import SentimentAnalyzer

from flask.json import JSONEncoder as _JSONEncoder

class JSONEncoder(_JSONEncoder):
    def default(self, o):
        import decimal
        if isinstance(o, decimal.Decimal):

            return float(o)

        super(JSONEncoder, self).default(o)
app.json_encoder = JSONEncoder

@app.route('/addUser',methods=['POST'])
def addUser():
    get_json = request.get_json()
    name = get_json['name']
    password = get_json['password']
    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                           charset='utf8mb4')
    cursor = conn.cursor()
    sql = "insert into `user` values(null,'"+name+"','"+password+"','user')"
    cursor.execute(sql);
    conn.commit()
    table_result = {"code": 200, "msg": "成功"}
    cursor.close()
    conn.close()
    return jsonify(table_result)

@app.route('/top',methods=['GET'])
def top():
    jsondata = {}
    if(len(request.args)!=0):
        if(request.args['category']=='good'):
            jsondata['data'] = good_datas
        else:
            jsondata['data'] = bad_datas
    else:
        jsondata['data'] = all_datas
    j = jsonify(jsondata)
    return j

@app.route('/loginByPassword',methods=['POST'])
def loginByPassword():
    get_json = request.get_json()
    name = get_json['name']
    password = get_json['password']
    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
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

@app.route('/query',methods=['GET'])
def query():
    text = request.args['text']
    jsondata = {}
    jsondata['data'] = analyzer.analyze(text)
    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                           charset='utf8mb4')
    cursor = conn.cursor()
    sql = "insert into `result` values(null,'" + text + "','"+jsondata['data']+"')"
    cursor.execute(sql);
    conn.commit()
    cursor.close()
    conn.close()
    j = jsonify(jsondata)
    return j

@app.route('/addComment',methods=['GET'])
def addComment():
    comment = request.args['comment']
    type = request.args['type']
    jsondata = {'code':200}
    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                           charset='utf8mb4')
    cursor = conn.cursor()
    sql = "insert into `comment` values(null,'" + comment + "','"+type+"')"
    cursor.execute(sql);
    conn.commit()
    cursor.close()
    conn.close()
    j = jsonify(jsondata)
    return j

@app.route('/updateComment',methods=['GET'])
def updateComment():
    id = int(request.args['id'])
    comment = request.args['comment']
    type = request.args['type']
    jsondata = {'code':200}
    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                           charset='utf8mb4')
    cursor = conn.cursor()
    sql = "update `comment` set `comment` = '"+comment+"' ,`type` = '"+type+"'  where id =" + str(id)
    cursor.execute(sql);
    conn.commit()
    cursor.close()
    conn.close()
    j = jsonify(jsondata)
    return j

@app.route('/delComment',methods=['GET'])
def delComment():
    id = int(request.args['id'])
    jsondata = {'code':200}
    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                           charset='utf8mb4')
    cursor = conn.cursor()
    sql = "DELETE from `comment` where id= " + str(id)
    cursor.execute(sql);
    conn.commit()
    cursor.close()
    conn.close()
    j = jsonify(jsondata)
    return j


@app.route('/comment',methods=['GET'])
def comment():
    limit = int(request.args['limit'])
    page = int(request.args['page'])
    page = (page-1)*limit
    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                           charset='utf8mb4')
    cursor = conn.cursor()
    if(len(request.args)==2):
        cursor.execute("select count(*) from `comment`");
        count = cursor.fetchall()
        cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
        cursor.execute("select * from `comment` limit " + str(page) + "," + str(limit));
        data_dict = []
        result = cursor.fetchall()
        for field in result:
            data_dict.append(field)
    else:
        type = str(request.args['type'])
        cursor.execute("select count(*) from `comment` where `type`= '"+type+"'");
        count = cursor.fetchall()
        cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
        cursor.execute("select * from `comment` where `type`= '"+type+"' limit " + str(page) + "," + str(limit));
        data_dict = []
        result = cursor.fetchall()
        for field in result:
            data_dict.append(field)
    table_result = {"code": 0, "msg": None, "count": count[0], "data": data_dict}
    cursor.close()
    conn.close()
    return jsonify(table_result)

@app.route('/data',methods=['GET'])
def data():
    limit = int(request.args['limit'])
    page = int(request.args['page'])
    page = (page-1)*limit
    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                           charset='utf8mb4')
    cursor = conn.cursor()
    cursor.execute("select count(*) from `result`");
    count = cursor.fetchall()
    cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
    cursor.execute("select * from `result` limit "+str(page)+","+str(limit));
    data_dict = []
    result = cursor.fetchall()
    for field in result:
        data_dict.append(field)
    table_result = {"code": 0, "msg": None, "count": count[0], "data": data_dict}
    cursor.close()
    conn.close()
    return jsonify(table_result)

@app.route('/ksh',methods=['GET'])
def ksh():
    path = str(request.args['path'])
    good = 0
    bad = 0
    if(len(path)==0):
        table_result = {"code": 0, "msg": None, "count": 0, "data": data}
    else:
        with open(path, 'r', encoding='UTF-8') as f:
            reader = f.readlines()
            rows = [row for row in reader]
        for i in rows:
            result = analyzer.analyze(i)
            if(result == "好评"):
                good+=1
            else:
                bad+=1
        table_result = {"good": good, "bad": bad}
    return jsonify(table_result)

@app.route('/data1',methods=['GET'])
def data1():
    path = str(request.args['path'])
    data = []
    if(len(path)==0):
        table_result = {"code": 0, "msg": None, "count": 0, "data": data}
    else:
        with open(path, 'r', encoding='UTF-8') as f:
            reader = f.readlines()
            rows = [row for row in reader]
        for i in rows:
            result = analyzer.analyze(i)
            d = {"content":i,"result":result}
            data.append(d)
        table_result = {"code": 0, "msg": None, "count": 10, "data": data}
    return jsonify(table_result)

# 用于判断文件后缀
def allowed_file(filename):
    return '.' in filename and filename.rsplit('.',1)[1] in ALLOWED_EXTENSIONS


# 上传文件
@app.route('/upload', methods=['POST'], strict_slashes=False)
def api_upload():
    file_dir = os.path.join(basedir, app.config['UPLOAD_FOLDER'])
    if not os.path.exists(file_dir):
        os.makedirs(file_dir)
    f = request.files['myfile']  # 从表单的file字段获取文件，myfile为该表单的name值
    if f and allowed_file(f.filename):  # 判断是否是允许上传的文件类型
        fname = secure_filename(f.filename)
        ext = fname.rsplit('.', 1)[1]  # 获取文件后缀
        unix_time = int(time.time())
        new_filename = str(unix_time) + '.' + ext  # 修改了上传的文件名
        path = os.path.join(file_dir, new_filename)
        f.save(path)  # 保存文件到upload目录
        return jsonify({"errno": 0, "errmsg": "上传成功", "path":path})
    else:
        return jsonify({"errno": 1001, "errmsg": "上传失败"})

if __name__ == "__main__":
    model_path = './bayes.pkl'
    userdict_path = './userdict.txt'
    stopword_path = './stopwords.txt'
    analyzer = SentimentAnalyzer(model_path=model_path, stopword_path=stopword_path, userdict_path=userdict_path)
    UPLOAD_FOLDER = 'upload'
    app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER
    basedir = os.path.abspath(os.path.dirname(__file__))
    ALLOWED_EXTENSIONS = set(['txt'])

    conn = pymysql.connect(host='192.168.193.128', user='root', password='123456', port=3306, db='goods',
                           charset='utf8mb4')
    cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
    cursor.execute("select comment from `comment`");
    data_dict = []
    result = cursor.fetchall()
    for field in result:
        data_dict.append(field['comment'])
    content = ''.join(data_dict)
    all_datas = []
    jieba.analyse.set_stop_words('./stopwords.txt')
    tags = jieba.analyse.extract_tags(content, topK=100, withWeight=True)
    for v, n in tags:
        mydict = {}
        mydict["name"] = v
        mydict["value"] = str(int(n * 10000))
        all_datas.append(mydict)
    cursor.close()


    cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
    cursor.execute("select comment from `comment` where `type`='好评'");
    data_dict = []
    result = cursor.fetchall()
    for field in result:
        data_dict.append(field['comment'])
    content = ''.join(data_dict)
    good_datas = []
    jieba.analyse.set_stop_words('./stopwords.txt')
    tags = jieba.analyse.extract_tags(content, topK=100, withWeight=True)
    for v, n in tags:
        mydict = {}
        mydict["name"] = v
        mydict["value"] = str(int(n * 10000))
        good_datas.append(mydict)
    cursor.close()


    cursor = conn.cursor(cursor=pymysql.cursors.DictCursor)
    cursor.execute("select comment from `comment` where `type`='差评'");
    data_dict = []
    result = cursor.fetchall()
    for field in result:
        data_dict.append(field['comment'])
    content = ''.join(data_dict)
    bad_datas = []
    jieba.analyse.set_stop_words('./stopwords.txt')
    tags = jieba.analyse.extract_tags(content, topK=100, withWeight=True)
    for v, n in tags:
        mydict = {}
        mydict["name"] = v
        mydict["value"] = str(int(n * 10000))
        bad_datas.append(mydict)
    cursor.close()

    app.run(port=5000)