package com.bookstore.controller.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /**
     * 采用ThreadLocal获取数据库连接
     * @return
     */
    public static Connection getConnectionByThreadLocal() {
        Connection connection = threadLocal.get();
        if (connection == null) {
            try {
                connection = JDBCUtils.getConnection();
                threadLocal.set(connection);
                connection.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static Connection getConnection() {
        InputStream is = null;
        Properties properties = null;
        Connection connection = null;
        try {
            //is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties = new Properties();
            properties.load(is);

            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driveClass = properties.getProperty("driveClass"); //driveClass=com.mysql.cj.jdbc.Driver

            Class.forName(driveClass);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void commitAndClose() {
        Connection connection = threadLocal.get();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        threadLocal.remove(); /* threadLocal用完要remove */
    }

    public static void rollbackAndClose() {
        Connection connection = threadLocal.get();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        threadLocal.remove(); /* threadLocal用完要remove */
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection connection, PreparedStatement ps) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
