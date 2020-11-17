package com.warma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Applicaton {
    static String database="warma_mall";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/"+database+"?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";
    static ArrayList<String> list1=new ArrayList<String>();
    static ArrayList<String> list2=new ArrayList<String>();
    public static void main(String[] args) {
        try{
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt= conn.createStatement();

            ResultSet rs = stmt.executeQuery("show tables");

            while(rs.next()){
                String table = rs.getString("Tables_in_warma_mall");
                list1.add(table);
            }
            for (String table : list1) {
                String sql ="SELECT COLUMN_NAME,COLUMN_KEY FROM \n" +
                            "INFORMATION_SCHEMA.COLUMNS\n" +
                            "WHERE\n" +
                            "table_name = '"+table+"' AND table_schema = '"+database+"'";
                ResultSet resultSet = stmt.executeQuery(sql);
                while(resultSet.next()){
                    String column_key = resultSet.getString("COLUMN_KEY");
                    if(column_key.contains("PRI")){
                        String column_name = resultSet.getString("COLUMN_NAME");
                        list2.add(column_name);
                        break;
                    }
                }
            }

            String result = Warma.createTable(list1,list2);
            System.out.println(result);

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }
    }
}
