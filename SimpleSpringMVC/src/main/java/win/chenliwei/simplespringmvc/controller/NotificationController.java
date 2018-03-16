package win.chenliwei.simplespringmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.wxpay.sdk.DS8WXPayByScan;
import com.github.wxpay.sdk.QRCodeUtil;
import com.github.wxpay.sdk.ResponseToWXPay;

@Controller
public class NotificationController {
	@SuppressWarnings("unused")
	private ResponseToWXPay responseToWXPay = new ResponseToWXPay();
	private DS8WXPayByScan ds8wxPayByScan = new DS8WXPayByScan();
	final static Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@RequestMapping(value="/response2wxnotify")
	public String response2WXNotify(HttpServletRequest req) throws Exception{
		logger.info("Got the call back function from wx pay");
		String QRPath = Thread.currentThread().getContextClassLoader().getResource("").toString();
		System.out.println(QRPath);
		QRPath = QRPath.replace("/", "//").replace("file://","") + "..//..//resources//PAY_Visit.JPG";
		QRCodeUtil.genQRCode("WX Pay visited",QRPath);
		return "ThankYou";
	}
	@RequestMapping(value="/wxpay")
	public String wxPay(HttpServletRequest req) throws Exception{
		logger.info("Got the wx pay from the client");
		String QRPath = Thread.currentThread().getContextClassLoader().getResource("").toString();
		System.out.println(QRPath);
		QRPath = QRPath.replace("/", "//").replace("file://","") + "..//..//resources//QR_Code.JPG";
		ds8wxPayByScan.setWXParameters("2000","5K8264ILTKCH16DQ2501SL8ZNMTM67VS","55555555555555");
		String requestXML = ds8wxPayByScan.genRequestXML();
		logger.info(QRPath);
		String PayLink = ds8wxPayByScan.parseWXPayLink(ds8wxPayByScan.requestWX(DS8WXPayByScan.URL4REQURESTPAY,requestXML));
		QRCodeUtil.genQRCode(PayLink,QRPath);
		return "qrcodewait4pay";
	}
}
