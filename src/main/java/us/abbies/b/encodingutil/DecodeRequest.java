package us.abbies.b.encodingutil;

import us.abbies.b.encodingutil.validation.CanDecodeCharset;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class DecodeRequest {
    private String inputEncoding;
    private String hexBytes;

    public DecodeRequest() {
    }

    @CanDecodeCharset
    public String getInputEncoding() {
        return inputEncoding;
    }

    public void setInputEncoding(String inputEncoding) {
        this.inputEncoding = inputEncoding;
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
