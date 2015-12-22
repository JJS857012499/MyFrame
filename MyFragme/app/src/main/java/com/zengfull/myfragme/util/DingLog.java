package com.zengfull.myfragme.util;

import android.util.Log;

/**
 * 自定义调试时的日志打印
 * @author Vinci
 * @date 2013-8-21 下午04:55:03
 * @modifylog
 */
public class DingLog {
	/**是否打印log 开发时为true 发布时改为false**/
	public static boolean DEBUG = false;
	private Class ctf;

	public DingLog(Class clazz)
	{
		this.ctf = clazz;
	}
	public void info(String msg)
	{
		if(DEBUG)
		{
			//Log.i(this.ctf.getName(), msg);
			Log.i("mytag", msg);
			//MyApp.getInstance().getFa().writeFileSdcard(FileAccess.logpath,new Timestamp(System.currentTimeMillis()).toLocaleString()+":"+msg+"\n");
		}
	}
	public void error(String msg)
	{
		if(DEBUG)
		{
			Log.e(this.ctf.getName(), msg);
		}
	}
	public void error(String msg,Exception e)
	{
		if(DEBUG)
		{
			e.printStackTrace();
			Log.e(this.ctf.getName(), msg);
		}
	}
	public void debug(String msg)
	{
		if(DEBUG)
		{
			Log.d(this.ctf.getName(), msg);
		}
	}
	public static void info(String tag,String msg)
	{
		if(DEBUG)
		{
			Log.d(tag, msg);
		}
	}
	public Class getCtf() {
		return ctf;
	}
	public void setCtf(Class ctf) {
		this.ctf = ctf;
	}
}
