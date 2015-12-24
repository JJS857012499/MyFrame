package com.zengfull.myfragme.model;


import com.zengfull.myfragme.util.FuncUtil;

import java.io.Serializable;

public class CustomerModel implements Serializable{
    public String mobile;
    public String name;
    public String pwd;
    public String serverpwd;
    public String loginType;
    public String cusName;
    public String sessionId = "";
    public String version = "";
    
    public boolean isUserNull(){
    	return FuncUtil.isEmpty(loginType)||FuncUtil.isEmpty(mobile)||FuncUtil.isEmpty(name)
    			||FuncUtil.isEmpty(name)||FuncUtil.isEmpty(sessionId)||FuncUtil.isEmpty(version);
    }
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getServerpwd() {
		return serverpwd;
	}
	public void setServerpwd(String serverpwd) {
		this.serverpwd = serverpwd;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "CustomerModel [mobile=" + mobile + ", name=" + name + ", pwd="
				+ pwd + ", serverpwd=" + serverpwd + ", loginType=" + loginType
				+ ", cusName=" + cusName + ", sessionId=" + sessionId
				+ ", version=" + version + "]";
	}
}
