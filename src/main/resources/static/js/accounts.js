var itemToUpdate = {};

function saveAccount(isNew) {
    var accountsGrid = $("#accountsGrid");
    if(isNew){
        accountsGrid.jsGrid("insertItem", $("#account").serializeJSON());
    } else {
        accountsGrid.jsGrid("updateItem", itemToUpdate, $("#account").serializeJSON());
    }
    //itemToUpdate = {}; // Uncomment once finalizing the functionality
    $("#accountDialog").modal("hide");
}

function openAccountModal(mode, item){
    var actDlg = $("#accountDialog");
    var addAct = $("#addAccount");
    var vehicleNumber = $('#vehicleNumber');
	if(mode === 'Edit'){
		$('input[name="accountId"]').val(item.accountId);
		$('input[name="accountHolderName"]').val(item.accountHolderName);
		$('input[name="accountNumber"]').val(item.accountNumber);
		$('input[name="ifscCode"]').val(item.ifscCode);
        vehicleNumber.html("").append('<option value="'+item.vehicleNumber+'" selected>'+item.vehicleNumber+'</option');
        itemToUpdate = item;
        addAct.attr("onclick", "saveAccount(false)");
        actDlg.find('.modal-title').text("Edit Account");
	} else {
        $("#account")[0].reset();
		ajaxGet('vehicleRCNumbers').success(function(response){
            vehicleNumber.html("").append('<option value="-1" disabled selected>--Select--</option>');
			$.each(response, function(i, val){
                vehicleNumber.append('<option value="'+val.rcNumber+'">'+val.rcNumber+'</option');
			});
		});
        actDlg.find('.modal-title').text("Add Account");
        addAct.attr("onclick", "saveAccount(true);");
	}
    actDlg.modal('show');
}

function initAccounts() {
    $("#accountsGrid").jsGrid({
        width: "100%",
        height: "400px",
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
            updateItem: function (item) { //This is a callback function
                console.log(item);
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
        	{title:'Account ID', name: "accountId", type: "text"},
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
                        .attr("onclick","openAccountModal('Add');")
                        .text("Add");
                }
            }
        ]
    });
}