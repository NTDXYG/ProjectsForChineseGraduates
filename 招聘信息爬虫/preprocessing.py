import pandas as pd

def getXandY(filename):
    data = pd.read_csv(filename,encoding='gbk',usecols=['companySize', 'workYear', 'education',
                                   'city', 'salary'])
    list = data.values.tolist()
    print(list)
    X = []
    y = []
    for i in list:
        x = []
        #公司规模
        if(str(i[0]) == '少于15人'):
            x.append(0)
        if (str(i[0]) == '15-50人'):
            x.append(1)
        if (str(i[0]) == '50-150人'):
            x.append(2)
        if (str(i[0]) == '150-500人'):
            x.append(3)
        if (str(i[0]) == '500-2000人'):
            x.append(4)
        if (str(i[0]) == '2000人以上'):
            x.append(5)
        #工作经验
        if (str(i[1]) == '不限'):
            x.append(0)
        if (str(i[1]) == '在校/应届'):
            x.append(1)
        if (str(i[1]) == '1年以下'):
            x.append(2)
        if (str(i[1]) == '1-3年'):
            x.append(3)
        if (str(i[1]) == '3-5年'):
            x.append(4)
        if (str(i[1]) == '5-10年'):
            x.append(5)

        # 学历
        if (str(i[2]) == '不限'):
            x.append(0)
        if (str(i[2]) == '大专'):
            x.append(1)
        if (str(i[2]) == '本科'):
            x.append(2)
        if (str(i[2]) == '硕士'):
            x.append(3)
        # 位置
        if (str(i[4]) == '北京'):
            x.append(0)
        if (str(i[4]) == '广州'):
            x.append(1)
        if (str(i[4]) == '上海'):
            x.append(2)
        if (str(i[4]) == '深圳'):
            x.append(3)
        if (str(i[4]) != '深圳' and str(i[4]) != '上海' and str(i[4]) != '广州' and str(i[4]) != '北京'):
            x.append(4)
        X.append(x)
        # y_label
        if(str(i[3][:2]).find('k') != -1):
            y.append(0)
        else:
            if(20>int(i[3][:2])>=10):
                y.append(1)
            if (30 > int(i[3][:2]) >= 20):
                y.append(2)
            if (40 > int(i[3][:2]) >= 30):
                y.append(3)
            if (int(i[3][:2]) >= 40):
                y.append(4)
    return X,y