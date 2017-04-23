//index.js
Page({
    loginClick : function(){
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