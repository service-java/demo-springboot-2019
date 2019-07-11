package top.zywork.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.zywork.constant.AppLoadConstants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Spring Boot注册自定义Bean组件的类<br />
 * 创建于2017-08-31
 * @author 王振宇
 * @version 1.0
 */
@Configuration
public class ApplicationBeanConfiguration {

    @Value("${appConfigLocation}")
    private String appConfigLocation;

    /**
     * ServletContext初始化Bean，由此Bean设置监听器的初始化参数
     * @return 带有自定义context-param的ServletContext初始化对象
     */
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {

            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter(AppLoadConstants.CONFIG_LOCATION, appConfigLocation);
            }
        };
    }

}
