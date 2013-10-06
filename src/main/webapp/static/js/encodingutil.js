$(function() {
    $('#decodeSubmit').click(function() {
        $.getJSON('/api/decode', {
            'inputEncoding': $('#decodeEncoding').val(),
            'hexBytes': $('#decodeHex').val()
        }, function(rsp) {
            $('#decodeText').val(rsp.output);
        })
    });

    $('#encodeSubmit').click(function() {
        $.getJSON('/api/encode', {
            'outputEncoding': $('#encodeEncoding').val(),
            'string': $('#encodeText').val()
        }, function(rsp) {
            $('#encodeHex').val(rsp.output);
        });
    });

    $('#transcodeSubmit').click(function() {
        $.getJSON('/api/transcode', {
            'inputEncoding': $('#transcodeInputEncoding').val(),
            'outputEncoding': $('#transcodeOutputEncoding').val(),
            'hexBytes': $('#transcodeInputHex').val()
        }, function(rsp) {
            $('#transcodeOutputHex').val(rsp.output);
        })
    })
});
