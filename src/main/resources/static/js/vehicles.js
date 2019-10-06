function saveVehicle(isNew) {
    $("#vehiclesGrid").jsGrid(isNew ? "insertItem" : "updateItem", $("#vehicle").serializeJSON());
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
        rowClick: function(args) {
        	openVehicleModal('Edit', args.item);
        },
        fields: [
        	{name: "vehicleId", type: "text", visible:false},
        	{title: "Vehicle RC No", name: "rcNumber", type: "text", width: 50},
        	{title: "Vehicle Type", name: "vehicleType", type: "text", width: 50},
            {
                type: "control",
                modeSwitchButton: false,
                editButton: false,
                headerTemplate: function () {
                    return $("<button>")
                        .attr("type", "button")
                        .attr("class", "btn btn-primary")
                        //.attr("data-toggle", "modal")
                        //.attr("data-target", "#vehicleDialog")
                        .attr("onclick","openVehicleModal();")
                        .text("Add");
                }
            }
        ]
    });
}