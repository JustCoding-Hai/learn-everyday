import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;

import java.io.File;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/**
 * 加解密工具类测试
 *
 * @author huangjinhai
 * @date 2021-11-29
 */
public class EncDecTest {


    @Test
    public void testEncDec() {

        /**
         * AES加解密
         */
        String cont = "Hello";
        String key = "Yw/Vz4kpJUv0+E/4/LuZEA==";
        System.out.println("aes key = " + key);
        System.out.println(Arrays.toString(Base64.decode(key)));
        AES aes = SecureUtil.aes(Base64.decode(key));
        byte[] encrypt = aes.encrypt(cont);
        String enc = Base64.encode(encrypt);
        System.out.println("密文:" + enc);
        byte[] decrypt = aes.decrypt(enc);
        System.out.println(new String(decrypt));

        /**
         * MD5加解密
         */
        String str = SecureUtil.md5(cont);
        System.out.println("MD5: " + str);
        System.out.println("MD5 16位: " + DigestUtil.md5Hex16(cont));

        /**
         * sha256
         */
        System.out.println("SHA-256:" + DigestUtil.sha256Hex("Admin@888" + "9348ierj8truigjf03wioekrutgfhj"));

        /**
         * RSA加解密
         */
        RSA rsa = new RSA();
        String privateKey = rsa.getPrivateKeyBase64();
        String publicKey = rsa.getPublicKeyBase64();
        System.out.println("私钥：" + privateKey);
        System.out.println("公钥：" + publicKey);
        String rsaEnc = rsa.encryptBase64(cont, KeyType.PublicKey);
        System.out.println("加密后：" + rsaEnc);
        System.out.println("解密后：" + rsa.decryptStr(rsaEnc, KeyType.PrivateKey));
        PublicKey publicK = rsa.getPublicKey();
        String modulus = HexUtil.encodeHexStr(((RSAPublicKey) publicK).getModulus().toByteArray());
        String exponent = HexUtil.encodeHexStr(((RSAPublicKey) publicK).getPublicExponent().toByteArray());
        System.out.println(modulus);
        System.out.println(exponent);

        /**
         * 生成文件摘要
         */
        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, "password".getBytes());

        // b977f4b13f93f549e06140971bded384
        String macHex1 = mac.digestHex("123");
        System.out.println("HAMC - " + macHex1);

        /**
         * 3DES加密
         */
        // 3DES加密
        String content = "test中文";

        byte[] desKey = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
        SymmetricCrypto des = new SymmetricCrypto(SymmetricAlgorithm.DESede, desKey);
        // 加密
        byte[] desEnc = des.encrypt(content);
        // 解密
        byte[] desDec = des.decrypt(desEnc);
        // 加密为16进制字符串（Hex表示）
        String encryptHex = des.encryptHex(content);
        // 解密为字符串
        //String decryptStr = des.decryptStr(encryptHex);
        System.out.println("3DES解密串：" + new String(desDec));
    }

}
