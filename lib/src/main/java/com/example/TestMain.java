package com.example;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

/**
 * 鑫海活体检测API测试
 */
public class TestMain {
    static String url = "https://service.eidcc.com/api";
    static String account="szqk"; //账号
    static String key = "JCvx7U7342WA4J2lKF8wAAKvBuAkBSA5"; //密码
    public static void main(String[] args){
        String acode = "900500";//代码（固定不变） 代码（固定不变） 代码（固定不变）
        String idNumber = "411422198905176929"; //身份证号
        String Name = "任丽"; //姓名
        String Photo= GetImageStrFromPath("D:/zhengmian.jpg");//Base64编码 jpg格式
        String param = "idNumber=" + idNumber + "&Name=" + Name+"&Photo="+Photo;
        String sign = md5(acode + param + account + md5(key).toUpperCase()).toUpperCase();//生成签名 生成签名
        String post_data = null;
        try {
            post_data = "acode=" + acode + "&param=" + URLEncoder.encode(param, "UTF-8") + "&account="
                    + account + "&sign=" + sign;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        String json = postHtml(url, post_data);
//返回的 json 即为查询到的信息 即为查询到的信息 即为查询到的信息 即为查询到的信息
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
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }
}