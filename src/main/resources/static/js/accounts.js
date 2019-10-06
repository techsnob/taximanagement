function saveAccount(isNew) {
    $("#accountsGrid").jsGrid(isNew ? "insertItem" : "updateItem", $("#account").serializeJSON());
    ajaxPost('insertAccount', $("#account").serializeJSON()).success(function (response) {
        alert(response.accountHolderName + " added sucessfully!");
    });
    $("#accountDialog").modal("hide");
}

function openAccountModal(mode, item){
	if(mode == 'Edit'){
		$('input[name="accountId"]').val(item.accountId);
		$('input[name="accountHolderName"]').val(item.accountHolderName);
		$('input[name="accountNumber"]').val(item.accountNumber);
		$('input[name="ifscCode"]').val(item.ifscCode);
		$("#addAccount").attr("onclick", "saveAccount(false);");
		$("#accountDialog").find('.modal-title').text("Edit Account");
	} else {
		$("#addAccount").attr("onclick", "saveAccount(true);");
	}
	$("#accountDialog").modal('show');
}

function initAccounts() {
    $("#accountsGrid").jsGrid({
        width: "100%",
        height: "400px",
        heading: true,
        sorting: true,
        noDataContent: "No Accounts added yet!",
        deleteConfirm: function(item) {
            if(confirm("The Account " + item.accountHolderName + " will be removed. Are you sure?")){
            	var response = ajaxPost('removeAccount', item.accountId)
            }
            return "The Account "+item.accountHolderName+" has been removed!";
        },
        rowClick: function(args) {
        	openAccountModal('Edit', args.item);
        },
        fields: [
        	{name: "accountId", type: "text", visible: false},
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
                        //.attr("data-toggle", "modal")
                        //.attr("data-target", "#accountDialog")
                        .attr("onclick","openAccountModal();")
                        .text("Add");
                }
            }
        ]
    });
}