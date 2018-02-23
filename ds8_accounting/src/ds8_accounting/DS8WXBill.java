/**
 * 
 */
package ds8_accounting;

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
/**
 * @author leavy
 *
 */
public class DS8WXBill {
	
	public static final String BILL_URL = "https://api.mch.weixin.qq.com/pay/downloadbill"; //URL for downloading bill
	public static final String ENCODING = "UTF-8";
	

	private SortedMap<String,String> WXParameters = new TreeMap<String,String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DS8WXBill Bill20180211 = new DS8WXBill();
		String bill_date="20180211";
		Bill20180211.setWXParameters(bill_date);
		Bill20180211.requestWXBill(Bill20180211.genRequestXML());
	}

	
	public String genRequestXML(){

		
		return "<xml><appid>" + WXParameters.get("appid") 
				+ "</appid><bill_date>" + WXParameters.get("bill_date")  
				+ "</bill_date><bill_type>" + WXParameters.get("bill_type")  
				+ "</bill_type><mch_id>"+ WXParameters.get("mch_id")  
				+ "</mch_id><nonce_str>" + WXParameters.get("nonce_str")  
				+ "</nonce_str><sign_type>" + WXParameters.get("sign_type")  
				+ "</sign_type><sign>"+ WXParameters.get("sign") + "</sign></xml>";
		
	}
	
	public void setWXParameters(String bill_date){
		WXParameters.put("appid","wx157b582419826eb6");
		WXParameters.put("bill_date",bill_date);
		WXParameters.put("bill_type","ALL");
		WXParameters.put("mch_id","1238106702");//�̻���
		WXParameters.put("nonce_str","5K8264ILTKCH16CQ2502SL8ZNMTM67VS");
		WXParameters.put("sign_type","MD5");
		WXParameters.put("key","B9YVRfvAQ9zRq77emS8ukQmGEbRzwRb3");
		WXParameters.put("sign",createSignMD5());
	}
	
	public String createSignMD5(){
        StringBuffer StrBuf = new StringBuffer();
        Set<Entry<String, String>> es = WXParameters.entrySet();//���в��봫�εĲ�������accsii��������
        Iterator<Entry<String, String>> it = es.iterator();
        while(it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue(); 
            if(!k.contentEquals("key")) StrBuf.append(k + "=" + v + "&" );
        }
        
        
        //key����΢��֧���˻���Կ
        StrBuf.append("key=" + WXParameters.get("key"));
        //������� ����MD5��ʱ����Ҫͳһ���룬����΢��apiҪ����UTF-8
        String enc = ENCODING;
        String sign = MD5Util.MD5Encode(StrBuf.toString(), enc).toUpperCase();
        return sign;
	}
	
	
	
	public void requestWXBill(String RequestXML){
        BufferedReader br = null;
        HttpURLConnection con = null;
               try{ 
                   URL url = new URL(BILL_URL);
                   con = (HttpURLConnection) url.openConnection();
                // ����POST�������������������
                   con.setDoOutput(true);  // POST��ʽ
                   con.setDoInput(true);
                   con.setUseCaches(false);
                   con.setRequestMethod("POST");
                // ����ͨ�õ���������
                   con.setRequestProperty("accept", "*/*");
                   con.setRequestProperty("connection", "Keep-Alive");
                   con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                    
                   // �������д����
                  try( OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(),ENCODING)){ 
	                   out.write(new String(RequestXML.getBytes(ENCODING)));
	                   out.flush(); 
                  	 }
                   br = new BufferedReader(new InputStreamReader(con 
                           .getInputStream(),ENCODING)); 
                  String line = ""; 
                  StringBuffer buffer = new StringBuffer();

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
           } 
}
