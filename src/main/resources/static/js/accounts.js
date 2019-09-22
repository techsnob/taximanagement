function saveAccount(isNew) {
    var json = $("#account").serializeJSON();
    $("#accountsGrid").jsGrid(isNew ? "insertItem" : "updateItem", json);
    ajaxPost('insertAccountDetails', json).success(function (response) {
        alert(response.accountHolderName + " added sucessfully!");
    });
    $("#accountDialog").modal("hide");
}

function initAccounts() {
    $("#accountsGrid").jsGrid({
        width: "100%",
        height: "400px",
        heading: true,
        sorting: true,
        noDataContent: "No Accounts added yet!",
        fields: [
            {title: "Account holder name", name: "accountHolderName", type: "text", width: 50},
            {title: "Account Number", name: "accountNumber", type: "text", width: 50},
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
                        .attr("data-target", "#accountDialog")
                        .text("Add");
                }
            }
        ]
    });
}