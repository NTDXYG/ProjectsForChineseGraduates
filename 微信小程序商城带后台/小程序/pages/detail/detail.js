const app = getApp();
var WxParse = require('../../utils/wxParse/wxParse.js');  
const util = require('../../utils/util.js')

Page({
  data: {
    product: {},
  },
  /**
    * 生命周期函数--监听页面加载
    */
  onLoad: function (options) {
    let id = options.id
    this.getProduct(id)
    this.getRecomList(id)
    this.getCommentList(id)
    var xq = "<p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd32e913.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd348179.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd36cb48.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd38c059.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd3c2fb1.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd3c5a0f.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd3d6fed.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd41f5ef.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd428a82.jpg\" style=\"\"/></p><p><img src=\"http://shopdz.pm.comsenz-service.com/data/Attach/Common/2016-06-21/57690dd42dab3.jpg\" style=\"\"/></p><p><br/></p>"
    this.setData({
      "xq": xq
    })
    WxParse.wxParse('xq', 'html', xq, this, 5);
  },
  getRecomList(id) {
    let self = this
    wx.request({
      url: 'http://127.0.0.1:8081/wx/getRecomList',
      data: ({
        id: id
      }),
      success: (res) => {
        self.setData({
          recomList: res.data
        })
      }
    })
  },
  getCommentList(id) {
    let self = this
    wx.request({
      url: 'http://127.0.0.1:8081/wx/getCommentCountById',
      data: ({
        id: id
      }),
      success: (res) => {
        self.setData({
          reviewCount: res.data
        })
      }
    })
  },
  getProduct(id) {
    let self = this
    wx.showLoading({
      title: '正在加载中...',
    })
    wx.request({
      url: 'http://127.0.0.1:8081/wx/getGoodsById',
      data:({
        id:id
      }),
      success: (res) => {
        wx.hideLoading()
        self.setData({
          product: res.data
        })
      }
    })
    
  },

  buy() {
    let d = this.data.product._id
    console.log(d)
    wx.showLoading({
      title: '正在购买...',
    })
    if (app.globalData.openid == null) {
      wx.hideLoading()
      wx.showModal({
        title: '提示',
        content: '请先登录',
        success(res) {
          if (res.confirm) {
            wx.switchTab({
              url: '../user/user',
            })
          }
        }
      })
    } else {
      wx.request({
        url: 'http://127.0.0.1:8081/wx/buyOrder',
        data: {
          'user_openid': app.globalData.openid,
          'goods_id': d
        }, success: (res) => {
          wx.hideLoading();
          wx.showToast({
            title: '购买成功'
          })
        }
      })
    }
  },

  addToTrolley() {
    let d = this.data.product._id
    console.log(d)
    wx.showLoading({
      title: '正在添加到购物车...',
    })
    if (app.globalData.openid == null) {
      wx.hideLoading()
      wx.showModal({
        title: '提示',
        content: '请先登录',
        success(res) {
          if (res.confirm) {
            wx.switchTab({
              url: '../user/user',
            })
          }
        }
      })
    } else {
      wx.request({
        url: 'http://127.0.0.1:8081/wx/addOrder',
        data: {
          'user_openid': app.globalData.openid,
          'goods_id': d
        }, success: (res) => {
          wx.hideLoading();
          wx.showToast({
            title: '已添加到购物车'
          })
        }
      })
    }
  },

  onTapCommentEntry() {
    let product = this.data.product

    wx.navigateTo({
      url: `/pages/comment/comment?data=${JSON.stringify(this.data.product)}`
    })

  }
})