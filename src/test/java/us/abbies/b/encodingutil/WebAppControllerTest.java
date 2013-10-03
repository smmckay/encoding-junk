package us.abbies.b.encodingutil;

import org.apache.commons.codec.DecoderException;
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
        TranscodeRequest req = new TranscodeRequest();
        req.setInputEncoding("UTF-8");
        req.setOutputEncoding("UTF-8");
        req.setHexBytes("4444");
        testRequest(req, "4444");

        req.setOutputEncoding("UTF-16BE");
        testRequest(req, "00440044");
    }

    @Test
    public void testDecode() throws Exception {
        DecodeRequest req = new DecodeRequest();
        req.setInputEncoding("UTF-8");
        req.setHexBytes("4444");
        testRequest(req, "DD");

        req.setInputEncoding("UTF-16BE");
        req.setHexBytes("00440044");
        testRequest(req, "DD");
    }

    @Test
    public void testEncode() throws Exception {
        EncodeRequest req = new EncodeRequest();
        req.setOutputEncoding("UTF-8");
        req.setString("DD");
        testRequest(req, "4444");

        req.setOutputEncoding("UTF-16BE");
        testRequest(req, "00440044");
    }

    private void testRequest(Object req, String value) throws DecoderException {
        Map<String, String> result;
        if (req instanceof TranscodeRequest) {
            result = controller.get((TranscodeRequest) req);
        } else if (req instanceof DecodeRequest) {
            result = controller.get((DecodeRequest) req);
        } else if (req instanceof EncodeRequest) {
            result = controller.get((EncodeRequest) req);
        } else {
            throw new IllegalArgumentException("req: " + req);
        }

        assertThat(result)
                .hasSize(1)
                .includes(entry("output", value));
    }
}
