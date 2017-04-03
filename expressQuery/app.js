//app.js
App({
  onLaunch: function () {
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
  },
  getUserInfo:function(cb){
    var that = this
    if(this.globalData.userInfo){
      typeof cb == "function" && cb(this.globalData.userInfo)
    }else{
      //调用登录接口
      wx.login({
        success: function () {
          wx.getUserInfo({
            success: function (res) {
              that.globalData.userInfo = res.userInfo
              typeof cb == "function" && cb(that.globalData.userInfo)
            }
          })
        }
      })
    }
  },
  globalData:{
    userInfo:null
  },
  getExpressInfo:function(nu,cb){
    
    wx.request({
      url: 'http://apis.baidu.com/kuaidicom/express_api/express_api?muti=0&order=desc&nu='+nu, //仅为示例，并非真实的接口地址
      data: {
        x: '',
        y: ''
      },
      header: {
        'apikey': 'ec1dfebcf2ef7ecb8c164cd6111ac4a9'
      },
      success: function (res) {
        //console.log(res.data)
        cb(res.data);
      }
    })
  }
})