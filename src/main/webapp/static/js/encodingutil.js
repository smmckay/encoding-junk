$(function() {
    var successFactory = function(textElem, alertDiv) {
        return function(rsp) {
            if (rsp.hasOwnProperty('output')) {
                $(textElem).val(rsp.output);
            } else if (rsp.hasOwnProperty('message')) {
                $(alertDiv).html('<p>' + rsp.message + '</p>').show();
            } else if (rsp.hasOwnProperty('errors')) {
                $.each(rsp.errors, function (idx, message) {
                    $(alertDiv).append('<p>' + message + '</p>')
                });
                $(alertDiv).show();
            }
        }
    };

    var errorFactory = function(alertDiv) {
        return function() {
            $(alertDiv).html('<p>Server error</p>').show();
        }
    };

    $('#decodeSubmit').click(function() {
        $('#decodeAlert').empty().hide();
        $.ajax({
            dataType: "json",
            url: 'api/decode',
            data: {
                'inputEncoding': $('#decodeEncoding').val(),
                'hexBytes': $('#decodeHex').val()
            },
            success: successFactory('#decodeText', '#decodeAlert'),
            error: errorFactory('#decodeAlert')
        });
    });

    $('#encodeSubmit').click(function() {
        $('#encodeAlert').empty().hide();
        $.ajax({
            dataType: "json",
            url: 'api/encode',
            data: {
                'outputEncoding': $('#encodeEncoding').val(),
                'string': $('#encodeText').val()
            },
            success: successFactory('#encodeHex', '#encodeAlert'),
            error: errorFactory('#encodeAlert')
        });
    });

    $('#transcodeSubmit').click(function() {
        $('#transcodeAlert').empty().hide();
        $.ajax({
            dataType: "json",
            url: 'api/transcode',
            data: {
                'inputEncoding': $('#transcodeInputEncoding').val(),
                'outputEncoding': $('#transcodeOutputEncoding').val(),
                'hexBytes': $('#transcodeInputHex').val()
            },
            success: successFactory('#transcodeOutputHex', '#transcodeAlert'),
            error: errorFactory('#transcodeAlert')
        });
    })
});
