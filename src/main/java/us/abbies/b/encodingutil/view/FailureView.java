package us.abbies.b.encodingutil.view;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;
import java.util.Map;

public class FailureView extends MappingJackson2JsonView {
    @Override
    @SuppressWarnings("unchecked")
    protected Object filterModel(Map<String, Object> model) {
        Object result = super.filterModel(model);
        if (result instanceof Map) {
            Map map = (Map) result;

            Exception ex = (Exception) map.remove("exception");
            if (ex != null) {
                map.put("errors", Collections.singletonMap(ex.getClass().getName(), ex.getMessage()));
            }
            map.put("success", false);
        }
        return result;
    }
}
