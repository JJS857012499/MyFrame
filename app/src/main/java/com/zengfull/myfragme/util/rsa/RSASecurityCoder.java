package com.zengfull.myfragme.util.rsa;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by asus on 2015/12/1.
 */
public class RSASecurityCoder {
    private static final String Algorithm = "RSA";
    private static final int Key_Size = 1024;
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private final byte[] privateKey;
    private final byte[] publicKey;

    public RSASecurityCoder()
            throws Exception {
        KeyPairGenerator localKeyPairGenerator = KeyPairGenerator.getInstance("RSA");
        localKeyPairGenerator.initialize(Key_Size);
        KeyPair localKeyPair = localKeyPairGenerator.generateKeyPair();
        this.publicKey = ((RSAPublicKey) localKeyPair.getPublic()).getEncoded();
        this.privateKey = ((RSAPrivateKey) localKeyPair.getPrivate()).getEncoded();
    }

    /**
     * 解密
     *
     * @param encryptedData 密文
     * @return
     * @throws Exception
     */
    public String getDecryptString(byte[] encryptedData)
            throws Exception {
        PKCS8EncodedKeySpec localPKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(this.privateKey);
        PrivateKey localPrivateKey = KeyFactory.getInstance(Algorithm).generatePrivate(localPKCS8EncodedKeySpec);
        Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        localCipher.init(Cipher.DECRYPT_MODE, localPrivateKey);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = localCipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = localCipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 加密
     *
     * @param dataStr  被加密的字符串
     * @param keyBytes 加密的秘钥
     * @return
     * @throws Exception
     */
    public byte[] getEncryptArray(String dataStr, byte[] keyBytes)
            throws Exception {
        X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(localX509EncodedKeySpec);
        Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        localCipher.init(Cipher.ENCRYPT_MODE, localPublicKey);
        byte[] data = dataStr.getBytes("UTF-8");
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = localCipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = localCipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;

        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    public byte[] getPublicKey() {
        return this.publicKey;
    }
}
