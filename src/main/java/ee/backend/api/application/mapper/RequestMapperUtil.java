package ee.backend.api.application.mapper;

import org.springframework.stereotype.Component;

@Component
public class RequestMapperUtil {

    public String trimStringValue(String value) {
        return value != null ? value.trim() : null;
    }
}
