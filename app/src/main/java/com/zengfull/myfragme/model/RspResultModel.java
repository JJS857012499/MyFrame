package com.zengfull.myfragme.model;

import java.io.Serializable;

public class RspResultModel<T> implements Serializable{
	private static final long serialVersionUID = 19394L;
	
	public String retcode;
	public String retmsg;
	public T source;



}
