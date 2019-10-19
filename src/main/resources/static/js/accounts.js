function saveAccount(isNew) {
    $("#accountsGrid").jsGrid(isNew ? "insertItem" : "updateItem", $("#account").serializeJSON());
    $("#accountDialog").modal("hide");
}

function openAccountModal(mode, item){
	if(mode == 'Edit'){
		$('input[name="accountId"]').val(item.accountId);
		$('input[name="accountHolderName"]').val(item.accountHolderName);
		$('input[name="accountNumber"]').val(item.accountNumber);
		$('input[name="ifscCode"]').val(item.ifscCode);
        $('#vehicleNumber').append('<option value="'+item.vehicleNumber+'" selected>'+item.vehicleNumber+'</option');
		$("#addAccount").attr("onclick", "saveAccount(false);");
		$("#accountDialog").find('.modal-title').text("Edit Account");
	} else {
		ajaxGet('vehicleRCNumbers').success(function(response){
			$("#vehicleNumber").html("").append('<option value="-1" disabled selected>--Select--</option>');
			$.each(response, function(i, val){
				$("#vehicleNumber").append('<option value="'+val.rcNumber+'">'+val.rcNumber+'</option');
			});
		});
		$("#addAccount").attr("onclick", "saveAccount(true);");
	}
	$("#accountDialog").modal('show');
}

function initAccounts() {
    $("#accountsGrid").jsGrid({
        width: "100%",
        height: "100%",
        heading: true,
        sorting: true,
        autoload: true,
        paging: true,
        pageLoading: false,
        pageSize: 2,
        pageIndex: 1,
        controller: {
            loadData: function (filter) {
                return ajaxGet('accounts');
            },
            insertItem: function (item) {
                return ajaxPost('insertAccount', item);
            },
            updateItem: function (item) {
                return ajaxPost('insertAccount', item);
            },
            deleteItem: function (item) {
                return ajaxPost('removeAccount', item.accountId);
            }
        },
        
        noDataContent: "No Accounts added yet!",
        deleteConfirm: function(item) {
            confirm("The Account " + item.accountHolderName + " will be removed. Are you sure?");
        },
        rowClick: function(args) {
        	openAccountModal('Edit', args.item);
        },
        fields: [
        	{name: "accountId", type: "text", visible: false},
        	{title: "Account holder name", name: "accountHolderName", type: "text"},
            {title: "Account Number", name: "accountNumber", type: "text"},
            {title: "IFSC code", name: "ifscCode", type: "text"},
            {title: "Vehicle Number", name: "vehicleNumber", type: "text"},
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