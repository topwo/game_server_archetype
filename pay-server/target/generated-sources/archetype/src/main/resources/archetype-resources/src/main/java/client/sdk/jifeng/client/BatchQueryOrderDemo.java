#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.sdk.jifeng.client;

/**
 * ************************************ 
 * @description：订单查询DEMO
 * @author 
 * @time 2013-3-6 下午6:50:07
 * @version 
 * copyright mAPPn
 ************************************
*
 */
public class BatchQueryOrderDemo {
	public static void main(String[] args) {

		//单订单查询接口
		StringBuffer request = new StringBuffer();
		request.append("<request>");
		request.append("<order_id>00002010090922</order_id>");
		request.append("<app_key>"+JifengUtil.APP_KEY+"</app_key>");
		request.append("</request>");

		String urlStr = "http://api.gfan.com/sdk/${artifactId}/queryAppPayLog";
		JifengUtil.doEncryptRequest(request.toString(), urlStr);
		
		//多订单查询接口
		StringBuffer requestPayLog = new StringBuffer();
		requestPayLog.append("<request>");
		requestPayLog.append("<order>");
		requestPayLog.append("<order_id>00002010090922</order_id>");
		requestPayLog.append("<app_key>"+JifengUtil.APP_KEY+"</app_key>");
		requestPayLog.append("</order>");
		requestPayLog.append("<order>");
		requestPayLog.append("<order_id >00002010090923</order_id>");
		requestPayLog.append("<app_key>"+JifengUtil.APP_KEY+"</app_key>");
		requestPayLog.append("</order>");
		requestPayLog.append("</request>");

		String urlStr1 = "http://api.gfan.com/sdk/${artifactId}/queryMultipleAppPayLog";
		JifengUtil.doEncryptRequest(requestPayLog.toString(), urlStr1);
	}
}
