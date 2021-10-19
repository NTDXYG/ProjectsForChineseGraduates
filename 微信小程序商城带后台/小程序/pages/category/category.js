// pages/home/home.js

const util = require('../../utils/util.js')

Page({
  data: {
    // 商品列表
    productList: [],
    issearch: false,
    category: [],
  },
  switchCategory: function (e) {
    let data = e.currentTarget.dataset.value;
    this.setData({
      currentCategory: data
    })
  },
  onLoad: function (options) {
    wx.request({
      url: 'http://127.0.0.1:8081/wx/getAllTypes',
      success: (res) => {
        const cate = res.data;
        this.setData({
          category:res.data,
          currentCategory: res.data[0]
        })
        var that = this;
        wx.request({
          url: 'http://127.0.0.1:8081/wx/getAllGoods',
          success: (res) => {
            that.setData({
              'list':res.data
            })
            for (var i = 0; i < cate.length; i++){
              for (var j = 0, len = res.data.length; j < len; j++) {
                if (res.data[j].type_id == cate[i].id) {
                  cate[i].children.push(res.data[j])
                }
              }
            }
            that.setData({
              category: cate,
              currentCategory: cate[0]
            })
          }
        })
      }
    })
  },
  searchIcon(e) {
    if (e.detail.value != '') {
      let key = e.detail.value.toLowerCase();
      let list = this.data.list;
      for (let i = 0; i < list.length; i++) {
        let a = key;
        let b = list[i].name.toLowerCase();
        if (b.search(a) != -1) {
          list[i].isShow = true
        } else {
          list[i].isShow = false
        }
      }
      this.setData({
        list: list,
        issearch: true
      })
    } else {
      this.setData({
        issearch: false
      })
    }

  }
})