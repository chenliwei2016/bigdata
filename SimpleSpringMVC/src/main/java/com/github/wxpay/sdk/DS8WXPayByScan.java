package com.github.wxpay.sdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.xml.stream.XMLStreamException;
/**
 * @author leavy
 *
 */
public class DS8WXPayByScan {
	
	public static final String URL4REQURESTPAY = "https://api.mch.weixin.qq.com/pay/unifiedorder"; //URL for downloading bill
	public static final String ENCODING = "UTF-8";
	public static final String WXPAYLINKSTART = "weixin://";
	public static final String WXPAYLINKEND = "]]></code_url>";
	public static final String WXQRPICLOCATION = "C://Users//leavy//Pictures";
	

	private SortedMap<String,String> WXParameters = new TreeMap<String,String>();

	public static void main(String[] args) throws MalformedURLException, IOException, XMLStreamException {
/*
		DS8WXPayByScan dS8WXPayByScan = new DS8WXPayByScan();
		String QRPath = Thread.currentThread().getContextClassLoader().getResource("").toString();
		dS8WXPayByScan.setWXParameters("1000","5K8264ILTKCH16CQ2501SL8ZNMTM67VS");
		String requestXML = dS8WXPayByScan.genRequestXML();
		System.out.println(requestXML);
		String PayLink = dS8WXPayByScan.parseWXPayLink(dS8WXPayByScan.requestWX(URL4REQURESTPAY,requestXML));
		System.out.println(PayLink);
		QRPath = QRPath + "../../resources";*/
		QRCodeUtil.genQRCode("test","C://Users//leavy//Documents//workspace-sts-3.8.2.RELEASE//.metadata//.plugins//org.eclipse.wst.server.core//tmp1//wtpwebapps//simplespringmvc//WEB-INF//classes//..//..//resources//QR_Code.JPG");
	}
	

	public String parseWXPayLink(String XMLResponse){
		return XMLResponse.substring(XMLResponse.indexOf(WXPAYLINKSTART),XMLResponse.indexOf(WXPAYLINKEND) );
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String genRequestXML(){

		return MapToXMLString.converter((Map)WXParameters);

	}
	
	//needs to be configurable below later
	public void setWXParameters(String device,String nonceStr,String out_trade_no ){
		WXParameters.put("appid","wx157b582419826eb6");
		WXParameters.put("mch_id","1238106702");//商户号
		WXParameters.put("nonce_str",nonceStr);
		WXParameters.put("sign_type","MD5");
		WXParameters.put("key","B9YVRfvAQ9zRq77emS8ukQmGEbRzwRb3");
		WXParameters.put("attach","ds8");
		WXParameters.put("body","NATIVE");
		WXParameters.put("detail", "Test Fee");
		WXParameters.put("notify_url", "http://172.20.10.6:8080/simplespringmvc/response2wxnotify");
		WXParameters.put("trade_type","NATIVE");
		WXParameters.put("product_id", "222222222222222222");
		WXParameters.put("out_trade_no",out_trade_no);
		WXParameters.put("total_fee", "1");
		WXParameters.put("spbill_create_ip", "172.20.10.6");
		WXParameters.put("time_start", "20180316160000");
		WXParameters.put("time_expire", "20180318160000");
		WXParameters.put("device_info",device);
		
		WXParameters.put("sign",createSignMD5());
	}
	
	public String createSignMD5(){
        StringBuffer StrBuf = new StringBuffer();
        Set<Entry<String, String>> es = WXParameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator<Entry<String, String>> it = es.iterator();
        while(it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue(); 
            if(!k.contentEquals("key")) StrBuf.append(k + "=" + v + "&" );
        }
        
        
        //key就是微信支付账户密钥
        StrBuf.append("key=" + WXParameters.get("key"));
        //字体编码 生成MD5的时候，需要统一编码，这里微信api要求是UTF-8
        String enc = ENCODING;
        String sign = MD5Util.MD5Encode(StrBuf.toString(), enc).toUpperCase();
        return sign;
	}

	
	public String requestWX(String URLStr,String RequestXML){
        BufferedReader br = null;
        HttpURLConnection con = null;
        StringBuffer buffer = new StringBuffer();
               try{ 
                   URL url = new URL(URLStr);
                   con = (HttpURLConnection) url.openConnection();
                // 发送POST请求必须设置如下两行
                   con.setDoOutput(true);  // POST方式
                   con.setDoInput(true);
                   con.setUseCaches(false);
                   con.setRequestMethod("POST");
                // 设置通用的请求属性
                   con.setRequestProperty("accept", "*/*");
                   con.setRequestProperty("connection", "Keep-Alive");
                   con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                    
                   // 输出流，写数据
                  try( OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(),ENCODING)){ 
	                   out.write(new String(RequestXML.getBytes(ENCODING)));
	                   out.flush(); 
                  	 }
                   br = new BufferedReader(new InputStreamReader(con 
                           .getInputStream(),ENCODING)); 
                  String line = ""; 

                   while ((line = br.readLine()) != null)
                   {  
                   buffer.append(line);
                   buffer.append("\n");
                   System.out.println(line);
                   } 
                    
               }
               catch (MalformedURLException e)
               { 
                   e.printStackTrace(); 
               }
               catch (IOException e)
               { 
                   e.printStackTrace(); 
               }

               return buffer.toString();
           } 
}
