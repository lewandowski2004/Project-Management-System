$('#totalNetWorth').blur(function () {
    if ($(this).val().length > 0) {
        var netto = parseFloat($('#totalNetWorth').val());
        var vat = (parseFloat($('#vatSelected').val()) + 100) / 100;
        var sum = (netto * vat).toFixed(2);
        $('#totalGrossValue').val(sum)
    } else {
        $('#totalGrossValue').val(0)
    }
});
$('#vatSelected').change(function () {
    if ($(this).val().length > 0) {
        var netto = parseFloat($('#totalNetWorth').val());
        var vat = (parseFloat($('#vatSelected').val()) + 100) / 100;
        var sum = (netto * vat).toFixed(2);
        $('#totalGrossValue').val(sum)
    } else {
        $('#totalGrossValue').val(0)
    }
});
$(".radio").click(function () {
    console.log('weszloo');
    var $checked = $radios.filter(function() {
        return $(this).prop('checked');
    });
    if($checked.val() === 'other'){
        $("other").prop('disabled', false);
    }else{
        $("other").prop('disabled', true);
    }

});


