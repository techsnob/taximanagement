function saveDriver(isNew) {
    var postData;
    if(isNew){
        url = "insertDriver";
        postData = new FormData($("#driver")[0]);
    } else {
        url = "updateDriver";
        postData = new FormData($("#driver")[0]);
    }
    var response = ajaxPostFileData('insertDriver', new FormData($("#driver")[0]));
    $("#driversGrid").jsGrid(isNew ? "insertItem" : "updateItem", response);
    $("#driverDialog").modal("hide");
}

function openDriverModal(mode, item){
	if(mode == 'Edit'){
		$('input[name="driverId"]').val(item.driverId);
		$('input[name="firstName"]').val(item.firstName);
		$('input[name="lastName"]').val(item.lastName);
		$('input[name="phoneNumber"]').val(item.phoneNumber);
		$("#insertDriver").attr("onclick", "saveDriver(false);");
		$("#driverDialog").find('.modal-title').text("Edit Driver");
		$("#driverDialog").modal('show');
	} else {
		$("#insertDriver").attr("onclick", "saveDriver(true);");
	}
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
        rowDoubleClick: function(args) {
        	openDriverModal('Edit', args.item);
        },
        fields: [
            {title:'Driver ID',  name: "driverId", type: "text"},
            {title: "First Name", name: "firstName", type: "text"},
            {title: "Last Name", name: "lastName", type: "text"},
            {title: "Phone Number", name: "phoneNumber", type: "text"},
            {name: "aadhaar_contenttype", type: "text", visible: false},
            { title: "Aadhaar", itemTemplate: function(_, item) {
                    return $("<a>")
                    	.attr("href", 'media?fileName=aadhaar&moduleName=drivers&contentType='+item.aadhaar_contenttype+'&columnId='+item.driverId)
                    	.attr("target", "_blank")
                    	.text("Link");
              	}
            },
            {name: "license_contenttype", type: "text", visible: false},
            { title: "License", itemTemplate: function(_, item) {
                return $("<a>")
                	.attr("href", 'media?fileName=license&moduleName=drivers&contentType='+item.license_contenttype+'&columnId='+item.driverId)
                	.attr("target", "_blank")
                	.text("Link");
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
                        //.attr("data-toggle", "modal")
                        //.attr("data-target", "#driverDialog")
                        .attr("onclick","openDriverModal();")
                        .text("Add");
                }
            }
        ]
    });
}