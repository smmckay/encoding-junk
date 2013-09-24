package us.abbies.b.encodingutil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import us.abbies.b.encodingutil.view.JsonExceptionResolver;
import us.abbies.b.encodingutil.view.JsonViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebAppConfig {
    @Bean
    public ViewResolver viewResolver() {
        return new JsonViewResolver();
    }

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new JsonExceptionResolver();
    }
}
