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
@RequestMapping("/")
public class WebAppController {
    @RequestMapping(value = "/transcode", method = GET)
    public Map<String, String> get(@Valid TranscodeRequest req) throws DecoderException {
        Charset inputCharset = Charset.forName(req.getInputEncoding());
        Charset outputCharset = Charset.forName(req.getOutputEncoding());

        byte[] inputBytes = Hex.decodeHex(req.getHexBytes().toCharArray());
        String inputString = new String(inputBytes, inputCharset);
        byte[] outputBytes = inputString.getBytes(outputCharset);
        String outputString = new String(Hex.encodeHex(outputBytes, false));
        return Collections.singletonMap("output", outputString);
    }
}
