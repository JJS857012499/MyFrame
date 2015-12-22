package com.zengfull.myfragme.util.rsa;

import android.util.Base64;

/**
 * Created by asus on 2015/12/1.
 */
public class SecurityUtil {
    private static RSASecurityCoder coder;

    static {
        try {
            coder = new RSASecurityCoder();
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    public static RSASecurityCoder getCoder() {
        return coder;
    }

    /**
     * 加密
     * @param paramString
     * @param paramArrayOfByte
     * @return
     */
    public static byte[] getRasCoder(String paramString, byte[] paramArrayOfByte) {
        try {
            byte[] arrayOfByte = coder.getEncryptArray(paramString, paramArrayOfByte);
            return arrayOfByte;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    /**
     * 解密 （Base64解码）
     * @param paramString
     * @return
     */
    public static String getRasDecryptString(String paramString) {
        try {
            String str = coder.getDecryptString(Base64.decode(paramString, Base64.DEFAULT));
            return str;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

}
