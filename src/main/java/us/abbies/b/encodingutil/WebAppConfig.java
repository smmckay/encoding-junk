package us.abbies.b.encodingutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebAppConfig {
    @Bean
    public ViewResolver viewResolver() {
        return new ViewResolver() {

            private final MappingJackson2JsonView successView = new MappingJackson2JsonView() {
                @Override
                protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                    model.put("success", true);
                    super.renderMergedOutputModel(model, request, response);
                }

            };

            private final MappingJackson2JsonView failureView = new MappingJackson2JsonView() {
                private final Logger logger = LoggerFactory.getLogger(WebAppConfig.class);

                @Override
                protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                    Exception ex = (Exception) model.remove("exception");
                    Object handler = model.remove("handler");
                    logger.info("Exception caught in {}", handler, ex);
                    model.clear();
                    model.put("success", false);
                    model.put("errorMessage", ex.toString());
                    super.renderMergedOutputModel(model, request, response);
                }
            };

            @Override
            public View resolveViewName(String viewName, Locale locale) throws Exception {
                if (viewName.equals("exception")) {
                    return failureView;
                }
                return successView;
            }
        };
    }

    @Bean
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                ModelAndView mav = new ModelAndView("exception");
                mav.addObject("exception", ex);
                mav.addObject("handler", handler);
                return mav;
            }
        };
    }
}
