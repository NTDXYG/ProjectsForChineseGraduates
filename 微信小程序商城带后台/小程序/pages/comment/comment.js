// pages/comment/comment.js

const util = require('../../utils/util')

Page({
  onLoad: function (options) {
    let product = this.data.product
    product = JSON.parse(options.data)
    console.log(product)
    this.setData({
      product
    })
    this.getCommentList(product._id)
  },
  getCommentList(id) {
    wx.request({
      url: 'http://127.0.0.1:8081/wx/getAllCommentsById',
      data: {
        'id': id
      }, success: (res) => {
        this.setData({
          commentList: res.data.map(review => {
            review.time = util.formatTime(review.time, 'yyyy/MM/dd')
            return review
          })
        })
      }
   })
  }
})