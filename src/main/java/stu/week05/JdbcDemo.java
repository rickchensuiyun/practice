package stu.week05;

import java.sql.*;

public class JdbcDemo {

    public static final String URL = "jdbc:oracle:thin:@localhost:1521:test";
    public static final String USER = "test";
    public static final String PASSWORD = "testtest";

    public static void main(String[] args) {
//        add();
//        update();
//        delete();
        query();
    }

    public static void delete() {
        String sql = "delete from T_SEAL_RECORD where id = 1";
        execute(sql);
    }

    public static void update() {
        String sql = " update T_SEAL_RECORD set inst_name ='test2' where id = 1";
        execute(sql);
    }

    public static void add() {
        String sql = "insert into T_SEAL_RECORD (id , inst_name , create_user ,create_time ) values (" +
                " 1 , 'test' , '测试1' ,sysdate )";
        execute(sql);
    }


    /**
     * 执行
     *
     * @param sql
     */
    public static void execute(String sql) {

        Connection conn = getConn();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, stmt, null);
        }
    }

    /**
     * 查询
     */
    public static void query() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //加载Oracle驱动
        try {
            conn = getConn();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id,inst_name,CREATE_USER ,SEAL_TYPE from T_SEAL_RECORD");
            //如果有数据，rs.next()返回true
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "---" + rs.getString("inst_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, stmt, rs);
        }
    }

    /**
     * 获取连接
     *
     * @return
     */
    public static Connection getConn() {

        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
