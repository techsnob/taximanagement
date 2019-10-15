function saveVehicle(isNew) {
    //$("#vehiclesGrid").jsGrid(isNew ? "insertItem" : "updateItem", $("#vehicle").serializeJSON());
    ajaxPostFileData("putvehicle",new FormData($("#vehicle")[0]));
    $("#vehicleDialog").modal("hide");
}

function openVehicleModal(mode, item){
	if(mode == 'Edit'){
		$('input[name="vehicleId"]').val(item.vehicleId);
		$('input[name="rcNumber"]').val(item.rcNumber);
		$('input[name="vehicleType"]').val(item.vehicleType);
		$("#insertvehicle").attr("onclick", "saveVehicle(false);");
		$("#vehicleDialog").find('.modal-title').text("Edit Vehicle");
		$("#vehicleDialog").modal('show');
	} else {
		$('input[name="vehicleId"]').val("");
		$("#insertvehicle").attr("onclick", "saveVehicle(true);");
	}
	$("#vehicleDialog").modal('show');
}

function initVehicles() {
    $("#vehiclesGrid").jsGrid({
        width: "100%",
        height: "400px",
        heading: true,
        sorting: true,
        noDataContent: "No Vehicles added yet!",
        deleteConfirm: function(item) {
            if(confirm("The vehicle " + item.rcNumber + " will be removed. Are you sure?")){
            	var response = ajaxPost('removevehicle', item.vehicleId)
            }
            return "The vehicle "+item.rcNumber+" has been removed!";
        },
        rowDoubleClick: function(args) {
        	openVehicleModal('Edit', args.item);
        },
        fields: [
        	{name: "vehicleId", type: "text", visible:false},
            {title: "Vehicle RC No", name: "rcNumber", type: "text"},
            {title: "Vehicle Type", name: "vehicleType", type: "text"},
            {name: "rcFileType", type: "text", visible: false},
            {title: "RC File", itemTemplate: function(_, item) {
                    return $("<a>")
                        .attr("href", 'media?fileName=rc_file&moduleName=vehicles&contentType='+item.rcFileType+'&columnId='+item.vehicleId)
                        .attr("target", "_blank")
                        .text("Link");
            }},
            {name: "fitnessType", type: "text", visible: false},
            {title: "Fitness", itemTemplate: function(_, item) {
                    return $("<a>")
                        .attr("href", 'media?fileName=fitness&moduleName=vehicles&contentType='+item.fitnessType+'&columnId='+item.vehicleId)
                        .attr("target", "_blank")
                        .text("Link");
                }},
            {name: "insuranceType", type: "text", visible: false},
            {title: "Insurance", itemTemplate: function(_, item) {
                    return $("<a>")
                        .attr("href", 'media?fileName=insurance&moduleName=vehicles&contentType='+item.insuranceType+'&columnId='+item.vehicleId)
                        .attr("target", "_blank")
                        .text("Link");
                }},
            {name: "taxsheetType", type: "text", visible: false},
            {title: "Tax Sheet", itemTemplate: function(_, item) {
                    return $("<a>")
                        .attr("href", 'media?fileName=taxsheet&moduleName=vehicles&contentType='+item.taxsheetType+'&columnId='+item.vehicleId)
                        .attr("target", "_blank")
                        .text("Link");
                }},
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,
                headerTemplate: function () {
                    return $("<button>")
                        .attr("type", "button")
                        .attr("class", "btn btn-primary")
                        .attr("onclick","openVehicleModal();")
                        .text("Add");
                }
            }
        ]
    });
}