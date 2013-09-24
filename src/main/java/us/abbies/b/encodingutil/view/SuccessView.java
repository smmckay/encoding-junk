package us.abbies.b.encodingutil.view;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

public class SuccessView extends MappingJackson2JsonView {
    @Override
    @SuppressWarnings("unchecked")
    protected Object filterModel(Map<String, Object> model) {
        Object result = super.filterModel(model);
        if (result instanceof Map) {
            ((Map<String, Object>) result).put("success", true);
        }
        return result;
    }
}
