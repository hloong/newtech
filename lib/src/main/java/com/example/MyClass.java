package com.example;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 商汤科技API授权工具类
 */
public class MyClass {

    public static String API_KEY = "3f22a51a4fe94b54a5d405329291c39b";
    public static String API_SECRET = "5783838b549d411ab84c87b9343b5032";

    public static String timestamp = System.currentTimeMillis() + "";
//获得随机nonce,最好是32位的uuid，可用下面方式实现：
    public static synchronized String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String nonce=str.replace("-", "");
        return nonce;
    }

//将timestamp、nonce、API_KEY 这三个字符串进行升序排列（依据字符串首位字符的ASCII码)，并join成一个字符串，可用下面方式实现:
    public static String genjoinstr(String timestamp,String nonce,String API_KEY){

        ArrayList<String> beforesort = new ArrayList<String>();
        beforesort.add(API_KEY);
        beforesort.add(timestamp);
        beforesort.add(nonce);

        Collections.sort(beforesort, new SpellComparator());
        StringBuffer aftersort = new StringBuffer();
        for (int i = 0; i < beforesort.size(); i++) {
            aftersort.append(beforesort.get(i));
        }

        String join_str = aftersort.toString();
        return join_str;
    }
//用API_SECRET对join_str做hamc-sha256签名，且以16进制编码，可用下面方式实现：
    public static String genEncryptString(String join_str, String API_SECRET) throws NoSuchAlgorithmException, InvalidKeyException {

        Key sk = new SecretKeySpec(API_SECRET.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance(sk.getAlgorithm());
        mac.init(sk);
        final byte[] hmac = mac.doFinal(join_str.getBytes());//完成hamc-sha256签名
        StringBuilder sb = new StringBuilder(hmac.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : hmac) {
            formatter.format("%02x", b);
        }
        String signature = sb.toString();//完成16进制编码检测
        return signature;
    }
    //将上述的值按照 #{k}=#{v} 并以 ',' join在一起，可用下面方式实现：
    public static String genauthorization(String API_KEY, String timestamp, String nonce, String signature){

        String authorization = "key=" + API_KEY
                +",timestamp=" + timestamp
                +",nonce=" + nonce
                +",signature=" + signature;
        return authorization;
    }

    public static String getAuth() throws InvalidKeyException, NoSuchAlgorithmException {
        String uuid = getUUID();
       return genauthorization(API_KEY,timestamp,uuid,genEncryptString(genjoinstr(timestamp,uuid,API_KEY),API_SECRET));
    }


    /**
     * 汉字拼音排序比较器
     */
    public static class SpellComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            try {
                // 取得比较对象的汉字编码，并将其转换成字符串
                String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
                String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
                // 运用String类的 compareTo（）方法对两对象进行比较
                return s1.compareTo(s2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }


//    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException {
//
//        String jon_str = genjoinstr(timestamp,getUUID(),API_KEY);
//        String encrypt_string = genEncryptString(jon_str,API_SECRET);
//        String genauthorization = genauthorization(API_KEY,timestamp,getUUID(),genEncryptString(jon_str,API_SECRET));
//        System.out.println("jon_str--->"+jon_str);
//        System.out.println("encrypt_string--->"+encrypt_string);
//        System.out.println("genauthorization-->"+genauthorization);
//    }

}
