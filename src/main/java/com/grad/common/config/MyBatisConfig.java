package com.grad.common.config;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 描述 ：SqlSessionFactory Bean
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 11:38
 **/
@Configuration
@EnableTransactionManagement
public class MyBatisConfig {

    // 连接池
    @Resource
    private DataSource metaDb;

    @Bean(name = "metaDbSqlSessionFactory")
    public SqlSessionFactory metaDbSqlSessionFactory() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(metaDb);
        bean.setTypeAliasesPackage(getTypeAliasePackages());

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //添加mapper文件映射位置
        try {
            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String getTypeAliasePackages() {
        List<String> packages = new ArrayList<>();
        packages.add("com.grad.eneity");
        return StringUtils.arrayToDelimitedString(packages.toArray(), ";");
    }

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager dtm1 = new DataSourceTransactionManager(metaDb);
        ChainedTransactionManager ctm = new ChainedTransactionManager(dtm1);
        return ctm;
    }
}
