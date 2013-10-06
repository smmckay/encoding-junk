package us.abbies.b.encodingutil;

import us.abbies.b.encodingutil.validation.CanEncodeCharset;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EncodeRequest {
    private String outputEncoding;
    private String string;

    public EncodeRequest() {
    }

    @CanEncodeCharset
    public String getOutputEncoding() {
        return outputEncoding;
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    @NotNull
    @Size(min = 1)
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
