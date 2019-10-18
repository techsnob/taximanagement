function calculateTripRate(trip) {
    var name = "input[name="+'"'+trip+"[total]"+'"'+"]";
    var triprate = "input[name="+'"'+trip+"rate"+'"]';
    $(name).val($("input[name="+'"'+trip+'"]').val() * $(triprate).val());

    var rates = ["shorttrip","mediumtrip","longtrip"];
    var amount=0;
    $.each(rates, function (i, val) {
        var element = $("input[name="+'"'+val+"[total]"+'"'+"]").val();
        var value = parseInt(element);
        amount = amount + (isNaN(value) ? 0 : value);
    });
    $('input[name="triptotal"]').val(amount);
}

function initTrip() {
    $.getJSON("triprates.json", function(json) {
        $('input[name="shorttriprate"]').val(json.shorttriprate);
        $('input[name="mediumtriprate"]').val(json.mediumtriprate);
        $('input[name="longtriprate"]').val(json.longtriprate);
    });


}