package com.grad.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 11:26
 **/
@Configuration
@AutoConfigureAfter(DbConfig.class)
public class DataSourceConfig {
    @Autowired
    private DbConfig dbConfig;

    @Bean(name = "metaDb")
    public DataSource metaDbDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(dbConfig.getDriver());
        ds.setJdbcUrl(dbConfig.getUrl());
        ds.setUsername(dbConfig.getUser());
        ds.setPassword(dbConfig.getPassword());
        ds.setMaximumPoolSize(dbConfig.getMaxPoolSize());
        ds.setMinimumIdle(dbConfig.getMinIdelSize());
        return ds;
    }
}
