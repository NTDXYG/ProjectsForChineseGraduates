// pages/user/user.js
const util = require('../../utils/util')
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
  },

  onShow() {
    if (app.globalData.openid != null) {
      this.setData({
        userInfo: app.globalData.userInfo
      })
    }
  },

  onTapLogin(event) {
    if (app.globalData.openid == null) {
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          wx.login({
            success: login_res => {
              // 发送 res.code 到后台换取 openId, sessionKey, unionId
              wx.request({
                url: 'http://127.0.0.1:8081/wx/login',
                data: {
                  code: login_res.code,
                  rawData: res.rawData, //用户非敏感信息
                  signature: res.signature, //签名
                  encrypteData: res.encryptedData, //用户敏感信息
                  iv: res.iv //解密算法的向量
                },
                success: (res) => {
                  app.globalData.openid = res.data.msg;
                  this.onShow()
                }
              })
            }
          })
        }
      })
    }
  },

  onTapAddress() {
    wx.navigateTo({
      url: '/pages/order/order',
    })
  },

  onTapSetting() {
    wx.openSetting({
      success(res) {
        console.log(res.authSetting)
        // res.authSetting = {
        //   "scope.userInfo": true,
        //   "scope.userLocation": true
        // }
      }
    })
  },
  onTapService() {
    wx.showModal({
      title: '客服电话',
      content: '18888888888',
      success(res) {
        if (res.confirm) {
          console.log('用户点击确定')
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })
  },
  onTapAbout() {
    wx.showToast({
      title: '谢谢欣赏',
      icon: 'success',
      duration: 2000
    })
  }
})