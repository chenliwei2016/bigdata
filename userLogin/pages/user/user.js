// pages/user/user.js    
var app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {}
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  
  onLoad: function () {
    console.log('user page onLoad')
    var that = this
    // app.globalData.username = "aaa"
    if(app.globalData.username == null){
      wx.redirectTo({
        url: '../login/login',
        success: function(res){
          // success
        },
        fail: function(res) {
          // fail
        },
        complete: function(res) {
          // complete
        }
      })
    } else {
      var that = this
        wx.login({
      success: function() {
        wx.getUserInfo({
        success: function(res) {
          that.setData({userInfo : res.userInfo})
        }
      })
      }
     })
     that.setData({motto : "Now you login in already, \n user=" + app.globalData.username + "\n" + "password=" + app.globalData.password})
    }
  }
})
