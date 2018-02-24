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
 * �����Ƽ�API��Ȩ������
 */
public class MyClass {

    public static String API_KEY = "3f22a51a4fe94b54a5d405329291c39b";
    public static String API_SECRET = "5783838b549d411ab84c87b9343b5032";

    public static String timestamp = System.currentTimeMillis() + "";
//������nonce,�����32λ��uuid���������淽ʽʵ�֣�
    public static synchronized String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String nonce=str.replace("-", "");
        return nonce;
    }

//��timestamp��nonce��API_KEY �������ַ��������������У������ַ�����λ�ַ���ASCII��)����join��һ���ַ������������淽ʽʵ��:
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
//��API_SECRET��join_str��hamc-sha256ǩ��������16���Ʊ��룬�������淽ʽʵ�֣�
    public static String genEncryptString(String join_str, String API_SECRET) throws NoSuchAlgorithmException, InvalidKeyException {

        Key sk = new SecretKeySpec(API_SECRET.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance(sk.getAlgorithm());
        mac.init(sk);
        final byte[] hmac = mac.doFinal(join_str.getBytes());//���hamc-sha256ǩ��
        StringBuilder sb = new StringBuilder(hmac.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : hmac) {
            formatter.format("%02x", b);
        }
        String signature = sb.toString();//���16���Ʊ�����
        return signature;
    }
    //��������ֵ���� #{k}=#{v} ���� ',' join��һ�𣬿������淽ʽʵ�֣�
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
     * ����ƴ������Ƚ���
     */
    public static class SpellComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            try {
                // ȡ�ñȽ϶���ĺ��ֱ��룬������ת�����ַ���
                String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
                String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
                // ����String��� compareTo������������������бȽ�
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
