package top.zywork.configuration;

import org.dozer.spring.DozerBeanMapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册Dozer mapper组件<br />
 * 创建于2017-08-31
 * @author 王振宇
 * @version 1.0
 */
@Configuration
public class DozerMapperBeanConfiguration {

    /**
     * 注册DozerMapper bean
     * @return dozerMapper，需要使用dozerMapper的类直接@Resource或@Autowired自动封装
     */
    @Bean
    public DozerBeanMapperFactoryBean getDozerMapper() {
        return new DozerBeanMapperFactoryBean();
    }

}
