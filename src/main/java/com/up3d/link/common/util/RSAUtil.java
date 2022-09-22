package com.up3d.link.common.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-01  15:08
 * @Description: RSA非对称加密实现
 */
public class RSAUtil {

    /**
     * 保存公钥和私钥到文件中
     * @param algorithm 加密算法
     * @param publicPath 公钥路径
     * @param privatePath 私钥路径
     */
    private static void generatorKey2File(String algorithm, String publicPath, String privatePath) throws Exception {
        // 创建密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        // 生成私钥字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 生成公钥的字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // s使用Base64进行编码
        String privateKeyEncodedString = Base64.encode(privateKeyEncoded);
        String publicKeyEncodedString = Base64.encode(publicKeyEncoded);
        System.out.println(privateKeyEncodedString);
        System.out.println(publicKeyEncodedString);
        // 把公钥和私钥保存到文件中
        FileUtils.writeStringToFile(new File(publicPath),publicKeyEncodedString,Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(privatePath),privateKeyEncodedString,Charset.forName("UTF-8"));
    }

    /**
     * RSA 加密
     * @param algorithm 加密算法
     * @param key 密钥
     * @param text 原文
     * @return 密文
     * @throws Exception 异常
     */
    private static String encryptRSA(String algorithm, Key key, String text) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对加密进行初始化 第一个参数是加密模式，第二个参数是你想用的公钥加密还是私钥加密
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] bytes = cipher.doFinal(text.getBytes());
        return Base64.encode(bytes);
    }

    /**
     * RSA 解密
     * @param algorithm 加密算法
     * @param key 密钥
     * @param text 密文
     * @return 明文
     * @throws Exception 异常
     */
    private static String decryptRSA(String algorithm,Key key,String text) throws Exception {
        // 创建加解密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对解密进行初始化 第一个参数是加密模式，第二个参数是你想用的公钥解密还是私钥解密
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] bytes = cipher.doFinal(Base64.decode(text));
        return new String(bytes);
    }

    /**
     * 读取私钥
     * @param privatePath 私钥路径
     * @param algorithm 算法
     * @return 私钥
     */
    public static PrivateKey getPrivateKey(String privatePath, String algorithm) throws Exception {
        // 从文件中读取私钥
        String privateKeyString = FileUtils.readFileToString(new File(privatePath), Charset.defaultCharset());
        // 创建key的工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建私钥key的规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        // 返回私钥对象
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 读取公钥
     * @param publicPath 公钥路径
     * @param algorithm 算法
     * @return 公钥
     * @throws Exception 异常
     */
    public static PublicKey getPublicKey(String publicPath, String algorithm) throws Exception {
        // 从文件中读取公钥
        String publicKeyString = FileUtils.readFileToString(new File(publicPath), Charset.defaultCharset());
        // 创建key的工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建公钥key的规则
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        // 返回公钥对象
        return keyFactory.generatePublic(keySpec);
    }
}
