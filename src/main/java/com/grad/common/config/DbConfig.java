package com.grad.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 ：数据库基本配置
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 11:24
 **/
@Configuration
@ConfigurationProperties(prefix = "db")
public class DbConfig {
    private String driver;
    private String url;
    private String user;
    private String password;
    private int maxPoolSize;
    private int minIdelSize;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSzie) {
        this.maxPoolSize = maxPoolSzie;
    }

    public int getMinIdelSize() {
        return minIdelSize;
    }

    public void setMinIdelSize(int minIdelSize) {
        this.minIdelSize = minIdelSize;
    }
}
