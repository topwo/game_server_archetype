
package httptest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kidbear.logic.net.ProtoIds;
import com.kidbear.logic.util.HttpClient;

/**
 * Http测试类
 */
public class HttpClientTest {

    /**
     * 访问url
     */
    public static String url = "http://127.0.0.1:9191";

    /**
     * 测试方法
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        HttpClientTest test = new HttpClientTest();
        JSONObject obj = new JSONObject();
        obj.put("userid", 2002);
        obj.put("typeid", ProtoIds.TEST);
        JSONObject dataJson = new JSONObject();
        obj.put("data", dataJson);
        String data = "data=" + JSON.toJSONString(obj);
        test.sendRequest(data, 2);
    }

    /**
     * 发送HTTP请求并打印结果及消耗时间
     *
     * @param params the params
     * @param method the method
     */
    public void sendRequest(String params, int method) {
        long start = System.currentTimeMillis();
        String ret = null;
        switch (method) {
            case 1:// get
                ret = HttpClient.get(url + "?" + params
                        + "");
                break;
            case 2:// post
                ret = HttpClient.post(url, params
                        + "");
                break;
        }
        System.out.println(ret + "-------->耗时：" + (System.currentTimeMillis() - start));
    }
}
