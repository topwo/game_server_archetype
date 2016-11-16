#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.sdk.xiaomi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	private static int connectionTimeOut = 30 * 1000;
	private static int timeOut = 30 * 1000;
	
	/**
	 * http get 請求
	 * @param urlString 請求地址
	 * @return 返回的內容
	 */
	public static String doGet(String urlString) {
		InputStream is = null;
	    BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setConnectTimeout(connectionTimeOut);
			conn.setReadTimeout(timeOut);
	       
	        StringBuffer buffer = new StringBuffer();
	        String line = null;

	        conn.connect();
	        is = conn.getInputStream();
//	        inputStreamReader = new InputStreamReader(inputStream);
	        reader = new BufferedReader(new InputStreamReader(is));
	            
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }
	        tryClose(is);
	        tryClose(reader);
	        return buffer.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	private static void tryClose(InputStream is ) {
		try{
			if( null != is ) {
				is.close();
				is = null;
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭Reader
	 * @param reader
	 */
	private static void tryClose(java.io.Reader reader ) {
		try{
			if( null != reader ) {
				reader.close();
				reader = null;
			}
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
}
