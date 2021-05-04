import requests
from lxml import etree
import logging
from urllib.parse import quote
import json
from urllib.parse import urlencode
import time


class JDSpider:
    # 爬虫实现类：传入商品类别（如手机、电脑），构造实例。然后调用getData爬取数据。
    def __init__(self, categlory):
        self.startUrl = "https://search.jd.com/Search?keyword=%s&enc=utf-8" % (quote(categlory))  # jD起始搜索页面
        self.commentBaseUrl = "https://sclub.jd.com/comment/productPageComments.action?"
        self.headers = {
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36"}
        self.productsId = self.getId()
        self.comtype = {1: "nagetive", 2: "medium", 3: "positive"}
        self.categlory = categlory
        self.iplist = {
            'http': [],
            'https': []
        }

    def getParamUrl(self, productid, page, score):
        params = {  # 用于控制页数，页面信息数的数据，非常重要，必不可少，要不然会被JD识别出来，爬不出相应的数据。
            "productId": "%s" % (productid),
            "score": "%s" % (score),  # 1表示差评，2表示中评，3表示好评
            "sortType": "5",
            "page": "%s" % (page),
            "pageSize": "10",
            "isShadowSku": "0",
            "rid": "0",
            "fold": "1"
        }
        url = self.commentBaseUrl + urlencode(params)
        return params, url

    def getHeaders(self, productid):  # 和初始的self.header不同，这是爬取某个商品的header，加入了商品id，我也不知道去掉了会怎样。
        header = {"Referer": "https://item.jd.com/%s.html" % (productid),
                  "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36"
                  }
        return header

    def getId(self):  # 获取商品id，为了得到具体商品页面的网址。结果保存在self.productId的数组里
        response = requests.get(self.startUrl, headers=self.headers)
        if response.status_code != 200:
            logging.warning("状态码错误，爬虫连接异常！")
        html = etree.HTML(response.text)
        return html.xpath('//li[@class="gl-item"]/@data-sku')

    def getData(self, maxPage, score, ):  # maxPage是爬取评论的最大页数，每页10条数据。差评和好评的最大一般页码不相同，一般情况下：好评>>差评>中评
        # maxPage遇到超出的页码会自动跳出，所以设大点也没有关系。
        # score是指那种评价类型，好评3、中评2、差评1。

        comments = []
        scores = []

        for j in range(len(self.productsId)):
            id = self.productsId[j]
            header = self.getHeaders(id)
            for i in range(1, maxPage):
                param, url = self.getParamUrl(id, i, score)
                print(">>>>>>>>>>>>>>>>第：%d 个，第 %d 页" % (j, i))
                try:
                    response = requests.get(url, headers=header, params=param)
                except Exception as e:
                    logging.warning(e)
                    break
                if response.status_code != 200:
                    logging.warning("状态码错误，爬虫连接异常")
                    continue
                time.sleep(2)  # 设置时延
                if response.text == '':
                    logging.warning("未爬取到信息")
                    continue
                try:
                    res_json = json.loads(response.text)
                except Exception as e:
                    logging.warning(e)
                    continue
                if len((res_json['comments'])) == 0:
                    logging.warning("页面次数已到：%d,超出范围" % (i))
                    break
                logging.info("正在爬取%s %s 第 %d" % (self.categlory, self.comtype[score], i))
                for cdit in res_json['comments']:
                    comment = cdit['content'].replace("\n", ' ').replace('\r', ' ')
                    comments.append(comment)
                    scores.append(cdit['score'])
                    print(comment)
        savepath = './' + self.categlory + '_' + self.comtype[score] + '.csv'
        logging.warning("已爬取%d 条 %s 评价信息" % (len(comments), self.comtype[score]))
        with open(savepath, 'a+', encoding='utf8') as f:
            for i in range(len(comments)):
                f.write("%d\t%s\t%s\n" % (i, scores[i], comments[i]))
        logging.warning("数据已保存在 %s" % (savepath))


if __name__ == "__main__":
    list = ['手机']
    for item in list:
        spider = JDSpider(item)
        spider.getData(10, 3)
        spider.getData(10, 1)