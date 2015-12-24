package com.zengfull.myfragme.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class IdentityParamReq implements KvmSerializable {
    public byte[] uniquecode;  
    public byte[] username; 
    public byte[] userpasswd; 
    public byte[] userphone; 
    public String usertype;  
   
    

	@Override
	public Object getProperty(int arg0) {
		// TODO Auto-generated method stub
		switch (arg0) {  
	        case 0:  
	            return uniquecode;  
	        case 1:  
	            return username;  
	        case 2:  
	            return userpasswd;  
	        case 3:  
	            return userphone; 
	        case 4:  
	            return usertype; 
	        default:  
	            break;  
	    }  
	    return null;  
	}

	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 5;  
	}

	@Override
	public void getPropertyInfo(int arg0, Hashtable arg1, PropertyInfo arg2) {
		// TODO Auto-generated method stub
		 switch (arg0) {  
	         case 0:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "uniquecode";  
	             break;  
	         case 1:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "username";  
	             break;  
	         case 2:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "userpasswd";  
	             break;  
	         case 3:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "userphone";  
	             break; 
	         case 4:  
	             arg2.type = PropertyInfo.STRING_CLASS;
	             arg2.name = "usertype";  
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
				uniquecode =  (byte[])arg1;  
	            break;  
	        case 1:  
	        	username = (byte[])arg1;  
	            break;  
	        case 2:  
	        	userpasswd =  (byte[])arg1;  
	            break;  
	        case 3:  
	        	userphone =  (byte[])arg1;  
	            break;  
	        case 4:  
	        	usertype =  arg1.toString();  
	            break;  
	        
	        default:  
	            break;  
	    }  
	}

}
