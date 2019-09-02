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
	$("#insertDriver").click(function(e) {
		var json = {
			driverName : $('input[name="driverName"]').val(),
			phoneNumber : $('input[name="phoneNumber"]').val(),
			licenseNumber : $('input[name="licenseNumber"]').val()
		};
		ajaxPost('insertDriverDetails', json).success(function(response) {
			alert(response.driverName + "added sucessfully!");
		});
	});

	$("#getAllDriverDetails").click(function(e) {
		ajaxGet('getAllDriverDetails').success(function(response) {
			
			$("#driversGrid").jsGrid({
		        width: "60%",
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
			alert(response.accountNumber + "added sucessfully!");
		});
	});
	
	$("#getAllAccountDetails").click(function(e) {
		ajaxGet('getAllAccountDetails').success(function(response) {
			
			$("#accountsGrid").jsGrid({
		        width: "60%",
		        height: "400px",
		        heading: true,
		        inserting: false,
		        editing: false,
		        sorting: true,
		        paging: true,
		        data: JSON.parse(response),
		        fields: [
		            { title: "Account Number", name: "accountNumber", type: "text", width: 50},
		            { title: "Driver Name",name: "ifscCode", type: "text", width: 50 },
		            { title: "Phone Number", name: "accountHolderName", type: "text", width: 50 }
		        ]
		    });

		});
	});
	
	
});