function saveDriver(isNew) {
    //var json = $("#driver").serializeJSON();
    // var json = {
    //     driverName: json.driverName,
    //     phoneNumber: json.phoneNumber,
    //     licenseNumber: json.licenseNumber,
    //     badgeNumber: json.badgeNumber,
    // };

    // var address = {
    //     houseName: json.houseName,
    //     streetName: json.streetName,
    //     landmark: json.landmark,
    //     area: json.area,
    //     pincode: json.pincode,
    //     village: json.village,
    //     district: json.district,
    //     state: json.state,
    //     country: json.country
    // };

    // var container = {
    //     driver: driver,
    //     address: address
    // };
    //$("#driversGrid").jsGrid(isNew ? "insertItem" : "updateItem", json.driver);
    // ajaxPost('insertDriverDetails', json).success(function (response) {
    //     alert(response.firstName + " added sucessfully!");
    // });

    $.ajax({
        type: "POST",
        url: "insertDriverDetails",
        data: new FormData($("#driver")[0]),
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
    $("#driverDialog").modal("hide");
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
            {title: "Driver Name", name: "firstName", type: "text", width: 50},
            {title: "Driver Name", name: "lastName", type: "text", width: 50},
            {title: "Phone Number", name: "phoneNumber", type: "text", width: 50},
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,
                headerTemplate: function () {
                    return $("<button>")
                        .attr("type", "button")
                        .attr("class", "btn btn-primary")
                        .attr("data-toggle", "modal")
                        .attr("data-target", "#driverDialog")
                        .text("Add");
                }
            }
        ]
    });
}