// pages/home/home.js
const app = getApp()
const util = require('../../utils/util.js')

Page({
  onLoad: function(options) {
    wx.request({
      url: 'http://127.0.0.1:8081/wx/getAllGoods',
      success: (res) => {
        this.setData({
          'productList': res.data.slice(0, 14)
        })
      }
    })
  },
  addToTrolley(event) {
    let d = event.currentTarget.dataset.index
    console.log(d)
    wx.showLoading({
      title: '正在添加到购物车...',
    })
    if (app.globalData.openid == null){
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
    } else{
      wx.request({
        url: 'http://127.0.0.1:8081/wx/addOrder',
        data:{
          'user_openid': app.globalData.openid,
          'goods_id':d
        }, success: (res) => {
          wx.hideLoading();
          wx.showToast({
            title: '已添加到购物车'
          })
        }
      })
    }
  },
})