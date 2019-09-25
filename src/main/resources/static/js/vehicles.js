function saveVehicle(isNew) {
    var json = $("#vehicle").serializeJSON();
    $("#vehiclesGrid").jsGrid(isNew ? "insertItem" : "updateItem", json);
    ajaxPost('insertAccountDetails', json).success(function (response) {
        alert(response.accountHolderName + " added sucessfully!");
    });
    $("#vehiclesGrid").modal("hide");
}

function initVehicles() {
    $("#vehiclesGrid").jsGrid({
        width: "100%",
        height: "400px",
        heading: true,
        sorting: true,
        noDataContent: "No Vehicles added yet!",
        fields: [
            {title: "Account holder name", name: "vehicleType", type: "text", width: 50},
            {title: "Account Number", name: "rcNumber", type: "text", width: 50},
            {title: "IFSC code", name: "ifscCode", type: "text", width: 50},
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,
                headerTemplate: function () {
                    return $("<button>")
                        .attr("type", "button")
                        .attr("class", "btn btn-primary")
                        .attr("data-toggle", "modal")
                        .attr("data-target", "#vehicleDialog")
                        .text("Add");
                }
            }
        ]
    });
}