package us.abbies.b.encodingutil;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

public class WebAppControllerTest {

    private WebAppController controller;

    @Before
    public void createController() {
        controller = new WebAppController();
    }

    @Test
    public void testTranscode() throws Exception {
        checkResult(controller.transcode("UTF-8", "UTF-8", "4444"), "4444");
        checkResult(controller.transcode("UTF-8", "UTF-16BE", "4444"), "00440044");
    }

    @Test
    public void testDecode() throws Exception {
        checkResult(controller.decode("UTF-8", "4444"), "DD");
        checkResult(controller.decode("UTF-16BE", "00440044"), "DD");
    }

    @Test
    public void testEncode() throws Exception {
        checkResult(controller.encode("UTF-8", "DD"), "4444");
        checkResult(controller.encode("UTF-16BE", "DD"), "00440044");
    }

    private static void checkResult(Map<String, String> result, String expected) {
        assertThat(result)
                .hasSize(1)
                .includes(entry("output", expected));
    }
}
