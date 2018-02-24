package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * ��Ȩ���У�2016 ��Ŀ���ƣ�ImgeBase64 
 *
 * ����������ͼƬת��ΪBase64�ַ���  
 * �����ƣ�cn.sanishan.util.Base64Img  
 * �����ˣ� 
 * ����ʱ�䣺2016��10��27�� 
 * ����3:25:49  
 * �޸��ˣ�  
 * �޸�ʱ�䣺2016��10��27�� ����3:25:49  
 * �޸ı�ע�� 
 *
 * @version V1.0
 */
public class Base64Img {
    /**
     * @Title: GetImageStrFromUrl
     * @Description: TODO(��һ������ͼƬת����Base64�ַ���)
     * @param imgURL ������Դλ�� 
     * @return Base64�ַ���
     */
    public static String GetImageStrFromUrl(String imgURL) {
        byte[] data = null;
        try {
            // ����URL  
            URL url = new URL(imgURL);
            // ��������  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();
            data = new byte[inStream.available()];
            inStream.read(data);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ���ֽ�����Base64����  
        BASE64Encoder encoder = new BASE64Encoder();
        // ����Base64��������ֽ������ַ���  
        return encoder.encode(data);
    }

    /**
     * @Title: GetImageStrFromPath
     * @Description: TODO(��һ�ű���ͼƬת����Base64�ַ���)
     * @param imgPath
     * @return
     */
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

    /**
     * @Title: GenerateImage
     * @Description: TODO(base64�ַ���ת����ͼƬ)
     * @param imgStr
     * @return
     */
    public static boolean GenerateImage(String imgStr) {
        if (imgStr == null) // ͼ������Ϊ��  
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64����  
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// �����쳣����  
                    b[i] += 256;
                }
            }
            // ����jpegͼƬ  
            String imgFilePath = "d://222.jpg";
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}  