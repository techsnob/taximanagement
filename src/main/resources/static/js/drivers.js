function saveClient(json, isNew) {
    var driver = {
        driverName: json.driverName,
        phoneNumber: json.phoneNumber,
        licenseNumber: json.licenseNumber,
        badgeNumber: json.badgeNumber,
    };

    var address = {
        houseName: json.houseName,
        streetName: json.streetName,
        landmark: json.landmark,
        area: json.area,
        pincode: json.pincode,
        village: json.village,
        district: json.district,
        state: json.state,
        country: json.country
    };

    var container = {
        driver: driver,
        address: address
    };
    $("#driversGrid").jsGrid(isNew ? "insertItem" : "updateItem", json.driver);
    ajaxPost('insertDriverDetails', container).success(function (response) {

        alert(response.driverName + " added sucessfully!");
    });
    $("#detailsDialog").dialog("close");
}

function showDetailsDialog(dialogType) {
    $("#detailsDialog").dialog("option", "title", "Driver")
        .dialog("open");
}

function initDrivers() {
    $("#driversGrid").jsGrid({
        width: "100%",
        height: "400px",
        heading: true,
        sorting: true,
        noDataContent: "No Drivers added yet!",
        fields: [
            {name: "driverId", type: "text", visible: false},
            {title: "Driver Name", name: "driverName", type: "text", width: 50},
            {title: "Phone Number", name: "phoneNumber", type: "text", width: 50},
            {title: "License Number", name: "licenseNumber", type: "text", width: 50},
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,
                headerTemplate: function () {
                    return $("<button>").attr("type", "button").text("Add")
                        .on("click", function () {
                            showDetailsDialog("Add");
                        });
                }
            }
        ]
    });

    $("#detailsDialog").dialog({
        autoOpen: false,
        width: 400,
        resizable: false,
        height: "auto",
        modal: true,
        close: function () {
            $("#detailsForm").find(".error").removeClass("error");
        }
    });

    $("#insertDriver").click(function () {
        saveClient($("#driver").serializeJSON(), true);
    });

}