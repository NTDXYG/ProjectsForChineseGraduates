// pages/trolley/trolley.js

const app = getApp()
const util = require('../../utils/util')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    length: 0,
    userInfo: null,
    trolleyList: [], // 购物车商品列表
    trolleyCheckMap: {}, // 购物车中选中的id哈希表
    trolleyAccount: 0, // 购物车结算总价
    isTrolleyEdit: false, // 购物车是否处于编辑状态
    isTrolleyTotalCheck: false, // 购物车中商品是否全选
  },


  onShow() {
    if (app.globalData.openid != null) {
      this.setData({
        userInfo: app.globalData.userInfo
      })
      wx.request({
        url: 'http://127.0.0.1:8081/wx/getAllOrders',
        data: {
          openid: app.globalData.openid
        },
        success: (res) => {
          this.setData({
            trolleyAccount: util.formatPrice(0),
            trolleyList: res.data,
            length: res.data.length
          })
        }
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
  onTapCheckSingle(event) {
    let checkId = event.currentTarget.dataset.id
    let trolleyCheckMap = this.data.trolleyCheckMap
    let trolleyList = this.data.trolleyList
    let isTrolleyTotalCheck = this.data.isTrolleyTotalCheck
    let trolleyAccount = this.data.trolleyAccount
    // 单项商品被选中/取消
    trolleyCheckMap[checkId] = !trolleyCheckMap[checkId];
    isTrolleyTotalCheck = true
    trolleyList.forEach(product => {
      if (!trolleyCheckMap[product.goods_id]) {
        isTrolleyTotalCheck = false
      }
    });
    trolleyAccount = this.calcAccount(trolleyList, trolleyCheckMap);
    this.setData({
      trolleyCheckMap,
      isTrolleyTotalCheck,
      trolleyAccount
    })
  },

  onTapCheckTotal() {
    let trolleyCheckMap = this.data.trolleyCheckMap
    let trolleyList = this.data.trolleyList
    let isTrolleyTotalCheck = this.data.isTrolleyTotalCheck
    let trolleyAccount = this.data.trolleyAccount

    // 全选按钮被选中/取消
    isTrolleyTotalCheck = !isTrolleyTotalCheck

    // 遍历并修改所有商品的状态
    trolleyList.forEach(product => {
      trolleyCheckMap[product.goods_id] = isTrolleyTotalCheck
    })
    trolleyAccount = this.calcAccount(trolleyList, trolleyCheckMap)
    this.setData({
      isTrolleyTotalCheck,
      trolleyCheckMap,
      trolleyAccount
    })

  },

  calcAccount(trolleyList, trolleyCheckMap) {
    let account = 0
    trolleyList.forEach(product => {
      account = trolleyCheckMap[product.goods_id] ? account + product.goods.price * product.count : account
    })
    return util.formatPrice(account)
  },

  onTapEdit() {
    let isTrolleyEdit = this.data.isTrolleyEdit
    if (isTrolleyEdit) {
      this.updateTrolley()
    } else {
      this.setData({
        isTrolleyEdit: !isTrolleyEdit
      })
    }
  },

  adjustTrolleyCount(event) {
    let trolleyCheckMap = this.data.trolleyCheckMap
    let trolleyList = this.data.trolleyList
    let dataset = event.currentTarget.dataset
    let adjustType = dataset.type
    let productId = dataset.id
    let product
    let index

    for (index = 0; index < trolleyList.length; index++) {
      if (productId === trolleyList[index].goods_id) {
        product = trolleyList[index]
        break
      }
    }

    if (product) {
      if (adjustType === "add") {
        product.count++
      } else {
        if (product.count <= 1) {
          // 商品数量不超过1，点击减号相当于删除
          delete trolleyCheckMap[productId]
          trolleyList.splice(index, 1)
        } else {
          // 商品数量大于1
          product.count--
        }
      }
    }

    // 调整结算总价
    let trolleyAccount = this.calcAccount(trolleyList, trolleyCheckMap)

    this.setData({
      trolleyAccount,
      trolleyList,
      trolleyCheckMap
    })

  },
  updateTrolley() {
    wx.showLoading({
      title: '更新购物车数据...',
    })
    wx.request({
      url: 'http://127.0.0.1:8081/wx/updateOrder',
      data: {
        list: this.data.trolleyList,
        openid:app.globalData.openid
      },
      success: (res) => {
        wx.hideLoading()
        wx.showToast({
          title: '更新购物车成功'
        })
        this.onShow()
      }
    })
    this.setData({
      isTrolleyEdit: false
    })
  },

  onTapPay() {
    if (!this.data.trolleyAccount) return
    let self = this
    let trolleyCheckMap = this.data.trolleyCheckMap
    let trolleyList = this.data.trolleyList
    let needToPayProductList = trolleyList.filter(product => {
      return !!trolleyCheckMap[product.goods_id]
    })
    wx.showLoading({
      title: '更新结算...',
    })
    wx.request({
      url: 'http://127.0.0.1:8081/wx/buyAllOrder',
      data: {
        list: needToPayProductList,
        openid: app.globalData.openid
      },
      success: (res) => {
        wx.hideLoading()
        wx.showToast({
          title: '购买成功'
        })
        this.setData({
          isTrolleyTotalCheck: false, // 购物车中商品是否全选
          trolleyCheckMap: {}
          })
        this.onShow()
      }
    })
  },
})