#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.kidbear.${artifactId}.util.mongo;

import java.util.Map;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class TestBean {
	@Id
	private Long _id;
	private String msg;
	private Double score;
	private SubBean subBean;
	private Map<Long, SubBean> subBeans;

	public Long getId() {
		return _id;
	}

	public void setId(Long _id) {
		this._id = _id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public SubBean getSubBean() {
		return subBean;
	}

	public void setSubBean(SubBean subBean) {
		this.subBean = subBean;
	}

	public Map<Long, SubBean> getSubBeans() {
		return subBeans;
	}

	public void setSubBeans(Map<Long, SubBean> subBeans) {
		this.subBeans = subBeans;
	}

}
