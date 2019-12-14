var itemToUpdate = {};
function saveDriver(isNew) {
    var driversGrid = $("#driversGrid"), driverForm = new FormData($("#driver")[0]);
    if(isNew){
        driversGrid.jsGrid("insertItem", driverForm);
    } else {
        driversGrid.jsGrid("updateItem", itemToUpdate , driverForm);
    }
    $("#driverDialog").modal("hide");
}

function openDriverModal(mode, item){
    var driverDialog = $("#driverDialog");
	if(mode === 'Edit'){
		$('input[name="driverId"]').val(item.driverId);
		$('input[name="firstName"]').val(item.firstName);
		$('input[name="lastName"]').val(item.lastName);
		$('input[name="phoneNumber"]').val(item.phoneNumber);
        $('input[name="license"]').hide();
        $('input[name="aadhaar"]').hide();
        itemToUpdate = JSON.parse(JSON.stringify(item));
        delete itemToUpdate['aadhaar'];
        delete itemToUpdate['fitness'];
		$("#insertDriver").attr("onclick", "saveDriver(false);");
        driverDialog.find('.modal-title').text("Edit Driver");
        driverDialog.modal('show');
	} else {
		$("#insertDriver").attr("onclick", "saveDriver(true);");
	}
    driverDialog.modal('show');
}

function initDrivers() {
    $("#driversGrid").jsGrid({
        width: "100%",
        height: "400px",
        heading: true,
        sorting: true,
        noDataContent: "No Drivers added yet!",
        controller: {
            insertItem: function (item) {
                return ajaxPostFileData("insertDriver", item);
            },
            updateItem: function (item) { //This is a callback function
                return ajaxPostFileData("updateDriver", item);
            }
        },
        deleteConfirm: function(item) {
            if(confirm("The driver " + item.firstName + " will be removed. Are you sure?")){
            	var response = ajaxPost('removedriver', item)
            }
            return "The driver "+item.firstName+" has been removed!";
        },
        rowDoubleClick: function(args) {
        	openDriverModal('Edit', args.item);
        },
        fields: [
            {title:'Driver ID',  name: "driverId", type: "text"},
            {title: "First Name", name: "firstName", type: "text"},
            {title: "Last Name", name: "lastName", type: "text"},
            {title: "Phone Number", name: "phoneNumber", type: "text"},
            {name: "aadhaar_type", type: "text", visible: false},
            { title: "Aadhaar", itemTemplate: function(_, item) {
                    if(item.aadhaar === null || item.aadhaar ==='' || item.aadhaar === undefined){
                        return showUploadMediaHtml(item, 'aadhaar', 'drivers',item.driverId);
                    } else {
                        return handleMedia(item, 'aadhaar', 'drivers',item.aadhaar_type, item.driverId );
                    }
              	}
            },
            {name: "license_type", type: "text", visible: false},
            { title: "License", itemTemplate: function(_, item) {
                    if(item.license_type === null || item.license_type === ""){
                        return showUploadMediaHtml(item, 'license', 'drivers',item.driverId);
                    } else {
                        return handleMedia(item, 'license', 'drivers',item.license_type, item.driverId );
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
                        .attr("onclick","openDriverModal();")
                        .text("Add");
                }
            }
        ]
    });
}