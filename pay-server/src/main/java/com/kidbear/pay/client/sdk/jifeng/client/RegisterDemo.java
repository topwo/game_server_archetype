package com.kidbear.pay.client.sdk.jifeng.client;

/**
 * ************************************ 
 * @description：注册用户DEMO
 * @author 
 * @time 2013-3-6 下午6:52:40
 * @version 
 * copyright mAPPn
 ************************************
*
 */
public class RegisterDemo {
	public static void main(String[] args) {

		StringBuffer request = new StringBuffer(1024);
		request.append("<request>");
		request.append("<username>xindebug001</username>");
		request.append("<password>123456</password>");
		request.append("<email>xindebug001@google.cn</email>");
		request.append("</request>");

		String urlStr = "http://api.gfan.com/uc1/common/register";
		JifengUtil.doEncryptRequest(request.toString(), urlStr);
	}
}
