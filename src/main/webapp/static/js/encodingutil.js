$(function() {
    var htmlForErrors = function(errors) {
        var html = '<div class="alert alert-danger alert-dismissible" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><ul>';
        for (var i = 0; i < errors.length; i++) {
            html += '<li>' + errors[i] + '</li>';
        }
        html += '</ul></div>';
        return html;
    };

    var handleResponse = function(rsp, elem) {
        if (rsp.hasOwnProperty('output')) {
            $(elem).val(rsp['output'])
        } else if (rsp.hasOwnProperty('errors')) {
            $('#errorbox').append(htmlForErrors(rsp['errors']));
        }
    };

    $('#decodeSubmit').click(function() {
        $('#errorbox').empty();
        $.getJSON('/api/decode', {
            'inputEncoding': $('#decodeEncoding').val(),
            'hexBytes': $('#decodeHex').val()
        }, function(rsp) {
            handleResponse(rsp, '#decodeText');
        })
    });

    $('#encodeSubmit').click(function() {
        $('#errorbox').empty();
        $.getJSON('/api/encode', {
            'outputEncoding': $('#encodeEncoding').val(),
            'string': $('#encodeText').val()
        }, function(rsp) {
            handleResponse(rsp, '#encodeHex');
        });
    });

    $('#transcodeSubmit').click(function() {
        $('#errorbox').empty();
        $.getJSON('/api/transcode', {
            'inputEncoding': $('#transcodeInputEncoding').val(),
            'outputEncoding': $('#transcodeOutputEncoding').val(),
            'hexBytes': $('#transcodeInputHex').val()
        }, function(rsp) {
            handleResponse(rsp, '#transcodeOutputHex');
        })
    })
});
