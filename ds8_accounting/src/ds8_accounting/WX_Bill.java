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
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WX_Bill {

	/** ����Ĳ��� */
    protected SortedMap parameters;
     
    protected HttpServletRequest request;
     
    protected HttpServletResponse response;
    /** ��Կ */
    protected String key;
    /** debug��Ϣ */
    protected String debugInfo;
     
    /**
    *��ȡ��Կ
    */
    public String getKey() {
        return key;
    }
    /**
    *������Կ
    */
    public void setKey(String key) {
        this.key = key;
    }
     
    /**
     * post������������
     * @param urlStr
     */
    public  void testPost(String urlStr) { 
         BufferedReader br= null;
         HttpURLConnection con=null;
         OutputStreamWriter out=null;
                try { 
                    URL url = new URL(urlStr); 
                   // URLConnection con = url.openConnection();
                    con = (HttpURLConnection) url.openConnection();
                 // ����POST�������������������
                    con.setDoOutput(true);  // POST��ʽ
                    con.setDoInput(true);
                    con.setUseCaches(false);
                    con.setRequestMethod("POST");
                 // ����ͨ�õ���������
                    con.setRequestProperty("accept", "*/*");
                    con.setRequestProperty("connection", "Keep-Alive");
                    con.setRequestProperty("user-agent",
                            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                     
//                  con.setRequestProperty("Pragma:", "no-cache"); 
//                  con.setRequestProperty("Cache-Control", "no-cache"); 
//                  con.setRequestProperty("Content-Type", "text/xml"); 
                     
                    // �������д����
                     out = new OutputStreamWriter(con 
                           .getOutputStream(),"UTF-8");   
                  
                     
                    //		 WX_MCH_ID=1238106702  �̻���
                    //		 WX_APP_ID=wx157b582419826eb6 appid
                    //		 WX_SIGN_KEY=B9YVRfvAQ9zRq77emS8ukQmGEbRzwRb3  ��Կ
                    //		 # App Secret (Ӧ����Կ) (��ȡ token ʱ������)
                    //		 WX_APP_SECRET=71b5d44e118bae8f2db225e165e39235
                     
                 // �����������
                    String xmlInfo = GetpayXmlRequest("wx157b582419826eb6","1238106702","1462240367309","B9YVRfvAQ9zRq77emS8ukQmGEbRzwRb3","20180112","ALL"); 
                    System.out.println("urlStr=" + urlStr); 
                    System.out.println("xmlInfo=" + xmlInfo); 
                   out.write(new String(xmlInfo.getBytes("UTF-8")));
                // flush������Ļ���
                    out.flush(); 
                   // out.close(); 
                  //��ȡ����������Ӧ���ݲ���ʾ ����BufferedReader����������ȡURL����Ӧ
                    br = new BufferedReader(new InputStreamReader(con 
                            .getInputStream(),"UTF-8")); 
                   String line = ""; 
                   StringBuffer buffer = new StringBuffer();
//                  for (line = br.readLine(); line != null; line = br.readLine()) { 
//                     System.out.println(line); 
//                     buffer.append(line);// ��������������ӵ� buffer ��
//                     buffer.append("\n"); // ��ӻ��з�
//
//                  }
                    while ((line = br.readLine()) != null)
                    {  
                    buffer.append(line); // ��������������ӵ� buffer ��
                    buffer.append("\n"); // ��ӻ��з�
                    System.out.println(line); 
                    } 
                     
                    //con.getOutputStream().close();
                }
                catch (MalformedURLException e)
                { 
                    e.printStackTrace(); 
                }
                catch (IOException e)
                { 
                    e.printStackTrace(); 
                } 
                // ʹ��finally�����ر�������
                finally {
                    try {
                        if(out!=null){
                            out.close();
                        }
 
                        if (br != null)
                        {
                            br.close();
                            con.disconnect();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
 
            } 
	
	String keywx = "9e4b63793722c61a555696be897d61f8";
    /**
     * ΢��֧��ǩ���㷨sign
     * ����md5ժҪ,������:����������a-z����,������ֵ�Ĳ������μ�ǩ����
                 ����ǩ�� ǩ�� sign   
     */
    protected void  createSign(SortedMap<Object,Object> parameters) {
        StringBuffer sb = new StringBuffer();
        //parameters ��������ֵ
        Set es = parameters.entrySet();//���в��봫�εĲ�������accsii��������
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue(); 
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v+ "&" );
            }
        }
        
        
        //key����΢��֧���˻���Կ
        sb.append("key=" + keywx);
        //sb.append("key=" + this.getKey());
        System.out.println("�ַ���ƴ�Ӻ��ǣ�"+sb.toString()); 
        //������� ����MD5��ʱ����Ҫͳһ���룬����΢��apiҪ����UTF-8
        String enc = getCharacterEncoding(this.request, this.response);
        //����MD5ǩ����֤ ע��ת���ɴ�д  �ڴ���Сд
        String sign = MD5Util.MD5Encode(sb.toString(), enc).toUpperCase();
         
        this.setParameter("sign", sign);
         
        //debug��Ϣ
        this.setDebugInfo(sb.toString() + " => sign:" + sign);
        return ;
    }
        /**
    *����debug��Ϣ
    */
    protected void setDebugInfo(String debugInfo) {
        this.debugInfo = debugInfo;
    }
    /**
     * ���ò���ֵ
     * @param parameter ��������
     * @param parameterValue ����ֵ
     */
    public void setParameter(String parameter, String parameterValue) {
        String v = "";
        if(null != parameterValue) {
            v = parameterValue.trim();
        }
        this.parameters.put(parameter, v);
    }
    /**
     * ��ȡ�����ַ���
     * @param request
     * @param response
     * @return String
     */
    public static String getCharacterEncoding(HttpServletRequest request,
            HttpServletResponse response) {
         
        if(null == request || null == response) {
            return "gbk";
        }
         
        String enc = request.getCharacterEncoding();
        if(null == enc || "".equals(enc)) {
            enc = response.getCharacterEncoding();
        }
         
        if(null == enc || "".equals(enc)) {
            enc = "gbk";
        }
         
        return enc;
    }
    
    
    /**
     * ������װ��XML��ʽ����΢��
    * */
    public  String GetpayXmlRequest(String appid,String spid,String getNonce_str,String sign, String trans_time, String bill_type)
    { 
        /*
       <xml>
           <appid>wx8888888888888888</appid>
           <bill_date>20160426</bill_date>
           <bill_type>ALL</bill_type>
           <mch_id>1900000109</mch_id>
           <nonce_str>21df7dc9cd8616b56919f20d9f679233</nonce_str>
           <sign>E8778F1C4C953C0BF37AA45C3BC379D0</sign>
         </xml>
      */
         
               StringBuffer sb = new StringBuffer(); 
               sb.append("<xml>"); 
               sb.append("<appid><![CDATA["); 
               sb.append(appid); 
               sb.append("]]></appid>"); 
             
               sb.append("<mch_id><![CDATA["); 
               sb.append(spid); 
               sb.append("]]></mch_id>"); 
              
               sb.append("<nonce_str><![CDATA["); 
               sb.append(getNonce_str); 
               sb.append("]]></nonce_str>"); 
              
               sb.append("<sign><![CDATA["); 
               sb.append(sign); 
               sb.append("]]></sign>"); 
               
               sb.append("<bill_date><![CDATA["); 
               sb.append(trans_time); 
               sb.append("]]></bill_date>"); 
              
               sb.append("<bill_type><![CDATA["); 
               sb.append(bill_type); 
               sb.append("]]></bill_type>"); 
              
               sb.append("</xml>"); 
               return sb.toString(); 
  
                /*
                  
                sb.append("<appid>");
                sb.append(appid); 
                sb.append("</appid>");
                sb.append("<bill_date>"); 
                sb.append(trans_time); 
                sb.append("</bill_date>");
                sb.append("<bill_type>"); 
                sb.append(bill_type); 
                sb.append("</bill_type>");
                sb.append("<mch_id>");
                sb.append(spid);
                sb.append("</mch_id>");
                sb.append("<nonce_str>");
                sb.append(stamp); 
                sb.append("</nonce_str>");
                sb.append("<sign>"); 
                sb.append(sign);
                sb.append("</sign>");
                sb.append("</xml>"); 
               return sb.toString(); 
               */
  
            } 
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String url = "https://api.mch.weixin.qq.com/pay/downloadbill"; 
          
          new WX_Bill().testPost(url); 
 
             
          //΢��api�ṩ�Ĳ���   ��ֵ
            String appid="wx8888888888888888";
            String spid="1900000109";//       1221024001
            String getNonce_str="1462240367309";//1462240367309  ibuaiVcKdpRxkhJA
            String trans_time="20160623";
            String bill_type="ALL";
            String sign ="";
              
           // ��ȡֵ
            SortedMap<Object,Object> sparameters = new TreeMap<Object,Object>(); 
            sparameters.put("appid", appid);
            sparameters.put("mch_id", spid);
            sparameters.put("nonce_str", getNonce_str);
            sparameters.put("bill_date", trans_time); 
            sparameters.put("bill_type", bill_type); 
              
            // new HttpPostTest().createSign(sparameters);
    }

}
