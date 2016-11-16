#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.sdk.jifeng.client;


/**
 * ************************************ 
 * @description：登录DEMO
 * @author 
 * @time 2013-3-6 下午6:52:04
 * @version 
 * copyright mAPPn
 ************************************
*
 */
public class LoginDemo {
	
	public static void main(String[] args) {
		StringBuffer request = new StringBuffer();
		request.append("<request>");
		request.append("<username>tes2sfsst</username>");
		request.append("<password>1232131</password>");
		request.append("</request>");

		String urlStr = "http://api.gfan.com/uc1/common/login";
		JifengUtil.doEncryptRequest(request.toString(), urlStr);
	}
}
