function ajaxPost(url, data) {
	return $.ajax({
		type : "POST",
		url : url,
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json"
	});
}

function ajaxGet(url) {
	return $.ajax({
		type : "GET",
		url : url
	});
}

$(function() {
	$("[data]").click(function () {
		$("section").load("pages/"+this.getAttribute("data")+".html");
    });
	
	$("#insertDriver").click(function(e) {
		var driver = {
			driverName : $('input[name="driverName"]').val(),
			phoneNumber : $('input[name="phoneNumber"]').val(),
			licenseNumber : $('input[name="licenseNumber"]').val(),
            badgeNumber : $('input[name="badgeNumber"]').val(),
		};

		var address = {
            houseName: $('input[name="houseName"]').val(),
            streetName: $('input[name="streetName"]').val(),
            landmark: $('input[name="landmark"]').val(),
            area: $('input[name="area"]').val(),
            pincode: $('input[name="pincode"]').val(),
            village : $('input[name="village"]').val(),
            district: $('input[name="district"]').val(),
            state: $('input[name="state"]').val(),
            country: $('input[name="country"]').val()
        };

		var json = {
            driver: driver,
            address: address
        };
		ajaxPost('insertDriverDetails', json).success(function(response) {
			alert(response.driverName + " added sucessfully!");
		});
	});

	$("section").on('click','button',  function(e) {
		ajaxGet(this.getAttribute("data")).success(function(response) {
			
			$("#driversGrid").jsGrid({
		        width: "100%",
		        height: "400px",
		        heading: true,
		        inserting: false,
		        editing: false,
		        sorting: true,
		        paging: true,
		        data: response,
		        fields: [
		            { name: "driverId", type: "text", visible: false},
		            { title: "Driver Name",name: "driverName", type: "text", width: 50 },
		            { title: "Phone Number", name: "phoneNumber", type: "text", width: 50 },
		            { title: "License Number",name: "licenseNumber", type: "text", width: 50 }
		        ]
		    });

		});
	});
	
	$("#addAccount").click(function(e) {
		var json = {
			accountNumber : $('input[name="accountNumber"]').val(),
			ifscCode : $('input[name="ifscCode"]').val(),
			accountHolderName : $('input[name="accountHolderName"]').val()
		};
		ajaxPost('insertAccountDetails', json).success(function(response) {
			alert(response.accountNumber + " added sucessfully!");
		});
	});
	
	$("#getAllAccountDetails").click(function(e) {
		ajaxGet('getAllAccountDetails').success(function(response) {
			
			$("#accountsGrid").jsGrid({
		        width: "100%",
		        height: "400px",
		        heading: true,
                noDataContent: "No Accounts added!",
		        inserting: false,
		        editing: false,
		        sorting: true,
		        paging: true,
		        data: JSON.parse(response),
		        fields: [
                    { title: "Account holder name", name: "accountHolderName", type: "text", width: 50 },
                    { title: "Account Number", name: "accountNumber", type: "text", width: 50},
                    { title: "IFSC code",name: "ifscCode", type: "text", width: 50 }
		        ]
		    });

		});
	});
	
	
});