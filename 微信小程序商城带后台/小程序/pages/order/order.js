// pages/order/order.js

const app = getApp()
const util = require('../../utils/util')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
    orderList: [], // 订单列表
  },

  onShow() {
    let that = this
    if (this.data.orderList.length == 0) {
      wx.showLoading({
        title: '刷新订单数据...',
      })
      wx.request({
        url: 'http://127.0.0.1:8081/wx/getBuyOrder',
        data: {
          openid: app.globalData.openid
        },
        success: (res) => {
          wx.hideLoading()
          that.setData({
            orderList: res.data
          })
        }
      })
    }
  },


  goComment(event) {
    let product = event.currentTarget.dataset.item
    wx.navigateTo({
      url: '/pages/add-comment/add-comment?data=' + JSON.stringify(product),
    })
  }

})