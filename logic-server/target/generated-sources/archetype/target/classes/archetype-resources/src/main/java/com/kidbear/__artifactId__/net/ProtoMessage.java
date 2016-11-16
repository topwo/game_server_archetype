#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package com.kidbear.${artifactId}.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 消息构造类
 */
public class ProtoMessage implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -3460913241121151489L;
	private Short typeid;
	private Long userid;
	/**
	 * The Data.
	 */
	public JSONObject data;

	/**
	 * Instantiates a new Proto message.
	 */
	public ProtoMessage() {

	}

	/**
	 * Instantiates a new Proto message.
	 *
	 * @param typeid the typeid
	 * @param data   the data
	 */
	public <T> ProtoMessage(Short typeid, T data) {
		this.typeid = typeid;
		this.setData(data);
	}

	/**
	 * Instantiates a new Proto message.
	 *
	 * @param data the data
	 */
	public <T> ProtoMessage(T data) {
		this.setData(data);
	}

	/**
	 * Gets resp.
	 *
	 * @param msg  the msg
	 * @param code the code
	 * @return the resp
	 */
	public static ProtoMessage getResp(String msg, int code) {
		JSONObject ret = new JSONObject();
		ret.put("code", code);
		if (msg != null) {
			ret.put("err", msg);
		}
		return new ProtoMessage(ret);
	}

	/**
	 * Gets success resp.
	 *
	 * @return the success resp
	 */
	public static ProtoMessage getSuccessResp() {
		return getResp(null, ResultCode.SUCCESS);
	}

	/**
	 * Gets empty resp.
	 *
	 * @return the empty resp
	 */
	public static ProtoMessage getEmptyResp() {
		return new ProtoMessage();
	}

	/**
	 * Gets error resp.
	 *
	 * @param msg the msg
	 * @return the error resp
	 */
	public static ProtoMessage getErrorResp(String msg) {
		return getResp(msg, ResultCode.COMMON_ERR);
	}

	/**
	 * Gets error resp.
	 *
	 * @param id the id
	 * @return the error resp
	 */
	public static ProtoMessage getErrorResp(short id) {
		return getResp(null, ResultCode.COMMON_ERR);
	}

	/**
	 * Gets error resp.
	 *
	 * @param code the code
	 * @return the error resp
	 */
	public static ProtoMessage getErrorResp(int code) {
		return getResp(null, code);
	}

	/**
	 * Gets error resp.
	 *
	 * @param code the code
	 * @param msg  the msg
	 * @return the error resp
	 */
	public static ProtoMessage getErrorResp(int code, String msg) {
		return getResp(msg, code);
	}

	/**
	 * Gets data.
	 *
	 * @return the data
	 */
	public JSONObject getData() {
		return this.data;
	}

	/**
	 * Sets data.
	 *
	 * @param data the data
	 */
	public void setData(JSONObject data) {
		this.data = data;
	}

	/**
	 * Gets data.
	 *
	 * @param <T> the type parameter
	 * @param t   the t
	 * @return the data
	 */
	public <T> T getData(Class<T> t) {// 转换为对象传递
		return JSON.parseObject(JSON.toJSONString(data), t);
	}

	/**
	 * Sets data.
	 *
	 * @param <T> the type parameter
	 * @param t   the t
	 */
	public <T> void setData(T t) {
		this.data = JSON.parseObject(JSON.toJSONString(t), JSONObject.class);
	}

	/**
	 * Gets typeid.
	 *
	 * @return the typeid
	 */
	public Short getTypeid() {
		return typeid;
	}

	/**
	 * Sets typeid.
	 *
	 * @param typeid the typeid
	 */
	public void setTypeid(Short typeid) {
		this.typeid = typeid;
	}

	/**
	 * Gets userid.
	 *
	 * @return the userid
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * Sets userid.
	 *
	 * @param userid the userid
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}
}
