function ajaxPost(url, data) {
    return $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json"
    });
}

function ajaxGet(url) {
    return $.ajax({
        type: "GET",
        url: url
    });
}

function hideContent() {
    $("#accounts").hide();
    $("#vehicles").hide();
    $("#drivers").hide();
    $("#reports").hide();
}
var GRID_OPTIONS={
    width: "100%",
    height: "400px",
    heading: true,
    sorting: true,
};
var GLOBAL_FUNCTIONS = {
    showaccounts: function () {
        ajaxGet('accounts').success(function (response) {
            $("#accountsGrid").jsGrid("option", "data", JSON.parse(response));
        });
        $("#accountDialog").load('pages/addaccount.html');
    },
    showdrivers: function () {
        ajaxGet('drivers').success(function (response) {
            $("#driversGrid").jsGrid("option", "data", response);
        });
    },
    showreports: function () {

    },
    showvehicles: function () {

    }
};

$(function () {
    hideContent();
    $("[data]").click(function () {
        hideContent();
        $("#" + this.getAttribute("data")).show();
        var func = "show" + this.getAttribute("data");
        GLOBAL_FUNCTIONS[func]();
    });
    initDrivers();
    initAccounts();
    initTrip();
});