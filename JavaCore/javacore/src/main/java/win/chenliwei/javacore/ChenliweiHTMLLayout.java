/**
 * @author: ChenLiwei
 * 2017-02-23
 * ChenliweiHTMLLayout.java
 * Comments: It is to overwrite the default layout which does not print the event time
 */
package win.chenliwei.javacore;

import java.text.SimpleDateFormat;

import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.helpers.Transform;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class ChenliweiHTMLLayout extends HTMLLayout {
	private StringBuffer sbuf = new StringBuffer(BUF_SIZE);
	
	public ChenliweiHTMLLayout() {
		super();
		
	}

	@Override
	public String format(LoggingEvent event) {
	    if(sbuf.capacity() > MAX_CAPACITY) {
	        sbuf = new StringBuffer(BUF_SIZE);
	      } else {
	        sbuf.setLength(0);
	      }

	      sbuf.append(Layout.LINE_SEP + "<tr>" + Layout.LINE_SEP);

	      sbuf.append("<td>");
	      SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      sbuf.append(sdf.format(event.getTimeStamp()));
	      sbuf.append("</td>" + Layout.LINE_SEP);

	      String escapedThread = Transform.escapeTags(event.getThreadName());
	      sbuf.append("<td title=\"" + escapedThread + " thread\">");
	      sbuf.append(escapedThread);
	      sbuf.append("</td>" + Layout.LINE_SEP);

	      sbuf.append("<td title=\"Level\">");
	      if (event.getLevel().equals(Level.DEBUG)) {
	        sbuf.append("<font color=\"#339933\">");
	        sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
	        sbuf.append("</font>");
	      }
	      else if(event.getLevel().isGreaterOrEqual(Level.WARN)) {
	        sbuf.append("<font color=\"#993300\"><strong>");
	        sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
	        sbuf.append("</strong></font>");
	      } else {
	        sbuf.append(Transform.escapeTags(String.valueOf(event.getLevel())));
	      }
	      sbuf.append("</td>" + Layout.LINE_SEP);

	      String escapedLogger = Transform.escapeTags(event.getLoggerName());
	      sbuf.append("<td title=\"" + escapedLogger + " category\">");
	      sbuf.append(escapedLogger);
	      sbuf.append("</td>" + Layout.LINE_SEP);

	      if(getLocationInfo()) {
	        LocationInfo locInfo = event.getLocationInformation();
	        sbuf.append("<td>");
	        sbuf.append(Transform.escapeTags(locInfo.getFileName()));
	        sbuf.append(':');
	        sbuf.append(locInfo.getLineNumber());
	        sbuf.append("</td>" + Layout.LINE_SEP);
	      }

	      sbuf.append("<td title=\"Message\">");
	      sbuf.append(Transform.escapeTags(event.getRenderedMessage()));
	      sbuf.append("</td>" + Layout.LINE_SEP);
	      sbuf.append("</tr>" + Layout.LINE_SEP);

	      if (event.getNDC() != null) {
	        sbuf.append("<tr><td bgcolor=\"#EEEEEE\" style=\"font-size : xx-small;\" colspan=\"6\" title=\"Nested Diagnostic Context\">");
	        sbuf.append("NDC: " + Transform.escapeTags(event.getNDC()));
	        sbuf.append("</td></tr>" + Layout.LINE_SEP);
	      }

	      String[] s = event.getThrowableStrRep();
	      if(s != null) {
	        sbuf.append("<tr><td bgcolor=\"#993300\" style=\"color:White; font-size : xx-small;\" colspan=\"6\">");
	        //appendThrowableAsHTML(s, sbuf);
	        sbuf.append("</td></tr>" + Layout.LINE_SEP);
	      }

	      return sbuf.toString();
	    }
}

