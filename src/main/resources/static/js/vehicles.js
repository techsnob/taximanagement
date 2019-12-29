function saveVehicle(isNew) {
    var vehiclesGrid = $("#vehiclesGrid");
    if(isNew){
        vehiclesGrid.jsGrid("insertItem", new FormData($("#vehicle")[0]));
    } else {
        vehiclesGrid.jsGrid("updateItem", itemToUpdate, $("#vehicle").serializeJSON());
    }
    $("#vehicleDialog").modal("hide");
    //$("#vehiclesGrid").jsGrid("loadData");
    $("#vehiclesLink").click();
}

function openVehicleModal(mode, item){
    var vehicleDialog = $("#vehicleDialog"), insertVehicle = $("#insertvehicle");
	if(mode === 'Edit'){
		$('input[name="vehicleId"]').val(item.vehicleId);
		$('input[name="rcNumber"]').val(item.rcNumber);
		$("input[name=vehicleType][value="+item.vehicleType+"]").prop('checked', true);
        $('input[name="rcFile"]').hide();
        $('input[name="fitness"]').hide();
        $('input[name="insurance"]').hide();
        $('input[name="taxsheet"]').hide();
        insertVehicle.attr("onclick", "saveVehicle(false);");
        vehicleDialog.find('.modal-title').text("Edit Vehicle");
        vehicleDialog.modal('show');
	} else {
        $("#vehicle")[0].reset();
        $('input[name="rcFile"]').show();
        $('input[name="fitness"]').show();
        $('input[name="insurance"]').show();
        $('input[name="taxsheet"]').show();
        insertVehicle.attr("onclick", "saveVehicle(true);");
	}
    vehicleDialog.modal('show');
}

function initVehicles() {
    $("#vehiclesGrid").jsGrid({
        width: "100%",
        height: "400px",
        heading: true,
        sorting: true,
        noDataContent: "No Vehicles added yet!",
        controller: {
            insertItem: function (item) {
                return ajaxPostFileData("putvehicle", item);
            },
            updateItem: function (item) { //This is a callback function
                return ajaxPost("updatevehicle", item);
            }
        },
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
        	{title: 'Vehicle ID', name: "vehicleId", type: "text"},
            {title: "Vehicle RC No", name: "rcNumber", type: "text"},
            {title: "Vehicle Type", name: "vehicleType", type: "text"},
            {name: "rcFileType", type: "text", visible: false},
            {title: "RC File", itemTemplate: function(_, item) {
                    if(item.rcFile === null || item.rcFile ===''){
                        return showUploadMediaHtml(item, 'rc_file', 'vehicles',item.vehicleId);
                    } else {
                        return handleMedia(item, 'rc_file', 'vehicles',item.rcFileType, item.vehicleId );
                    }
            }},
            {name: "fitnessType", type: "text", visible: false},
            {title: "Fitness", itemTemplate: function(_, item) {
                    if(item.fitness === null || item.fitness ===''){
                        return showUploadMediaHtml(item, 'fitness', 'vehicles',item.vehicleId);
                    } else {
                        return handleMedia(item, 'fitness', 'vehicles',item.fitnessType, item.vehicleId );
                    }
                }},
            {name: "insuranceType", type: "text", visible: false},
            {
                title: "Insurance", itemTemplate: function (_, item) {
                    if(item.insurance === null || item.insurance ===''){
                        return showUploadMediaHtml(item, 'insurance', 'vehicles',item.vehicleId);
                    } else {
                        return handleMedia(item, 'insurance', 'vehicles',item.insuranceType, item.vehicleId );
                    }
                }
            },
            {name: "taxsheetType", type: "text", visible: false},
            {
                title: "Tax Sheet", itemTemplate: function (_, item) {
                    if(item.taxsheet === null || item.taxsheet ===''){
                        return showUploadMediaHtml(item, 'taxsheet', 'vehicles',item.vehicleId);
                    } else {
                        return handleMedia(item, 'taxsheet', 'vehicles', item.taxsheetType, item.vehicleId);
                    }
                }
            },
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