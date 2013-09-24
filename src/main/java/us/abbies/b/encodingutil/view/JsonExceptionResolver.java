package us.abbies.b.encodingutil.view;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class JsonExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mav = new ModelAndView("exception");

        if (ex instanceof BindException) {
            BindException be = (BindException) ex;
            Map<String, Object> errors = new HashMap<String, Object>();
            for (FieldError fieldError : be.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getRejectedValue());
            }
            mav.addObject("errors", errors);
        } else {
            mav.addObject("exception", ex);
        }
        return mav;
    }
}
