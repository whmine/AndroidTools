package com.actionbarsherlock.tools;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.actionbarsherlock.tools.pub.ConnectionConfigDef;
import com.actionbarsherlock.tools.pub.ConnectionConfigReader;
import com.actionbarsherlock.tools.pub.ConnectionDef;
import com.actionbarsherlock.tools.pub.exception.SysException;

public class DbUtil {

    private static final String CONNECTIONS_FILE = "connection-config.xml";
    private static ConnectionConfigDef config;

    public static void initConfig() {
        ConnectionConfigReader reader = new ConnectionConfigReader();
        InputStream in = DbUtil.class.getClassLoader().getResourceAsStream(
                CONNECTIONS_FILE);
        try {
            reader.read(in);
            config = reader.getConfigDef();
        } catch (SysException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return database connection
     */
    public static Connection getConnection() {
        if (config == null) {
            initConfig();
        }
        String defaultConnName = config.getDefaultName();
        Map connMap = config.getConnections();
        ConnectionDef connDef = (ConnectionDef) connMap.get(defaultConnName);

        Connection conn = null;

        try {
            Class.forName(connDef.getDriverClass());
            conn = DriverManager.getConnection(connDef.getUrl(),
                    connDef.getUserName(), connDef.getPassword());
        } catch (ClassNotFoundException e) {
            System.out.println(" No class " + connDef.getDriverClass()
                    + " found error");
            throw new RuntimeException("Connection create exception!" + e);
        } catch (SQLException e) {
            System.out.println("Failed to get connection :" + e.getMessage());
            throw new RuntimeException("Connection create exception!");
        }

        return conn;
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

    public static long getNextId(Connection con, String sequence) {
        Statement st = null;
        ResultSet rs = null;
        long nextId = 1;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT " + sequence
                    + ".nextval as id FROM dual");
            rs.next();
            nextId = rs.getLong(1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, st, null);
        }
        return nextId;
    }

    public static Date getCurrentDate(Connection con) throws SQLException {
        String strSQL = " SELECT sysdate FROM dual";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(strSQL);
        rs.next();
        Date dtRtn = rs.getDate(1);
        close(rs, st, null);
        return dtRtn;
    }

}
