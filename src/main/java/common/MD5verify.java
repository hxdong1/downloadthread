package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//md5校验
public class MD5verify {
    public boolean VerifyMD5(String MD5, String path) throws NoSuchAlgorithmException {

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] read = new byte[1024];
            int len = -1;
            while ((len = fileInputStream.read(read)) != -1) {
                md5.update(read, 0, len);
            }
            //关流
            fileInputStream.close();
            //获取哈希值
            byte[] digest = md5.digest();
            String s = digest.toString();
            BigInteger bigInteger = new BigInteger(1, digest);
            String resultsMD5 = bigInteger.toString(16);

            if (resultsMD5.equalsIgnoreCase(MD5)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
