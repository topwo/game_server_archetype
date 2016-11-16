#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.sdk.xiaomi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class XiaoMiService {

	private static String verifySessionUrl = "http://mis.migc.xiaomi.com/api/biz/service/verifySession.do";

	private static String queryOrderUrl = "http://mis.migc.xiaomi.com/api/biz/service/queryOrder.do";

	/**
	 * 验证用户登录信息
	 * 
	 * @param userId
	 *            用户的登录ID，从SDK客户端获取
	 * @param session
	 *            用户登录的session， 从SDK客户端获取
	 */
	public static boolean verify(String userId, String session) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", String.valueOf(Constant.APP_ID));
		params.put("uid", userId);
		params.put("session", session);
		try {
			String requestUrl = getRequestUrl(params, verifySessionUrl);
			String result = HttpUtil.doGet(requestUrl);
			JSONObject obj = JSON.parseObject(result);
			int errcode = obj.getIntValue("errcode");
			if (Constant.CODE_SUCCESS == errcode) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 订单查询接口
	 * 
	 * @param userId
	 *            用户ID
	 * @param cpOrderId
	 *            开发者产生的订单号
	 * @return 订单数据map
	 */
	public static Map<String, String> getOrder(String userId, String cpOrderId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("appId", String.valueOf(Constant.APP_ID));
		params.put("uid", userId);
		params.put("cpOrderId", cpOrderId);

		Map<String, String> orderInfo = new HashMap<String, String>();
		try {
			String requestUrl = getRequestUrl(params, queryOrderUrl);
			String result = HttpUtil.doGet(requestUrl);
			JSONObject obj = JSON.parseObject(result);
			orderInfo.put("appId", String.valueOf(obj.getLongValue("appId")));
			orderInfo.put("cpOrderId", obj.getString("cpOrderId"));
			orderInfo.put("cpUserInfo", obj.getString("cpUserInfo"));
			orderInfo.put("uid", String.valueOf(obj.getLongValue("uid")));
			orderInfo.put("orderId", obj.getString("orderId"));
			orderInfo.put("orderStatus", obj.getString("orderStatus"));
			orderInfo.put("${artifactId}Fee", String.valueOf(obj.getLongValue("${artifactId}Fee")));
			orderInfo.put("productCode", obj.getString("productCode"));
			orderInfo.put("productName", obj.getString("productName"));
			orderInfo.put("productCount",
					String.valueOf(obj.getIntValue("productCount")));
			orderInfo.put("${artifactId}Time", obj.getString("${artifactId}Time"));
			orderInfo
					.put("orderConsumeType", obj.getString("orderConsumeType"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderInfo;
	}

	/**
	 * 订单回调处理方法，用于接收平台发送的订单信息
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param response
	 *            HttpServletResponse对象
	 * @throws IOException
	 */
	public static void notify(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String queryString = request.getQueryString();
		int resultCode = Constant.CODE_SUCCESS;
		try {
			Map<String, String> signParams = getSignParams(queryString);
			String tmpSign = getSign(signParams);
			String sign = request.getParameter("signature");

			if (tmpSign.equals(sign)) {
				signParams.put("signature", sign);
				processOrder(signParams);
			} else {
				resultCode = Constant.CODE_SIGN_ERROR;
			}
		} catch (Exception e) {
			resultCode = Constant.CODE_SYS_ERROR;
			e.printStackTrace();
		}

		JSONObject obj = new JSONObject();
		obj.put("errcode", resultCode);

		response.setContentType("application/json");
		response.getOutputStream().write(obj.toJSONString().getBytes());
	}

	private static String getRequestUrl(Map<String, String> params,
			String baseUrl) throws Exception {
		String signString = ParamUtil.getSortQueryString(params);
		String signature = getSign(params);
		return baseUrl + "?" + signString + "&signature=" + signature;
	}

	public static Map<String, String> getSignParams(String queryString)
			throws UnsupportedEncodingException {

		Map<String, String> result = new HashMap<String, String>();
		String[] params = queryString.split("&");
		for (String param : params) {
			String[] tmp = param.split("=");
			String key = tmp[0];
			if (!"signature".equals(key)) {
				result.put(key, URLDecoder.decode(tmp[1], "UTF-8"));
			}
		}
		return result;
	}

	public static String getSign(Map<String, String> params) throws Exception {
		String signString = ParamUtil.getSortQueryString(params);
		return HmacSHA1Encryption.hmacSHA1Encrypt(signString,
				Constant.SECRET_KEY);
	}

	private static void processOrder(Map<String, String> order) {
		// TODO: 订单处理逻辑由开发者自己处理，这里未实现

	}
}
