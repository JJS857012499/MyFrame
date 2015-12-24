package com.zengfull.myfragme.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class LoginParamReq implements KvmSerializable {
    public byte[] clientPublicKey;  
    public byte[] loginname; 
    public byte[] loginpasswd; 
    public String logintype;  

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {  
	        case 0:  
	            return clientPublicKey;  
	        case 1:  
	            return loginname;  
	        case 2:  
	            return loginpasswd;  
	        case 3:  
	            return logintype; 
	        default:  
	            break;  
	    }  
	    return null;  
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 4;  
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		 switch (arg0) {  
	         case 0:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "clientPublicKey";  
	             break;  
	         case 1:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "loginname";  
	             break;  
	         case 2:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "loginpasswd";  
	             break;  
	         case 3:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "logintype";  
	             break; 
	         default:  
	             break;  
	     }  
		
	}

	@Override
	public void setProperty(int arg0, Object arg1) {
		// TODO Auto-generated method stub
		switch (arg0) {  
			case 0:  
				clientPublicKey =  (byte[])arg1;  
	            break;  
	        case 1:  
	        	loginname = (byte[])arg1;  
	            break;  
	        case 2:  
	        	loginpasswd =  (byte[])arg1;  
	            break;  
	        case 3:  
	        	logintype =  arg1.toString();  
	            break;  
	        
	        default:  
	            break;  
	    }  
	}

}
