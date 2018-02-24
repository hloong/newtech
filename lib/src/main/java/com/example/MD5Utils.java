package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     * Ĭ�ϵ������ַ�����ϣ��������ֽ�ת���� 16 ���Ʊ�ʾ���ַ�,apacheУ�����ص��ļ�����ȷ���õľ���Ĭ�ϵ�������
     */
    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * �����ַ�����md5У��ֵ
     *
     * @param s
     * @return
     */
    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    /**
     * ����ļ���ָ����MD5ֵ�Ƿ�ƥ��
     *
     * @param file      ָ�����ļ�
     * @param targetMD5 Ŀ��MD5�ַ���
     * @return
     */
    public static boolean checkMD5(File file, String targetMD5) {
        try {
            String sourceMD5 = getFileMD5String(file);
            return targetMD5.equals(sourceMD5);
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * �����ļ���md5У��ֵ
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getFileMD5String(File file) throws IOException {
        InputStream fis;
        fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int numRead = 0;
        while ((numRead = fis.read(buffer)) > 0) {
            messagedigest.update(buffer, 0, numRead);
        }
        fis.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /*
     * �����汾��ʱ�����ͨ���ò��Է������ɰ汾apk��MD5ֵ
     * filePath��ֵͨ�����䣬ÿ����Ҫ�ֶ��޸�fileNameֵ
     */
    public static void main(String[] args) throws IOException {
        String filePath = "D:/Program Files/360/jiagu/output/hbxglong@163.com/";
        String fileName = "Shop_1.0.3_20180207_Guanwang.apk";
//        String fileName = "IUwallet_1.0.1_20171130_official_online.apk";
        File file = new File(filePath + fileName);
        System.out.print(fileName + "��MD5ֵΪ:\n" + getFileMD5String(file));
    }

}
