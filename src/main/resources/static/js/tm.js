function ajaxPost(url, data) {
    return $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        beforeSend: function () {
            $("#content").modal('show');
        },
        complete: function () {
            $("#content").modal('hide');
        }
    });
}

function ajaxGet(url) {
    return $.ajax({
        type: "GET",
        url: url,
        beforeSend: function () {
            $("#content").modal('show');
        },
        complete: function () {
            $("#content").modal('hide');
        }
    });
}

function ajaxPostFileData(url, postData){
	return $.ajax({
        type: "POST",
        url: url,
        data: postData,
        dataType: "json",
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        beforeSend: function () {
            $("#content").modal('show');
        },
        complete: function () {
            $("#content").modal('hide');
        }
    });
}

function hideContent() {
    $("#accounts").hide();
    $("#vehicles").hide();
    $("#drivers").hide();
    $("#reports").hide();
}

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
        $("#driverDialog").load('pages/adddriver.html');
    },
    showreports: function () {
    },
    showvehicles: function () {
        ajaxGet('vehicles').success(function (response) {
            $("#vehiclesGrid").jsGrid("option", "data", response);
        });
        $("#vehicleDialog").load('pages/addvehicle.html');
    }
};

$(function () {
    $("#content").modal('show');
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
    initVehicles();
    $("#content").modal('hide');
});