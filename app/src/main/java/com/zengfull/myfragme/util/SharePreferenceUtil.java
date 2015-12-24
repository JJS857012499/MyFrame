package com.zengfull.myfragme.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 常用的sp操作
 * 将一些基础信息写入手机存储
 * @author Vinci
 * @date 2013-2-27 下午04:54:35 
 * @modifylog
 */
public class SharePreferenceUtil {
	public final static String SPKEY_KEY = "SPKEY_KEY";
	public final static String SPKEY_NAME = "SPKEY_NAME";
	public final static String SPKEY_PHONE = "SPKEY_PHONE";
	public final static String SPKEY_CUSNAME = "SPKEY_CUSNAME";
	public final static String SPKEY_LOGINTYPE = "SPKEY_LOGINTYPE";
	public final static String SPKEY_SERVERPWD = "SPKEY_SERVERPWD";
	public final static String SPKEY_USERPWD = "SPKEY_USERPWD";
	public final static String SPKEY_NOTICE = "SPKEY_NOTICE";
	public final static String SPKEY_SAVEPWD = "SPKEY_SAVEPWD";
	public final static String SPKEY_SAVENAME = "SPKEY_SAVENAME";
	public final static String SPKEY_ISSAVEPWD = "SPKEY_ISSAVEPWD";
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public SharePreferenceUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
		editor = sp.edit();
	}
	
	public void setParam(String key,String value)
	{
		editor.putString(key, value);
		editor.commit();
	}
	
	public String getValue(String key)
	{
		return sp.getString(key, "");
	}

	public SharedPreferences getSp() {
		return sp;
	}

	public void setSp(SharedPreferences sp) {
		this.sp = sp;
	}

	public SharedPreferences.Editor getEditor() {
		return editor;
	}

	public void setEditor(SharedPreferences.Editor editor) {
		this.editor = editor;
	}
}
