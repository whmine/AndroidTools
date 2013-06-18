package com.actionbarsherlock.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHelper {

    private static final String DATABASE_URL = "jdbc:sqlite:E:/data.db";

    public static Connection getConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DATABASE_URL, null, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Connection conn;
        try {
            conn = getConnection();
            // 设置自动提交为false
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            // 判断表是否存在
            ResultSet rsTables = conn.getMetaData().getTables(null, null,
                    "student", null);
            if (rsTables.next()) {
                System.out.println("表存在,创建表的事情不要做了");
            } else {
                stmt.executeUpdate("create table student (id,name);");
            }

            stmt.executeUpdate("insert into student values (11,'hehe');");
            stmt.executeUpdate("insert into student values (12,'xixi');");
            stmt.executeUpdate("insert into student values (13,'haha');");
            // 提交
            conn.commit();
            // 得到结果集
            ResultSet rs = stmt.executeQuery("select * from student;");
            while (rs.next()) {
                System.out.println("id = " + rs.getString("id"));
                System.out.println("name = " + rs.getString("name"));
            }
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL异常!");
        }
    }

    public static void close(ResultSet rs, Statement stm, Connection con) {
        close(rs);
        close(stm, con);
    }

    public static void close(Statement stm, Connection con) {
        close(stm);
        close(con);
    }

    public static void close(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Connection close exception!");
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Resultset close exception!");
        }
    }

    public static void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Statement close exception!");
        }
    }
}
