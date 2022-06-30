package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.DAO;
import com.bookstore.controller.utils.JDBCUtils;
import com.bookstore.controller.utils.ReflectionUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.util.List;

public class BaseDAO<T> implements DAO<T> {

    QueryRunner queryRunner = new QueryRunner();
    private Class<T> clazz; //clazz为T类型的Class

    public BaseDAO() {
        //this.getClass()中的this为BaseDAO的子类对象，获取BaseDAO子类对象的Class
        Class<? extends BaseDAO> aClass = this.getClass(); //aClass为实际运行时BaseDAO的子类对象，<? extends BaseDAO>
        this.clazz = ReflectionUtils.getSuperGenericType(aClass); //获得aClass带有泛型的父类
    }

    /**
     * 往数据库插入数据通用操作
     * @param sql
     * @param args
     * @return 自增ID
     */
    @Override
    public long insert(String sql, Object... args) {
        long id = 0; //插入记录后，记录自增ID值
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //connection = JDBCUtils.getConnection();
            connection = JDBCUtils.getConnectionByThreadLocal(); //采用ThreadLocal
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //Statement.RETURN_GENERATED_KEYS可以获取到sql语句执行后影响的关键字集

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e); /* 抛给TransactionFilter处理，一般是事务回滚 */
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }/* finally {
            JDBCUtils.close(connection, ps, rs); 已被 JDBCUtils.commitAndClose() 或 JDBCUtils.rollbackAndClose() 关闭，无需再关闭，否则会报错
        }*/
        return id;
    }

    /**
     * 更新数据表中的数据
     * @param sql
     * @param args
     */
    @Override
    public void update(String sql, Object... args) {
        Connection connection = null;
        try {
            //connection = JDBCUtils.getConnection();
            connection = JDBCUtils.getConnectionByThreadLocal(); //采用ThreadLocal
            queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e); /* 抛给TransactionFilter处理，一般是事务回滚 */
        }/* finally {
            JDBCUtils.close(connection); 已被 JDBCUtils.commitAndClose() 或 JDBCUtils.rollbackAndClose() 关闭，无需再关闭，否则会报错
        }*/
    }

    /**
     * 查询一条记录，如: select id, name from xxx where id = ?
     * @param sql
     * @param args
     * @return 数据表中的一条记录
     */
    @Override
    public T query(String sql, Object... args) {
        Connection connection = null;
        T query = null;
        try {
            //connection = JDBCUtils.getConnection();
            connection = JDBCUtils.getConnectionByThreadLocal(); //采用ThreadLocal
            query = queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException e) {
            throw new RuntimeException(e); /* 抛给TransactionFilter处理，一般是事务回滚 */
        }/* finally {
            JDBCUtils.close(connection); 已被 JDBCUtils.commitAndClose() 或 JDBCUtils.rollbackAndClose() 关闭，无需再关闭，否则会报错
        }*/
        return query;
    }

    /**
     * 查询多个结果，如: select id from xxx，返回多条结果
     * @param sql
     * @param args
     * @return 结果集
     */
    @Override
    public List<T> queryForList(String sql, Object... args) {
        Connection connection = null;
        List<T> list = null;
        try {
            //connection = JDBCUtils.getConnection();
            connection = JDBCUtils.getConnectionByThreadLocal(); //采用ThreadLocal
            list = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e) {
            throw new RuntimeException(e); /* 抛给TransactionFilter处理，一般是事务回滚 */
        }/* finally {
            JDBCUtils.close(connection); 已被 JDBCUtils.commitAndClose() 或 JDBCUtils.rollbackAndClose() 关闭，无需再关闭，否则会报错
        }*/
        return list;
    }

    /**
     * 获取单个数值，如: select count(*) from xxx 返回的值
     * @param sql
     * @param args
     * @return
     * @param <V> 单个数值
     */
    @Override
    public <V> V getSingleValue(String sql, Object... args) {
        Connection connection = null;
        V value;
        try {
            //connection = JDBCUtils.getConnection();
            connection = JDBCUtils.getConnectionByThreadLocal(); //采用ThreadLocal
            value = (V)queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            throw new RuntimeException(e); /* 抛给TransactionFilter处理，一般是事务回滚 */
        }/* finally {
            JDBCUtils.close(connection); 已被 JDBCUtils.commitAndClose() 或 JDBCUtils.rollbackAndClose() 关闭，无需再关闭，否则会报错
        }*/
        return value;
    }

    /**
     * 批量操作，本方法较少使用，可以被其他方法取代。
     * @param sql
     * @param args
     */
    @Override
    public void batch(String sql, Object[]... args) {
        Connection connection = null;
        try {
            //connection = JDBCUtils.getConnection();
            connection = JDBCUtils.getConnectionByThreadLocal(); //采用ThreadLocal
            queryRunner.batch(connection, sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e); /* 抛给TransactionFilter处理，一般是事务回滚 */
        }/* finally {
            JDBCUtils.close(connection); 已被 JDBCUtils.commitAndClose() 或 JDBCUtils.rollbackAndClose() 关闭，无需再关闭，否则会报错
        }*/
    }
}
