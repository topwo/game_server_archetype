#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.client.${artifactId};

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ${package}.util.cache.MCSupport;

@Entity
@Table(name = "Pay")
public class Pay implements MCSupport {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -8138454450127124448L;
	@Id
	private long billno;// 厂商订单号（原样返回给游戏服）
	private String orderid;// 平台订单号
	private String account;// 第三方账号
	private long userid;// 游戏服账号
	private double amount;// 金额
	private long appid;// 应用 ID
	private int zone;// 分区 id
	private String channel;// 渠道标识
	private String gamename;// 游戏名
	private int goodType;// 物品类型
	private Date ${artifactId}Date;// 充值时间
	private int isFinished;// 是否充值完成0-未验证，1-支付失败，2-支付成功，发货失败，3-支付成功，发货成功

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getZone() {
		return zone;
	}

	public void setZone(int zone) {
		this.zone = zone;
	}

	public long getBillno() {
		return billno;
	}

	public void setBillno(long billno) {
		this.billno = billno;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

	public Date getPayDate() {
		return ${artifactId}Date;
	}

	public void setPayDate(Date ${artifactId}Date) {
		this.${artifactId}Date = ${artifactId}Date;
	}

	public int getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}

	public long getAppid() {
		return appid;
	}

	public void setAppid(long appid) {
		this.appid = appid;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	@Override
	public long getIdentifier() {
		return billno;
	}

	public int getGoodType() {
		return goodType;
	}

	public void setGoodType(int goodType) {
		this.goodType = goodType;
	}
}
