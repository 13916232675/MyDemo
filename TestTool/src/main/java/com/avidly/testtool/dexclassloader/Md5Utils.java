package com.avidly.testtool.dexclassloader;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Utils {
	

    public static String fileMd5(String path) throws NoSuchAlgorithmException, IOException {
        File file = new File(path);
        return fileMd5(file);
    }

    public static String fileMd5(File file) throws NoSuchAlgorithmException, IOException {
        return fileMd5(new FileInputStream(file), true);
    }

    public static String fileMd5(InputStream in, boolean close) throws IOException, NoSuchAlgorithmException {
    	char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte buffer[] = new byte[1024];
        int len;
        while ((len = in.read(buffer, 0, 1024)) != -1) {
            digest.update(buffer, 0, len);
        }
        if (close) {
        	in.close();
        }
        byte[] md = digest.digest();
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hex[byte0 >>> 4 & 0xf];
            str[k++] = hex[byte0 & 0xf];
        }
        return new String(str);
    }

    public static String textOfMd5(String source) throws IOException, NoSuchAlgorithmException {
        char hex[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] data = source.getBytes();
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(data, 0, data.length);
        byte[] md = digest.digest();
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hex[byte0 >>> 4 & 0xf];
            str[k++] = hex[byte0 & 0xf];
        }
        return new String(str);
    }

}
