package com.fastcampus.baseballmanagement.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getInstance() {
        // MySQL 연결 정보
        String url = "jdbc:mysql://localhost:3306/baseball";
        String username = "root";
        String password = "1q2w3e4r!@";

        // JDBC 드라이버 로드
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("debug : DB has been connected");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}