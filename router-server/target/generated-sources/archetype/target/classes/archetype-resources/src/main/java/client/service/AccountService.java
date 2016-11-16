#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.service;

import java.util.Date;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import ${package}.client.ChannelCode;
import ${package}.client.model.Account;
import ${package}.util.HttpClient;
import ${package}.util.cache.MC;
import ${package}.util.hibernate.HibernateUtil;
import ${package}.util.hibernate.TableIDCreator;

@Service
public class AccountService {
	public static String xyLoginValidateUrl = "http://passport.xyzs.com/checkLogin.php";
	public Logger logger = LoggerFactory.getLogger(AccountService.class);

	public Account getAccount(String username) {
		String name = StringEscapeUtils.escapeSql(username);
		Account account = HibernateUtil.find(Account.class, "where name='"
				+ name + "'");
		return account;
	}

	/**
	 * @Title: loginOrRegist
	 * @Description: 登录或注册
	 * @param username
	 * @param password
	 * @param channel
	 * @return int
	 *         100-登录成功，101-登录密码错误，200-注册成功，201-注册密码6-10位,202-请使用字母或数字,203-用户名不能超过8个字
	 * @throws
	 */
	public int loginOrRegist(String username, String password, int channel) {
		String name = StringEscapeUtils.escapeSql(username);
		String pwd = StringEscapeUtils.escapeSql(password);
		Account account = HibernateUtil.find(Account.class, "where name='"
				+ name + "'");
		if (account != null) {// 登录
			if (HibernateUtil.find(Account.class, "where name='" + name
					+ "' and pwd='" + pwd + "'") == null) {
				return 101;
			}
			account.setLastlogintime(new Date());
			HibernateUtil.save(account);
			return 100;
		} else {// 注册
			if (pwd.length() < 6 || pwd.length() > 10) {
				return 201;
			}
			if (name.length() > 8) {
				return 203;
			}
			for (char c : name.toCharArray()) {
				if (!Character.isDigit(c)
						&& !((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
					return 202;
				}
			}
			Account newAcc = new Account();
			newAcc.setId(TableIDCreator.getTableID(Account.class, 1));
			newAcc.setName(name);
			newAcc.setPwd(pwd);
			newAcc.setChannel(channel);
			newAcc.setCreatetime(new Date());
			newAcc.setLastlogintime(new Date());
			MC.add(newAcc, newAcc.getIdentifier());
			HibernateUtil.insert(newAcc);
			return 200;
		}
	}

	/**
	 * @Title: regist
	 * @Description:
	 * @param username
	 * @param password
	 * @return int 0-注册成功，1-账号已存在，2-密码至少8位
	 * @throws
	 */
	public int regist(String username, String password, int channel) {
		String name = StringEscapeUtils.escapeSql(username);
		String pwd = StringEscapeUtils.escapeSql(password);
		if (HibernateUtil.find(Account.class, "where name='" + name + "'") != null) {
			return 1;
		}
		if (pwd.length() < 8) {
			return 2;
		}
		Account account = new Account();
		account.setId(TableIDCreator.getTableID(Account.class, 1));
		account.setName(name);
		account.setPwd(pwd);
		account.setChannel(channel);
		account.setCreatetime(new Date());
		MC.add(account, account.getIdentifier());
		HibernateUtil.insert(account);
		return 0;
	}

	/**
	 * @Title: login
	 * @Description: 登录
	 * @param username
	 * @param password
	 * @return int 0-成功，1-账号不存在，2-密码不正确
	 * @throws
	 */
	public int login(String username, String password, int channel) {
		String name = StringEscapeUtils.escapeSql(username);
		String pwd = StringEscapeUtils.escapeSql(password);
		if (HibernateUtil.find(Account.class, "where name='" + name + "'") == null) {
			return 1;
		}
		if (HibernateUtil.find(Account.class, "where name='" + name
				+ "' and pwd='" + pwd + "'") == null) {
			return 2;
		}
		return 0;
	}

	/**
	 * @Title: oneKeyLogin
	 * @Description: 一键登录
	 * @return int
	 * @throws
	 */
	public JSONArray oneKeyLogin(int channel) {
		JSONArray ret = new JSONArray();
		Account account = new Account();
		account.setId(TableIDCreator.getTableID(Account.class, 1));
		account.setName("Guest" + account.getId());
		// 8位随机密码
		StringBuffer pwd = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			int randomNum = (int) Math.ceil(Math.random() * 9);
			pwd.append(randomNum);
		}
		account.setPwd(pwd.toString());
		account.setChannel(channel);
		account.setCreatetime(new Date());
		MC.add(account, account.getIdentifier());
		HibernateUtil.insert(account);
		ret.add(account.getName());
		ret.add(account.getPwd());
		return ret;
	}

	public JSONArray xyLoginHandle(String uid, String appid, String token) {
		JSONArray ret = new JSONArray();
		JSONObject validateRet = JSONObject.parseObject(HttpClient.post(
				xyLoginValidateUrl, "uid=" + uid + "&appid=" + appid
						+ "&token=" + token + ""));
		int code = validateRet.getIntValue("ret");
		ret.add(code == 0 ? true : false);
		if (code != 0) {
			logger.error("uid:{}登录失败,原因:{}", uid,
					validateRet.getString("error"));
			ret.add(validateRet.getString("error"));
			return ret;
		}
		// 登录成功
		Account account = HibernateUtil.find(Account.class, "where name='"
				+ uid + "'");
		if (account != null) {// 登录
			account.setLastlogintime(new Date());
			HibernateUtil.save(account);
		} else {// 注册
			account = new Account();
			account.setId(TableIDCreator.getTableID(Account.class, 1));
			account.setName(uid);
			// account.setPwd("123456");
			account.setChannel(ChannelCode.IOS_XY);
			account.setCreatetime(new Date());
			account.setLastlogintime(new Date());
			MC.add(account, account.getIdentifier());
			HibernateUtil.insert(account);
		}
		ret.add(account);
		return ret;
	}

	public Account sdkLoginHandle(String uid, int channel) {
		uid = StringEscapeUtils.escapeSql(uid);
		Account account = HibernateUtil.find(Account.class, "where name='"
				+ uid + "'");
		if (account == null) {// 登录或注册
			Account newAcc = new Account();
			newAcc.setId(TableIDCreator.getTableID(Account.class, 1));
			newAcc.setName(uid);
			// newAcc.setPwd("123456");
			newAcc.setChannel(channel);
			newAcc.setCreatetime(new Date());
			newAcc.setLastlogintime(new Date());
			MC.add(newAcc, newAcc.getIdentifier());
			HibernateUtil.insert(newAcc);
			account = newAcc;
		} else {
			account.setLastlogintime(new Date());
			HibernateUtil.save(account);
		}
		return account;
	}
}
