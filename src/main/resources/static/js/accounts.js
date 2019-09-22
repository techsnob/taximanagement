function saveAccount(json, isNew) {
    $("#accountsGrid").jsGrid(isNew ? "insertItem" : "updateItem", json);
    ajaxPost('insertAccountDetails', json).success(function (response) {
        alert(response.accountHolderName + " added sucessfully!");
    });
    $("#accountDialog").dialog("close");
}

function showAccountDialog(dialogType) {
    $("#accountDialog").dialog("option", "title", "Account")
        .dialog("open");
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
                headerTemplate: function() {
                    return $("<button>").attr("type", "button").attr("class","btn btn-primary").text("Add")
                        .on("click", function () {
                            showAccountDialog("Add");
                        });
                }
            }
        ]
    });

    $("#addAccount").click(function () {
        saveAccount($("#account").serializeJSON(), true);
    });

    $("#accountDialog").dialog({
        autoOpen: false,
        width: 400,
        resizable: false,
        height: "auto",
        modal: true,
        close: function() {
            // $("#detailsForm").find(".error").removeClass("error");
        }
    });
}