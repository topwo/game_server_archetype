package com.kidbear.pay.client.sdk.jifeng.client;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ************************************
 * 
 * @description：发送和响应返回结果
 * @author
 * @time 2013-3-6 下午6:58:11
 * @version copyright mAPPn
 ************************************
 *
 */
public class JifengUtil {

	// channelID、APP_KEY
	public static String APP_KEY = "";
	// 密钥
	public static String SECRET_KEY = "";
	// 开发者ID
	public static String U_ID = "";

	/**
	 * 
	 * @description：发送加密请求信息
	 * @author
	 * @time 2013-3-7 下午3:34:47
	 * @param request
	 * @param urlStr
	 * @return String:
	 */
	public static String doEncryptRequest(String request, String urlStr) {
		// 加密发送包体加密方法：base64( TEA(发送内容， key))。 key是由机锋网分配的16字节密钥，TEA采用16轮迭代。
		byte[] requestBytes = request.getBytes();
		byte[] secretKeyBytes = SECRET_KEY.getBytes();
		byte[] encryptBytes = new Crypter().encrypt(requestBytes,
				secretKeyBytes);
		@SuppressWarnings("restriction")
		String requestStr = new BASE64Encoder().encode(encryptBytes);
		return doRequest(requestStr, urlStr);
	}

	/**
	 * 
	 * @description：发送POST请求
	 * @author xinge21
	 * @time 2013-3-7 下午3:40:30
	 * @param request
	 * @param urlStr
	 * @return String:
	 */
	public static String doRequest(String request, String urlStr) {
		int responseCode = 0;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(60000);
			// User-Agent:值为 packageName=?,appName=?,channelid=?(分隔符为英文逗号)
			conn.setRequestProperty("User-Agent",
					"packageName=com.mappn.sdk.demo.api,appName=GfanSDK_API_Demo,channelID="
							+ APP_KEY);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			Writer writer = new OutputStreamWriter(conn.getOutputStream(),
					"UTF-8");
			writer.write(request);
			writer.close();
			responseCode = conn.getResponseCode();
			System.out.println(responseCode);
			Reader in = new InputStreamReader(conn.getInputStream(), "UTF-8");
			char[] arr = new char[1024];
			int len = 0;
			while ((len = in.read(arr)) != -1) {
				buffer.append(arr, 0, len);
			}
			in.close();
			conn.disconnect();

			System.out.println(buffer.toString());
		} catch (MalformedURLException e) {

		} catch (UnsupportedEncodingException e) {

		} catch (IOException e) {

		} finally {

		}

		return buffer.toString();
	}
}
