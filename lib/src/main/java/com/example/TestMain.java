package com.example;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * �κ�������API����
 */
public class TestMain {
    static String url = "https://service.eidcc.com/api";
    static String account="szqk"; //�˺�
    static String key = "JCvx7U7342WA4J2lKF8wAAKvBuAkBSA5"; //����
    public static void main(String[] args){
        String acode = "900500";//���루�̶����䣩 ���루�̶����䣩 ���루�̶����䣩
        String idNumber = "411422198905176929"; //���֤��
        String Name = "����"; //����
        String Photo= GetImageStrFromPath("D:/zhengmian.jpg");//Base64���� jpg��ʽ
        String param = "idNumber=" + idNumber + "&Name=" + Name+"&Photo="+Photo;
        String sign = md5(acode + param + account + md5(key).toUpperCase()).toUpperCase();//����ǩ�� ����ǩ��
        String post_data = null;
        try {
            post_data = "acode=" + acode + "&param=" + URLEncoder.encode(param, "UTF-8") + "&account="
                    + account + "&sign=" + sign;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        String json = postHtml(url, post_data);
//���ص� json ��Ϊ��ѯ������Ϣ ��Ϊ��ѯ������Ϣ ��Ϊ��ѯ������Ϣ ��Ϊ��ѯ������Ϣ
        System.out.println(json);
    }
    static String md5(String text) {
        byte[] bts;
        try {
            bts = text.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bts_hash = md.digest(bts);
            StringBuffer buf = new StringBuffer();
            for (byte b : bts_hash) {
                buf.append(String.format("%02X", b & 0xff));
            }
            return buf.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
    static String postHtml(String url, String postData) {
        try {
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(postData);
            out.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    public static String GetImageStrFromPath(String imgPath) {
        InputStream in = null;
        byte[] data = null;
        // ��ȡͼƬ�ֽ�����
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ���ֽ�����Base64����
        BASE64Encoder encoder = new BASE64Encoder();
        // ����Base64��������ֽ������ַ���
        return encoder.encode(data);
    }
}