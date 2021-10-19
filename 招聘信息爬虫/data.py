import requests
import math
import time
import pandas as pd
import pymysql
from sqlalchemy import create_engine

def get_json(url, num):
    """
    从指定的url中通过requests请求携带请求头和请求体获取网页中的信息,
    :return:
    """
    url1 = 'https://www.lagou.com/jobs/list_python/p-city_0?&cl=false&fromSearch=true&labelWords=&suginput='
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36',
        'Host': 'www.lagou.com',
        'Referer': 'https://www.lagou.com/jobs/list_%E6%95%B0%E6%8D%AE%E5%88%86%E6%9E%90?labelWords=&fromSearch=true&suginput=',
        'X-Anit-Forge-Code': '0',
        'X-Anit-Forge-Token': 'None',
        'X-Requested-With': 'XMLHttpRequest'
    }
    data = {
        'first': 'true',
        'pn': num,
        'kd': 'java'}
    s = requests.Session()
    print('建立session：', s, '\n\n')
    s.get(url=url1, headers=headers, timeout=3)
    cookie = s.cookies
    print('获取cookie：', cookie, '\n\n')
    res = requests.post(url, headers=headers, data=data, cookies=cookie, timeout=3)
    res.raise_for_status()
    res.encoding = 'utf-8'
    page_data = res.json()
    print('请求响应结果：', page_data, '\n\n')
    return page_data


def get_page_num(count):
    """
    计算要抓取的页数，通过在拉勾网输入关键字信息，可以发现最多显示30页信息,每页最多显示15个职位信息
    :return:
    """
    page_num = math.ceil(count / 15)
    if page_num > 30:
        return 30
    else:
        return page_num


def get_page_info(jobs_list):
    """
    获取职位
    :param jobs_list:
    :return:
    """
    page_info_list = []
    for i in jobs_list:  # 循环每一页所有职位信息
        job_info = []
        job_info.append(i['companyFullName'])
        job_info.append(i['companyShortName'])
        job_info.append(i['companySize'])
        job_info.append(i['financeStage'])
        job_info.append(i['district'])
        job_info.append(i['positionName'])
        job_info.append(i['workYear'])
        job_info.append(i['education'])
        job_info.append(i['salary'])
        job_info.append(i['positionAdvantage'])
        job_info.append(i['industryField'])
        job_info.append(i['firstType'])
        job_info.append(",".join(i['companyLabelList']))
        job_info.append(i['secondType'])
        job_info.append(i['city'])
        page_info_list.append(job_info)
    return page_info_list

def unique(old_list):
    newList = []
    for x in old_list:
        if x not in newList :
            newList.append(x)
    return newList

def main():
    connect_info = 'mysql+pymysql://{}:{}@{}:{}/{}?charset=utf8'.format("root", "123456", "192.168.85.134", "3306",
                                                                        "lagou")
    engine = create_engine(connect_info)
    url = ' https://www.lagou.com/jobs/positionAjax.json?needAddtionalResult=false'
    first_page = get_json(url, 1)
    total_page_count = first_page['content']['positionResult']['totalCount']
    num = get_page_num(total_page_count)
    total_info = []
    time.sleep(10)
    for num in range(1, num + 1):
        # 获取每一页的职位相关的信息
        page_data = get_json(url, num)  # 获取响应json
        jobs_list = page_data['content']['positionResult']['result']  # 获取每页的所有python相关的职位信息
        page_info = get_page_info(jobs_list)
        total_info += page_info
        print('已经爬取到第{}页，职位总数为{}'.format(num, len(total_info)))
        time.sleep(20)
    #将总数据转化为data frame再输出,然后在写入到csv各式的文件中
    df = pd.DataFrame(data=unique(total_info),
                      columns=['companyFullName', 'companyShortName', 'companySize', 'financeStage',
                               'district', 'positionName', 'workYear', 'education',
                               'salary', 'positionAdvantage', 'industryField',
                               'firstType', 'companyLabelList', 'secondType', 'city'])
    df.to_csv('java.csv', index=True)
    print('职位信息已保存本地')
    df.to_sql(name='demo',
              con=engine,
              if_exists='append',
              index=False
              )
    print('职位信息已保存数据库')

if __name__ == '__main__':
    main()