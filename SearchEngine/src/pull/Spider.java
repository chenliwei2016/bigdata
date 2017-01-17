package pull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sleepycat.je.*;

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.*;



public class Spider {
	
	final static Logger log = LoggerFactory.getLogger(Spider.class);
	
	public static org.apache.hadoop.conf.Configuration hbaseConfig; 
	 static { 
		 hbaseConfig = HBaseConfiguration.create();  
		 hbaseConfig.set("hbase.master", "192.168.58.191:600000");
	    } 

	 //private static final String userAgent = "Mozilla/5.0 (jsoup)";
	 //private static final int timeout = 5 * 1000;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO start Berkeley DB
		Environment berkeleyDbEnv = null;
		EnvironmentConfig berkeleyDbEnvConf = new EnvironmentConfig();
		berkeleyDbEnvConf.setAllowCreate(true);
		berkeleyDbEnvConf.setTransactional(false);
		berkeleyDbEnv = new Environment(new File("/home/hadoop/berkeleyDb.conf"),berkeleyDbEnvConf);
		
		DatabaseConfig berkeleyDbConf = new DatabaseConfig();
		berkeleyDbConf.setAllowCreate(true);
		Database berkeleyDb = berkeleyDbEnv.openDatabase(null, "NewsDB", berkeleyDbConf);
		
		 HConnection hbaseConn = HConnectionManager.createConnection(hbaseConfig);
		 HTable news = (HTable) hbaseConn.getTable("news"); 
		
		// analysis web page
		String url = "/home/hadoop/news163.html";
		Document doc = Jsoup.parse(new File(url), "gb2312");
		Element elementDiv = doc.getElementsByClass("area-half left").first();
		Elements elementLinks = elementDiv.getElementsByTag("A");
		 for(Element aLink : elementLinks){
			String linkText = aLink.text();
			String link = aLink.attr("href").toString();
			System.out.println(linkText + ":" + link);
			DatabaseEntry linkKey = new DatabaseEntry(link.getBytes("gb2312"));
			DatabaseEntry linkValue = new DatabaseEntry(linkText.getBytes("gb2312"));
			 
			if(berkeleyDb.get(null, linkKey, linkValue, LockMode.DEFAULT) == OperationStatus.NOTFOUND){
				//store it to Berkeley DB for duplication check in future
				berkeleyDb.put(null, linkKey, linkValue);
				//store it to HBase for persist
				Put newsItem = new Put(linkKey.getData());
				 newsItem.add("cf".getBytes(), null, linkValue.getData());
				 news.put(newsItem);
			}
			
			
			log.info("test");
		 }
		 
		 news.close();
		 hbaseConn.close();
		 
		//close the Berkeley DB
		 if(berkeleyDbEnv != null){
			 berkeleyDb.close();
			 berkeleyDbEnv.cleanLog();
			 berkeleyDbEnv.close();
		 }
		 
	}
	
}
