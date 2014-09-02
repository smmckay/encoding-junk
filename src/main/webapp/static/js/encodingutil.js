$(function() {
    var htmlForErrors = function(errors) {
        var html = '<div class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>';
        for (var i = 0; i < errors.length; i++) {
            html += '<p>' + errors[i] + '</p>';
        }
        html += '</div>';
        return html;
    };

    var successFactory = function(textElem, alertDiv) {
        return function(rsp) {
            if (rsp.hasOwnProperty('output')) {
                $(textElem).val(rsp['output']);
            } else if (rsp.hasOwnProperty('errors')) {
                $(alertDiv).append(htmlForErrors(rsp['errors']));
            }
        }
    };

    var errorFactory = function(alertDiv) {
        return function() {
            $(alertDiv).append(htmlForErrors(['Server error']));
        }
    };

    $('#decodeSubmit').click(function() {
        $('#decodeAlert').empty();
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
        $('#encodeAlert').empty();
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
        $('#transcodeAlert').empty();
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
