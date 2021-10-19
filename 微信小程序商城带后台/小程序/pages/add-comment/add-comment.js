// pages/add-comment/add-comment.js
const app = getApp()
const util = require('../../utils/util')

Page({
  onLoad: function (options) {
    let product = this.data.product
    product = JSON.parse(options.data)
    this.setData({
      product
    })
  },

  onInput(event) {
    this.setData({
      commentValue: event.detail.value.trim()
    })
  },

  addComment(event) {
    let content = this.data.commentValue;
    if (!content) return
    wx.showLoading({
      title: '正在发表评论'
    })
    wx.request({
      url: 'http://127.0.0.1:8081/wx/addComment',
      data: {
        openid: app.globalData.openid,
        goods_id: this.data.product._id,
        content: content
      },
      success: (res) => {
        wx.hideLoading()
        wx.showToast({
          title: '发表评论成功'
        })
        setTimeout(() => {
          wx.navigateBack()
        }, 1500)
      }
    })
  },

})