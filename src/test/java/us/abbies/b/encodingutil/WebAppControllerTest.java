package us.abbies.b.encodingutil;

import org.junit.Before;
import org.junit.Test;

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
        assertThat(controller.transcode("UTF-8", "UTF-8", "4444"))
                .hasSize(1).includes(entry("output", "4444"));
        assertThat(controller.transcode("UTF-8", "UTF-16BE", "4444"))
                .hasSize(1).includes(entry("output", "00440044"));
    }

    @Test
    public void testDecode() throws Exception {
        assertThat(controller.decode("UTF-8", "4444"))
                .hasSize(1).includes(entry("output", "DD"));
        assertThat(controller.decode("UTF-16BE", "00440044"))
                .hasSize(1).includes(entry("output", "DD"));
    }

    @Test
    public void testEncode() throws Exception {
        assertThat(controller.encode("UTF-8", "DD"))
                .hasSize(1).includes(entry("output", "4444"));
        assertThat(controller.encode("UTF-16BE", "DD"))
                .hasSize(1).includes(entry("output", "00440044"));
    }
}
