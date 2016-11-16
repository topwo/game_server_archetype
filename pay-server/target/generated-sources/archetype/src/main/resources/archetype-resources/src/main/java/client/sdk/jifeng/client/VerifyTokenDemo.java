#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.sdk.jifeng.client;


/**
 * @description  验证TOKEN
 * @author 
 * @time 2013-3-7 上午10:04:21
 * @copyright mAPPn
 */

public class VerifyTokenDemo {
	public static void main(String[] args) {
		StringBuffer request = new StringBuffer();
		request.append("token=iouaw7c5ss5p9oltmv5etsyp8wxbqaqd");
		String urlStr = "http://api.gfan.com/uc1/common/verify_token";
		JifengUtil.doRequest(request.toString(), urlStr);
	}
	
}
	
	 