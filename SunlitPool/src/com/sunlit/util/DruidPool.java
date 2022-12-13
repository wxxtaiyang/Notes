package com.sunlit.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidPool {
    private DataSource dataSource;
    private static DruidPool druidPool;

    private DruidPool() {
        Properties p = new Properties();
        try{
            p.load(new FileInputStream("druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(p);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static DruidPool getInstance(){
        if(druidPool == null){
            druidPool = new DruidPool();
        }
        return druidPool;
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭方法
     * @param ctn 连接对象
     * @param ps 执行者对象
     * @param rs 数据对象
     */
    public void close(Connection ctn, PreparedStatement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ctn != null) {
            try {
                ctn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
