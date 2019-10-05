function saveDriver(isNew) {
    $("#driversGrid").jsGrid(isNew ? "insertItem" : "updateItem", $("#driver").serializeJSON());

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

function openDriverModal(mode, item){
	if(mode == 'Edit'){
		$('input[name="firstName"]').val(item.firstName);
		$('input[name="lastName"]').val(item.lastName);
		$('input[name="phoneNumber"]').val(item.phoneNumber);
		$("#insertDriver").attr("onclick", "saveDriver(false);");
		$("#driverDialog").find('.modal-title').text("Edit Driver");
		$("#driverDialog").modal('show');
	}
	$("#insertDriver").attr("onclick", "saveDriver(true);");
	$("#driverDialog").modal('show');
}

function initDrivers() {
    $("#driversGrid").jsGrid({
        width: "100%",
        height: "400px",
        heading: true,
        sorting: true,
        noDataContent: "No Drivers added yet!",
        deleteConfirm: function(item) {
            if(confirm("The driver " + item.firstName + " will be removed. Are you sure?")){
            	var response = ajaxPost('removedriver', item)
            }
            return "The driver "+item.firstName+" has been removed!";
        },
        rowClick: function(args) {
        	openDriverModal('Edit', args.item);
        },
        fields: [
            {name: "driverId", type: "text", visible: false},
            {title: "First Name", name: "firstName", type: "text", width: 50},
            {title: "Last Name", name: "lastName", type: "text", width: 50},
            {title: "Phone Number", name: "phoneNumber", type: "text", width: 50},
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,
                headerTemplate: function () {
                    return $("<button>")
                        .attr("type", "button")
                        .attr("class", "btn btn-primary")
                        //.attr("data-toggle", "modal")
                        //.attr("data-target", "#driverDialog")
                        .attr("onclick","openDriverModal();")
                        .text("Add");
                }
            }
        ]
    });
}