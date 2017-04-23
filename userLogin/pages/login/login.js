// pages/login/login.js
var app = getApp()

Page({
  data:{
    username: null,
    password: null
  },
  onLoad:function(options){
    // 页面初始化 options为页面跳转所带来的参数
  },
  onReady:function(){
    // 页面渲染完成
  },
  onShow:function(){
    // 页面显示
  },
  onHide:function(){
    // 页面隐藏
  },
  onUnload:function(){
    // 页面关闭
  },
  inputuser : function(e) {
    this.setData({username : e.detail.value})
  },
  inputpasswd : function(e) {
    this.setData({password : e.detail.value})
  },
  loginClick : function() {
    app.globalData.username = this.data.username
    app.globalData.password = this.data.password
    wx.switchTab({
      url: '../user/user',
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
  }
})