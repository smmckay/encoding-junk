package us.abbies.b.encodingutil.view;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Locale;

public class JsonViewResolver implements ViewResolver {
    private final MappingJackson2JsonView failureView;
    private final MappingJackson2JsonView successView;

    public JsonViewResolver() {
        failureView = new FailureView();
        successView = new SuccessView();
        successView.setModelKey("output");
    }

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (viewName.equals("exception")) {
            return failureView;
        }
        return successView;
    }
}
