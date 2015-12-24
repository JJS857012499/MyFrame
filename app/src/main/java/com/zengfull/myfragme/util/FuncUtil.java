package com.zengfull.myfragme.util;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuncUtil {
	private static DecimalFormat df=new DecimalFormat("#0.00"); 
	final public static SimpleDateFormat Y4M2D2=new SimpleDateFormat("yyyyMMdd");
	final public static SimpleDateFormat Y4_M2_D2=new SimpleDateFormat("yyyy-MM-dd");
	final public static SimpleDateFormat Y2M2D2=new SimpleDateFormat("yyMMdd");
	final public static SimpleDateFormat M2D2=new SimpleDateFormat("MMdd");
	final public static SimpleDateFormat DTREAD=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	final public static SimpleDateFormat YMDHMS=new SimpleDateFormat("yyyyMMddHHmmss");
	final public static SimpleDateFormat YMDHMSL=new SimpleDateFormat("HHmmssSSS");
	final public static SimpleDateFormat HHMMSS=new SimpleDateFormat("HHmmss");
	final public static SimpleDateFormat Y2M2D2HMSL=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	final public static SimpleDateFormat Y2M2D2HMSS=new SimpleDateFormat("yyyyMMddHHmmssSS");
	final public static SimpleDateFormat MMSS=new SimpleDateFormat("mmss");
	final public static long DAY_MILL=(24*60*60*1000);
	
	/**
	 * 判断是否邮箱地址
	 * @author Vinci
	 * @date 2013-2-21 下午02:53:33 
	 * @modifylog   
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email){   
	    Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");   
	    Matcher m = p.matcher(email);   
	    return m.find();   
	}  

	
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:31:45   
	 * @modifylog:
	 */
	public static boolean isNum(String str) {
		boolean flag = false;
		Pattern pat = Pattern.compile("^[0-9]*$");
		Matcher mat = null;
		mat = pat.matcher(str);
		flag = mat.matches();
		return flag;
	}
	
	/**
	 * 判断是否为数字，包括小数
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		if(isEmpty(str))
			return false;
		boolean flag = false;
		Pattern pat = Pattern.compile("^[0-9|.]+$");
		Matcher mat = null;
		mat = pat.matcher(str);
		flag = mat.matches();
		return flag;
	}
	
	/**
	 * 分 转 元
	 * @param num
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:34:29   
	 * @modifylog:
	 */
	public static BigDecimal ftoy(BigDecimal num){
		return num.divide(new BigDecimal(100), 2, BigDecimal.ROUND_CEILING);
	}
	
	/**
	 * 分 转 元
	 * @param num
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:35:46   
	 * @modifylog:
	 */
	public static BigDecimal ftoy(long num){
		return ftoy(new BigDecimal(num));
	}
	
	/**
	 * 分 转 元
	 * @param num
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:35:55   
	 * @modifylog:
	 */
	public static BigDecimal ftoy(String num){
		return ftoy(new BigDecimal(num));
	}
	
	/**
	 * 元 转 分
	 * @param y
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:36:08   
	 * @modifylog:
	 */
	public static BigDecimal ytof(String y){
		if(y.charAt(0) == '.') {
			y = '0' + y;
		}
		String[] ps = y.split("\\.");
		long ttp = Long.parseLong(ps[0]);
		long r = ttp*100;
		if (ps.length == 2){
			if (ps[1].length() == 1)
				r += Long.parseLong(ps[1])*10;
			else if (ps[1].length() == 2)
				r += Long.parseLong(ps[1]);
			else {
				r += Long.parseLong(ps[1].substring(0, 2));
				if (ps[1].charAt(2) > '4')
					r++;
			}
		}
		return new BigDecimal(r);
	}	
	
	/**
	 * 元 转 分
	 * @param y
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:36:24   
	 * @modifylog:
	 */
	public static BigDecimal ytof(double y){
		return ytof(String.valueOf(y));
	}
	
	/**
	 * 元 转 分
	 * @param y
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:36:24   
	 * @modifylog:
	 */
	public static BigDecimal ytof(BigDecimal y){
		return ytof(y.toString());
	}
	
	/**
	 * 格式化数字0.00
	 * @param number
	 * @return
	 */
	public static String formatNumber(BigDecimal number){
		return formatNumber(number.doubleValue());
	}
	
	/**
	 * 格式化数字0.00
	 * @param number
	 * @return
	 */
	public static String formatNumber(double number){
		DecimalFormat df1=(DecimalFormat) DecimalFormat.getInstance();
		df1.applyPattern("0.00");
		return df1.format(number);
	}
	
	/**
	 * 格式化数字，默认是#,##0.00
	 * @param number
	 * @return
	 */
	public static String formatNumber1(BigDecimal number,String pattern){
		return formatNumber1(number.doubleValue(),pattern);
	}
	
	/**
	 * 格式化数字，默认是#,##0.00
	 * @param number
	 * @return
	 */
	public static String formatNumber1(double number,String pattern){
		DecimalFormat df1=(DecimalFormat) DecimalFormat.getInstance();
		if(isEmpty(pattern)){
			pattern = "#,##0.00";
		}
		df1.applyPattern(pattern);
		return df1.format(number);
	}
	
	public static BigDecimal parseString(String source,String pattern) throws ParseException{
		DecimalFormat df1=(DecimalFormat) DecimalFormat.getInstance();
		if(isEmpty(pattern)){
			pattern = "#,##0.00";
		}
		df1.applyPattern(pattern);
		return new BigDecimal(df1.parse(source).doubleValue());
	}
	
	/**
	 * 四舍五入处理
	 * @param pos 保留小数位
	 * @param bd
	 * @return BigDecimal
	 */
	public static BigDecimal setScale(int pos,BigDecimal bd){
		if(bd==null)return null;
		return bd.setScale(pos, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 格式化金额
	 */
	public static String fmtMoney(Object obj)
	{
		try
		{
			Double d=Double.valueOf(obj!=null ? obj.toString() : "0.00");
			return df.format(d);
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return df.format(0.00);
		}
	}
	public static BigDecimal toDecimal(String str, String divide) {
		if (str == null || str.trim().length()==0) {
			str = "0";
		}
		BigDecimal bdRnt = new BigDecimal(str).divide(new BigDecimal(divide));
		return bdRnt;
	}
	
	public static BigDecimal toDecimalMut(String str, String muti) {
		if (str == null || str.trim().length()==0) {
			str = "0";
		}
		BigDecimal bdRnt = new BigDecimal(str).multiply(new BigDecimal(muti));
		return bdRnt;
	}	
	//百分格式化
	public static String Round(BigDecimal num)
	{
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(num);
		return str;
	}

	public static String RoundAndScale(BigDecimal num)
	{
		num = num.divide(new BigDecimal("100.00"));
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(num);
		return str;
	}

	public static String RoundAndScalePer(BigDecimal num)
	{
		DecimalFormat df = new DecimalFormat("0.00");
		String str = df.format(num.multiply(new BigDecimal("100.00")));
		return str;
	}
	
	/**
	 * 获取时间偏移后时间量
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getTimeOffset(Date date,long offset){
		if(date==null)
			return null;
		return new Date(date.getTime()+offset);
	}
	
	/**
	 * 字符串分割
	 * @param line
	 * @param split_char
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:28:45   
	 * @modifylog:
	 */
	public static String[] split(String line,char split_char) {
		List segments = new ArrayList();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if (c == split_char) {
				segments.add(dropNull(sb.toString()));
				sb = new StringBuffer();
			} else {
				sb.append(c);
			}
		}
		segments.add(sb.toString());
		return (String[]) segments.toArray(new String[0]);
	}
	
	/**
	 * 字符串 消除空格
	 * @param input
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:29:06   
	 * @modifylog:
	 */
	public static String dropNull(String input) {
		if (input == null) {
			input = "";
		}
		return input.trim();
	}
	
	public static boolean isEmpty(EditText et){
		if(et==null||TextUtils.isEmpty(et.getText()))
			return true;
		return false;
	}
	
	public static boolean isEmpty(TextView tv){
		if(tv==null||TextUtils.isEmpty(tv.getText()))
			return true;
		return false;
	}
	
	/**
	 * 字符串是否为空
	 * @param s
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:43:21   
	 * @modifylog:
	 */
	public static boolean isEmpty(String s){
		if(s==null||"".equals(s.trim()))
			return true;
		return false;
	}
	
	/**
	 * 是否为空数组
	 * @param val
	 * @return
	 */
	public static boolean isEmpty(Object[] val) {
		if (val == null || val.length == 0) {
			return true;
		}
		for (int i = 0; i < val.length; i++) {
			if (val[i] != null && !"".equals(val[i])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 是否为空List
	 * @param ls
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:46:40   
	 * @modifylog:
	 */
	public static boolean isEmpty(List ls){
		if(ls==null||ls.size()==0)return true;
		return false;
	}
	//Map排序
	public static SortedMap<String, String> mapSortByKey(Map<String, String> unsort_map){
		TreeMap<String, String> result = new TreeMap<String, String>();
		Object[] unsort_key = unsort_map.keySet().toArray();
		Arrays.sort(unsort_key);
		for (int i = 0; i < unsort_key.length; i++) {
			result.put(unsort_key[i].toString(), unsort_map.get(unsort_key[i]));
		}
		return result.tailMap(result.firstKey());
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param formatter
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:29:43   
	 * @modifylog:
	 */
	public static String formatTime(Date date,String formatter){
		SimpleDateFormat sdf  = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param formatter
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:29:43   
	 * @modifylog:
	 */
	public static String formatTime(Timestamp date,String formatter){
		if(date==null)
	        return "";
		SimpleDateFormat sdf  = new SimpleDateFormat(formatter);
		Date date_new = new Date(date.getTime());
		return sdf.format(date_new);
	}
	
	/**
	 * 转化时间
	 * @param datestr
	 * @param formatter
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:30:03   
	 * @modifylog:
	 */
	public static Date parseTime(String datestr,String formatter) throws Exception{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(formatter);
			return sdf.parse(datestr);
		}
		catch(Exception ex){
			Exception oe = new Exception("时间解析错误:" + ex.getMessage());
			throw oe;
		}
	}
	
	/**
	 * 转化时间
	 * @param datestr
	 * @param formatter
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:30:03   
	 * @modifylog:
	 */
	public static Timestamp parseStampTime(String datestr,String formatter) throws Exception{
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(formatter);
			return new Timestamp(sdf.parse(datestr).getTime());
		}
		catch(Exception ex){
			Exception oe = new Exception("时间解析错误:" + ex.getMessage());
			throw oe;
		}
	}
	
	/**
	 * 获取当前时间
	 * @return
	 * @author: Vinci
	 * @time: 2012-5-3 下午03:31:08   
	 * @modifylog:
	 */
	public static Timestamp getCurrTimestamp(){
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * 组织insql
	 * @param inParam
	 * @return
	 * @author: vinci
	 * @time: 2012-5-8 下午11:58:26   
	 * @modifylog:
	 */
	public static String buildSqlIn(String[] inParam)
	{
		if(null!=inParam&&inParam.length>0)
		{
			String inSql = "";
			for(int i=0;i<inParam.length;i++)
			{
				inSql += ",'"+inParam[i]+"'";
			}
			return inSql.substring(1);
		}
		return "";
	}
	
	/**
	 * 组织insql
	 * @param inParam
	 * @return
	 * @author: vinci
	 * @time: 2012-5-8 下午11:58:26   
	 * @modifylog:
	 */
	public static String buildSqlIn(List inParam)
	{
		if(null!=inParam&&inParam.size()>0)
		{
			String inSql = "";
			for(int i=0;i<inParam.size();i++)
			{
				inSql += ",'"+inParam.get(i)+"'";
			}
			return inSql.substring(1);
		}
		return "";
	}
	
	/**
	 * 四位随机码
	 * @return
	 */
	public static String getValidatecode(int n) {
		Random random = new Random();
		String sRand = "";
		n = n == 0 ? 4 : n;// default 4
		for (int i = 0; i < n; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}
	
	/**
	 * 根据路径创建一系列的目录
	 * @param path
	 */
	public static void mkDir(String path) {
		File file;
		try {
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
	}	
	
	/***
	 * 检查身份证
	 * */
	public static boolean checkIdcard(String idcard){
		boolean valid = false;
		if(idcard!=null && !"".equals(idcard.trim())){
			if(idcard.length()==18){
				valid = isNum(idcard.substring(0, idcard.length()-1));
				if(valid){
					valid =  isNum(idcard.substring(idcard.length()-1));
					if(!valid){
						if(idcard.endsWith("x") || idcard.endsWith("X")){
							valid = true;
						}
					}
				}
			}else if(idcard.length()==15){
				valid =  isNum(idcard);
			}
		}
		return valid;
	}
	
	
	/**
	 * 检查手机
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		if (isEmpty(mobile)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(1[3|4|5|8|9])\\d{9}$");
		Matcher matcher = pattern.matcher(mobile);
		return matcher.matches();
	}
	
	public static String join(String[] array){
		if(array == null ||array.length==0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(String s: array){
			if(sb.length()>0){
				sb.append(",");
			}
			sb.append(s);
		}
		return sb.toString();
	}
	/**
	 * 判断汉字
	 * comment here
	 * @param strVal
	 * @return
	 */
	public static boolean isChinese(String strVal) {
		int iRnt = 0;
		boolean rs=false;
		for (int i=0; i<strVal.length(); i++) {			
			String strHanzi = strVal.substring(i, i+1);
			byte[] bytes = null;
			try {
				bytes = (strHanzi).getBytes("gbk");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return false;
			}
			if (bytes == null || bytes.length > 2 || bytes.length <= 0
					|| bytes.length == 1) {
				//非汉字
				iRnt++;
			}
			if (bytes.length == 2) { // 汉字
				return true;
			}
		}//for
		rs=false;
		return rs;
	}
	
	public static byte[] getBytesFromIS(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int b = 0;
		// BufferedReader br = new BufferedReader(new InputStreamReader(input,
		//		"GBK"));
		while ((b = is.read()) != -1)
			baos.write(b);

		return baos.toByteArray();
	}
	
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i]<0 ? src[i] + 256 : src[i];
            String hv = Integer.toHexString(v);

            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    
    public static String underscoreName(String name)
	{
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0)
		{
			result.append(name.substring(0, 1).toLowerCase());
			for (int i = 1; i < name.length(); i++)
			{
				String s = name.substring(i, i + 1);
				if (s.equals(s.toUpperCase())&&Character.isLetter(s.charAt(0)))
				{
					result.append("_");
					result.append(s.toLowerCase());
				}
				else
				{
					result.append(s);
				}
			}

		}
		return result.toString();
	}
    
	public static int indexOfIg(String[] strs,String toFind)
	{
		if(strs==null) return -1;
		for(int i=0;i<strs.length;++i)
		{
			if(toFind==null&&strs[i]==null||toFind.equalsIgnoreCase(strs[i])) return i;
		}
		return -1;
	}
	
	/**
	 * 转换日期格式yyyymmdd 为yyyy-MM-dd 方便界面显示
	 * @param source
	 * @return
	 */
	public static String chDateformat(String source){
		if(null == source){
			return null;
		}
		Pattern p = Pattern.compile("^[\\d]{8}$");
		if(p.matcher(source).matches()){
			return source.substring(0,4)+"-"+source.substring(4,6)+"-"+source.substring(6);
		}else{
			return source;
		}
	}
	
	 public static String md5(String str) {
		 	MessageDigest md;
	        try {
	        	md = MessageDigest.getInstance("MD5");
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return str;
	        }
	        md.reset();
	        md.update(str.getBytes());
	        byte[] hash = md.digest();
	        StringBuffer outStrBuf = new StringBuffer(32);
	        for (int i = 0; i < hash.length; i++) {
	            int v = hash[i] & 0xFF;
	            if (v < 16) {
	            	outStrBuf.append('0');
	            }
	            outStrBuf.append(Integer.toString(v, 16).toLowerCase());
	        }
	        return outStrBuf.toString();
	 }
	 
	 /**
	 * 字符填充
	 * @param dir
	 * @param str
	 * @param fillinstr
	 * @param length
	 * @return
	 */
	 public static String formatFillStr(int dir,String str,String fillinstr,int length){
		if(str==null)
			str="";
		String tmp=new String(str);
		byte[] bt = tmp.getBytes();
		if(dir==1){
			for(int i=bt.length;i<length;i++)
				tmp=fillinstr+tmp;
		}
		else if(dir==2){
			for(int i=bt.length;i<length;i++)
				tmp+=fillinstr;
		}
		return tmp;
	 }	
	 
	 public static String formatUrl(String url)
	 {
		 if(!isEmpty(url))
         {
			 url = url.replace("\\", "/");
         	try {
         		String[] urlData = url.split("/");
         		String fileName = urlData[urlData.length-1];
         		String zw = fileName.substring(0, fileName.indexOf("."));
         		return url.replace(zw, URLEncoder.encode(zw,"UTF-8"));
 			} catch (Exception e) {
 			}
         }
		 return "";
	 }
	 
	/**
	 * 去除特殊字符或将所有中文标号替换为英文标号     
	 * @param str
	 * @return
	 * @author: Vinci
	 * @time: 2012-10-6 下午05:03:01   
	 * @return String
	 * @modifylog:
	 */
    public static String stringFilter(String str) {    
        str = str.replaceAll("【", "[").replaceAll("】", "]")    
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号    
        String regEx = "[『』]"; // 清除掉特殊字符    
        Pattern p = Pattern.compile(regEx);    
        Matcher m = p.matcher(str);    
        return m.replaceAll("").trim();    
    }  
    /**   
     * 半角转换为全角   
     *    
     * @param input   
     * @return   
     */   
    public static String ToDBC(String input) {    
        char[] c = input.toCharArray();    
        for (int i = 0; i < c.length; i++) {    
            if (c[i] == 12288) {    
                c[i] = (char) 32;    
                continue;    
            }    
            if (c[i] > 65280 && c[i] < 65375)    
                c[i] = (char) (c[i] - 65248);    
        }    
        return new String(c);    
    }  
    
    public static String byteToString(byte b) {
		byte high, low;
		byte maskHigh = (byte)0xf0;
		byte maskLow = 0x0f;
		high = (byte)((b & maskHigh) >> 4);
		low = (byte)(b & maskLow);
		StringBuffer buf = new StringBuffer();
		buf.append(findHex(high));
		buf.append(findHex(low));
		return buf.toString();
	}
	private static char findHex(byte b) {
		int t = new Byte(b).intValue();
		t = t < 0 ? t + 16 : t;
		if ((0 <= t) &&(t <= 9)) {
			return (char)(t + '0');
		}
		return (char)(t-10+'A');
	}
	
	public static int stringToByte(String in, byte[] b){
		if (b.length < in.length() / 2) {
			return 0;
		}
		int j=0;
		StringBuffer buf = new StringBuffer(2);
		for (int i=0; i<in.length(); i++, j++) {
			buf.insert(0, in.charAt(i));
			buf.insert(1, in.charAt(i+1));
			int t = Integer.parseInt(buf.toString(),16);
			b[j] = (byte)t;
			i++;
			buf.delete(0,2);
		}
		return j;
	}
	/**
	 * 字节转字符串
	 * @author vinci
	 * @date 2013-9-29 下午03:43:54 
	 * @modifylog   
	 * @param data
	 * @return
	 */
	 public static String bytesToString(byte[] data) {
		 String str = "";
		 for(int i=0;i<data.length;i++){
			byte b = data[i];
			str += byteToString(b);
		 }
		 return str;
	}
	 
	 /**
	  * 截取指定长度的字符串
	 * @param text
	 * @param length
	 * @param endWith
	 * @return
	 */
	public static String getPexfStr(String text, int length, String endWith) {        
	        int textLength = text.length();  
	        int byteLength = 0;  
	        StringBuffer returnStr =  new StringBuffer();  
	        for(int i = 0; i<textLength && byteLength < length*2; i++){  
	            String str_i = text.substring(i, i+1);   
	            if(str_i.getBytes().length == 1){//英文  
	                byteLength++;  
	            }else{//中文  
	                byteLength += 2 ;  
	            }  
	            returnStr.append(str_i);  
	        }  
	        try {  
	            if(byteLength<text.getBytes("GBK").length){//getBytes("GBK")每个汉字长2，getBytes("UTF-8")每个汉字长度为3  
	                returnStr.append(endWith);  
	            }  
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	        return returnStr.toString();  
	        
	 }
	
	public static boolean hasNum(String orgStr){
		boolean flag = false;
		Pattern p = Pattern.compile(".*\\d+.*");
		Matcher m = p.matcher(orgStr);
		if (m.matches())
		flag = true;
		return flag;
	}

	public static String getWeekString(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		String[] strs = new String[] { "日", "一", "二", "三", "四", "五", "六" };
		return "星期" + strs[cal.get(Calendar.DAY_OF_WEEK)-1];
	}
	
	
	 public static int copy(Reader input, Writer output) throws IOException {
	        long count = copyLarge(input, output);
	        if (count > Integer.MAX_VALUE) {
	            return -1;
	        }
	        return (int) count;
	    }
	 
	  public static long copyLarge(Reader input, Writer output) throws IOException {
	        char[] buffer = new char[1024 * 4];
	        long count = 0;
	        int n = 0;
	        while (-1 != (n = input.read(buffer))) {
	            output.write(buffer, 0, n);
	            count += n;
	        }
	        return count;
	    }
	  
	  public static String formatYYYYMMDD(String str){
		  if(isEmpty(str)||str.length()!=8){
			  return str;
		  }
		  
		  return str.substring(0,4)+"-"+str.substring(4, 6)+"-"+str.substring(6,8);
	  }
	  
	  public static String formatHHMMSS(String str){
		  if(isEmpty(str)||str.length()!=6){
			  return str;
		  }
		  return str.substring(0,2)+":"+str.substring(2, 4)+":"+str.substring(4,6);
	  }
	  /**
	  0-	磁条卡
	  1-	IC卡
	**/
	  public static String getCardType(String type){
		  if("1".equals(type)){
			  return "IC卡";
		  }
		  else if("0".equals(type)){
			  return "磁条卡";
		  }
		  return "未知";
	  }
	  
	  public static String getPayFlag(String type){
		  if("0".equals(type)){
			  return "未执行快付";
		  }
		  else if("1".equals(type)){
			  return "快付执行成功";
		  }
		  else if("2".equals(type)){
			  return "快付执行失败";
		  }
		  return "未知";
	  }
	  
	  public static String getUploadFlag(String type){
		  if("0".equals(type)){
			  return "未上传";
		  }
		  else if("1".equals(type)){
			  return "已上传";
		  }
		  return "未知";
	  }
}
