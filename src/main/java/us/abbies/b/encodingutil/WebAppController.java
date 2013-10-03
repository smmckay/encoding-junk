package us.abbies.b.encodingutil;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/api", method = GET)
public class WebAppController {
    @RequestMapping("/transcode")
    public Map<String, String> get(@Valid TranscodeRequest req) throws DecoderException {
        Charset inputCharset = Charset.forName(req.getInputEncoding());
        Charset outputCharset = Charset.forName(req.getOutputEncoding());

        byte[] inputBytes = Hex.decodeHex(req.getHexBytes().toCharArray());
        String inputString = new String(inputBytes, inputCharset);
        byte[] outputBytes = inputString.getBytes(outputCharset);
        String outputString = new String(Hex.encodeHex(outputBytes, false));
        return Collections.singletonMap("output", outputString);
    }

    @RequestMapping("/decode")
    public Map<String, String> get(@Valid DecodeRequest req) throws DecoderException {
        Charset inputCharset = Charset.forName(req.getInputEncoding());
        byte[] inputBytes = Hex.decodeHex(req.getHexBytes().toCharArray());
        String outputString = new String(inputBytes, inputCharset);
        return Collections.singletonMap("output", outputString);
    }

    @RequestMapping("/encode")
    public Map<String, String> get(@Valid EncodeRequest req) {
        Charset outputCharset = Charset.forName(req.getOutputEncoding());
        String inputString = req.getString();
        byte[] outputBytes = inputString.getBytes(outputCharset);
        String outputString = new String(Hex.encodeHex(outputBytes, false));
        return Collections.singletonMap("output", outputString);
    }
}
