package com.celiosato.exames.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	private Long timeSatmp;

	public StandardError(Integer status, String msg, Long timeSatmp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timeSatmp = timeSatmp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeSatmp() {
		return timeSatmp;
	}

	public void setTimeSatmp(Long timeSatmp) {
		this.timeSatmp = timeSatmp;
	}

}

