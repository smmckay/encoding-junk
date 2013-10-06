package us.abbies.b.encodingutil;

import us.abbies.b.encodingutil.validation.CanDecodeCharset;
import us.abbies.b.encodingutil.validation.CanEncodeCharset;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TranscodeRequest {
    private String inputEncoding;
    private String outputEncoding;
    private String hexBytes;

    public TranscodeRequest() {
    }

    @CanDecodeCharset
    public String getInputEncoding() {
        return inputEncoding;
    }

    public void setInputEncoding(String inputEncoding) {
        this.inputEncoding = inputEncoding;
    }

    @CanEncodeCharset
    public String getOutputEncoding() {
        return outputEncoding;
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    @Pattern(regexp = "([A-Fa-f0-9][A-Fa-f0-9])+")
    @NotNull
    public String getHexBytes() {
        return hexBytes;
    }

    public void setHexBytes(String hexBytes) {
        this.hexBytes = hexBytes;
    }
}
