package us.abbies.b.encodingutil;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.abbies.b.encodingutil.validation.CanDecodeCharset;
import us.abbies.b.encodingutil.validation.CanEncodeCharset;
import us.abbies.b.encodingutil.validation.HexString;
import us.abbies.b.encodingutil.validation.NotEmpty;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Validated
@RequestMapping(value = "/api", method = GET)
public class WebAppController {
    @ExceptionHandler
    public Map<String, List<String>> exception(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
            errors.add(cv.getMessage());
        }
        return Collections.singletonMap("errors", errors);
    }

    @ExceptionHandler
    public Map<String, List<String>> exception(Exception ex) {
        return Collections.singletonMap(
                "errors",
                Collections.singletonList(ex.getMessage()));
    }

    @RequestMapping("/transcode")
    public Map<String, String> transcode(@CanDecodeCharset String inputEncoding,
                                         @CanEncodeCharset String outputEncoding,
                                         @HexString String hexBytes) throws DecoderException {
        Charset inputCharset = Charset.forName(inputEncoding);
        Charset outputCharset = Charset.forName(outputEncoding);

        byte[] inputBytes = Hex.decodeHex(hexBytes.toCharArray());
        String inputString = new String(inputBytes, inputCharset);
        byte[] outputBytes = inputString.getBytes(outputCharset);
        String outputString = new String(Hex.encodeHex(outputBytes, false));
        return Collections.singletonMap("output", outputString);
    }

    @RequestMapping("/decode")
    public Map<String, String> decode(@CanDecodeCharset String inputEncoding,
                                      @HexString String hexBytes) throws DecoderException {
        Charset inputCharset = Charset.forName(inputEncoding);
        byte[] inputBytes = Hex.decodeHex(hexBytes.toCharArray());
        String outputString = new String(inputBytes, inputCharset);
        return Collections.singletonMap("output", outputString);
    }

    @RequestMapping("/encode")
    public Map<String, String> encode(@CanEncodeCharset String outputEncoding,
                                      @NotEmpty String string) {
        Charset outputCharset = Charset.forName(outputEncoding);
        byte[] outputBytes = string.getBytes(outputCharset);
        String outputString = new String(Hex.encodeHex(outputBytes, false));
        return Collections.singletonMap("output", outputString);
    }
}
