#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.sdk.xiaomi;

import java.util.Arrays;
import java.util.Map;

public class ParamUtil {
	/**
	 * 获取得到排序好的查询字符串
	 * @param params 请求参数
	 * @param isContainSignature 是否包含signature参数
	 * @return
	 */
	public static  String getSortQueryString(Map<String,String> params) throws Exception {
		
		Object[] keys = params.keySet().toArray();
		Arrays.sort(keys);
		StringBuffer sb = new StringBuffer();
		for(Object key : keys){
			sb.append(String.valueOf(key)).append("=").append(params.get(String.valueOf(key))).append("&");
		}
		
		String text = sb.toString();
		if(text.endsWith("&")) {
			text=text.substring(0,text.length()-1);
		}
		return text;
	}
}
