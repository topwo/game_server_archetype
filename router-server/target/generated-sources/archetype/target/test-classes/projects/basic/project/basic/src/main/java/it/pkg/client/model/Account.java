package it.pkg.client.model;

import it.pkg.util.cache.MCSupport;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Account", indexes = { @Index(columnList = "channel"),
		@Index(columnList = "name"), @Index(columnList = "createtime") })
@Entity
public class Account implements MCSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7416561688915379777L;
	@Id
	private long id;
	private String name;
	private String pwd;
	@Column(columnDefinition = "INT default 0")
	private int channel;
	@Column(columnDefinition = "INT default 0")
	private long lastServer;
	private Date createtime;
	private Date lastlogintime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getIdentifier() {
		return id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getLastServer() {
		return lastServer;
	}

	public void setLastServer(long lastServer) {
		this.lastServer = lastServer;
	}

	public Date getLastlogintime() {
		return lastlogintime;
	}

	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
}
