package ee.backend.api.application.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequestMapperUtilTest {

    private final RequestMapperUtil requestMapperUtil = new RequestMapperUtil();

    @Test
    void shouldTrimString() {
        assertEquals("", requestMapperUtil.trimStringValue(" "));
        assertEquals("a", requestMapperUtil.trimStringValue(" a"));
        assertEquals("abb", requestMapperUtil.trimStringValue(" abb "));
        assertEquals("abc", requestMapperUtil.trimStringValue("abc "));
        assertEquals("abcd ee", requestMapperUtil.trimStringValue("         abcd ee          "));
        assertNull(requestMapperUtil.trimStringValue(null));
    }

}