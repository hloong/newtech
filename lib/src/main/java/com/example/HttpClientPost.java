package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * Created by 900003 on 2017/11/27.
 * �����Ƽ�����ƥ��API
 */

public class HttpClientPost {
    public static final String filepath = "C:/Users/900003/Desktop/003.png";//ͼƬ·��
    public static final String POST_URL = "https://v2-auth-api.visioncloudapi.com/identity/idnumber_verification/stateless";
    public static final String username = "����";//����
    public static final String id_number = "420921198812102615";//���֤��

    public static void HttpClientPost() throws IOException{
        System.out.println("http===>");
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(POST_URL);
        StringBody name = new StringBody(username, Charset.forName("UTF-8"));
        StringBody number = new StringBody(id_number);
        FileBody fileBody = new FileBody(new File(filepath));
        MultipartEntity entity = new MultipartEntity();

        entity.addPart("name", name);
        entity.addPart("idnumber", number);
        entity.addPart("image_file", fileBody);
        post.setEntity(entity);
        try {
            System.out.println("Auth===>"+ MyClass.getAuth());
            post.setHeader("Authorization",MyClass.getAuth());//�뽫AUTHORIZATION�滻Ϊ����API_KEY��API_SECRET�õ���ǩ����֤��
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        HttpResponse response = httpclient.execute(post);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entitys = response.getEntity();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(entitys.getContent()));
            String line = reader.readLine();
            System.out.println(line);
        }else{
            HttpEntity r_entity = response.getEntity();
            String responseString = EntityUtils.toString(r_entity);
            System.out.println("�������ǣ�"+response.getStatusLine().getStatusCode()+"  "+response.getStatusLine().getReasonPhrase());
            System.out.println("����ԭ���ǣ�"+responseString);
            //����Ҫ���ݳ����ԭ���жϴ�����Ϣ�����޸�
        }

        httpclient.getConnectionManager().shutdown();
    }

    public static void main(String[] args) throws IOException{
        HttpClientPost();
    }
}
