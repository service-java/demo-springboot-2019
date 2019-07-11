package top.zywork.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 注册Druid数据源组件<br />
 * 创建于2017-08-31
 * @author 王振宇
 * @version 1.0
 */
@Configuration
public class DataSourceBeanConfiguration {

    /**
     * 注册DruidDataSource bean
     * @return 由application.yml文件中指定的Datasource配置信息的DruidDataSource对象
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

}
