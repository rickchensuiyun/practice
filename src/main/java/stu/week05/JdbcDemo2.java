package stu.week05;

import java.sql.*;

/**
 * 相比JdbcDemo，增加了事务，批处理，采用了预编译的方式。
 * 理论上预编译的效率更高。
 */
public class JdbcDemo2 {

    public static final String URL = "jdbc:oracle:thin:@localhost:1521:test";
    public static final String USER = "test";
    public static final String PASSWORD = "testtest";

    public static void main(String[] args) {
//        query();
//        add();
//        batchAdd();
//        update();
//        delete();

    }

    public static void delete() {
        String sql = "delete from T_SEAL_RECORD where id in (?,?,?) ";

        Connection conn = getConn();
        PreparedStatement pt = null;
        try {
            pt = conn.prepareStatement(sql);
            pt.setInt(1, 25);
            pt.setInt(2, 26);
            pt.setInt(3, 27);

            pt.execute();

            //不提交事务
//            conn.rollback();
            conn.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, pt, null);
        }
    }


    public static void update() {
        String sql = " update T_SEAL_RECORD set inst_name = ? where id =  ? ";
        Connection conn = getConn();
        PreparedStatement pt = null;
        try {
            pt = conn.prepareStatement(sql);
            pt.setString(1, "修改后的name");
            pt.setInt(2, 15);

            pt.execute();
            //不提交事务
            conn.rollback();
//            conn.commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, pt, null);
        }
    }

    public static void batchAdd(){
        String sql = "insert into T_SEAL_RECORD (id , inst_name , create_user ,create_time ) values (" +
                " ? , ? , ? , ? )";

        Connection conn = getConn();
        PreparedStatement pt = null;

        try {
            pt = conn.prepareStatement(sql);
            pt.setInt(1, 25);
            pt.setString(2, "测试name1");
            pt.setString(3, "测试人1");
            pt.setDate(4, new Date((new java.util.Date()).getTime()));
            pt.addBatch();

            pt.setInt(1, 26);
            pt.setString(2, "测试name2");
            pt.setString(3, "测试人2");
            pt.setDate(4, new Date((new java.util.Date()).getTime()));
            pt.addBatch();

            pt.setInt(1, 27);
            pt.setString(2, "测试name3");
            pt.setString(3, "测试人3");
            pt.setDate(4, new Date((new java.util.Date()).getTime()));
            pt.addBatch();

            pt.executeBatch();

            //不提交事务
//            conn.rollback();
            conn.commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, pt, null);
        }
    }

    public static void add() {
        String sql = "insert into T_SEAL_RECORD (id , inst_name , create_user ,create_time ) values (" +
                " ? , ? , ? , ? )";

        Connection conn = getConn();
        PreparedStatement pt = null;
        try {
            pt = conn.prepareStatement(sql);
            pt.setInt(1, 15);
            pt.setString(2, "测试name");
            pt.setString(3, "测试人1");
            pt.setDate(4, new Date((new java.util.Date()).getTime()));

            pt.execute();

            //不提交事务
            conn.rollback();
//            conn.commit();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn, pt, null);
        }

    }

    public static void query() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //加载Oracle驱动
        try {
            conn = getConn();
            stmt = conn.prepareStatement("select id,inst_name,CREATE_USER ,SEAL_TYPE from T_SEAL_RECORD");
            rs = stmt.executeQuery();
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

    public static Connection getConn() {

        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(false);//手动提交/回滚事务
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
    public static void close(Connection conn, PreparedStatement st, ResultSet rs) {
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
