package com.wjj.miaosha.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjj.miaosha.pojo.User;
import com.wjj.miaosha.vo.RespBean;
import sun.security.provider.MD5;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 生成用户工具类
 * @Author: wjj
 * @CreateTime: 2023-11-02
 * @Version: 1.0
 */
public class UserUtil {
    private static void creatUser(int count) throws Exception {
        List<User> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(1300000000L + i);
            user.setNickname("user" + i);
            user.setSlat("1a2b3c");
            user.setPassword(MD5Util.inputPassToDBPass("123456", user.getSlat()));
            user.setRegisterDate(new Date());
            user.setLoginCount(1);
            users.add(user);
        }
        System.out.println("create user");
        //插入数据库的操作
        Connection conn = getConn();
        String sql = "insert into t_user(login_count,nickname,register_date,slat,password,id)values(?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            pst.setInt(1, user.getLoginCount());
            pst.setString(2, user.getNickname());
            pst.setTimestamp(3, new Timestamp(user.getRegisterDate().getTime()));
            pst.setString(4, user.getSlat());
            pst.setString(5, user.getPassword());
            pst.setLong(6, user.getId());
        }
        pst.executeBatch();
        pst.clearParameters();
        conn.close();
        System.out.println("insert to db");
//        //登录，生成userTicket
//        String urlString = "http://localhost:8080/login/toLogin";
//        File file = new File("E:\\桌面\\config.txt");
//        if (file.exists()) {
//            file.delete();
//        }
//        RandomAccessFile raf = new RandomAccessFile(file, "rw");
//        raf.seek(0);
//        for (int i = 0; i < users.size(); i++) {
//            User user = users.get(i);
//            URL url = new URL(urlString);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setDoOutput(true);
//            OutputStream outputStream = httpURLConnection.getOutputStream();
//            String parms = "mobile=" + user.getId() + "&password=" + MD5Util.inputPassToFromPass("123456");
//            outputStream.write(parms.getBytes());
//            outputStream.flush();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            byte[] buff = new byte[1024];
//            int len = 0;
//            while ((len = inputStream.read(buff)) >= 0) {
//                byteArrayOutputStream.write(buff, 0, len);
//            }
//            inputStream.close();
//            byteArrayOutputStream.close();
//            String response = new String(byteArrayOutputStream.toByteArray());
//            ObjectMapper mapper = new ObjectMapper();
//            RespBean respBean = mapper.readValue(response, RespBean.class);
//            String userTicket = (String) respBean.getObj();
//            System.out.println("create userTicket :" + user.getId());
//            String row = user.getId() + "," + userTicket;
//            raf.seek(raf.length());
//            raf.write(row.getBytes());
//            raf.write("\r\n".getBytes());
//            System.out.println("write to file :" + user.getId());
//        }
//        raf.close();
//        System.out.println("over");


    }

    private static Connection getConn() throws Exception {
        String url = "jdbc:mysql://134.175.62.214:3306/miaosha?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
        String userName = "root";
        String passwd = "123456";
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(url, userName, passwd);
    }

    public static void main(String[] args) throws Exception {
        creatUser(5000);
    }
}
