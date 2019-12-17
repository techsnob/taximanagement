function ajaxPost(url, data) {
    return $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(data),
        dataType: "json",
        contentType: "application/json",
        beforeSend: function () {
            $("#content").modal('show');
        },
        complete: function () {
            $("#content").modal('hide');
        }
    });
}

function ajaxGet(url) {
    return $.ajax({
        type: "GET",
        url: url,
        beforeSend: function () {
            $("#content").modal('show');
        },
        complete: function () {
            $("#content").modal('hide');
        }
    });
}

function ajaxPostFileData(url, postData){
	return $.ajax({
        type: "POST",
        url: url,
        data: postData,
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
}

function deleteMedia(element) {
    if(element){
        var url= element.previousElementSibling.getAttribute("href").replace("media","delete");
        ajaxGet(url).success(function (data) {
            alert(data);
            $("#vehiclesLink").click();
        });
    }
}

function handleMedia(item, fileName, module, fileType, itemId) {
    var html='<a target="_blank" href='+'media?fileName='+fileName+'&moduleName='+module+'&contentType='+fileType+'&columnId='+itemId+'>Link</a>';
    html = html + '<span class="ml-3" onclick="deleteMedia(this);"><i class="fas fa-trash"></i></span>';
    return html;
}

function uploadMediaInCell(element) {
    var formData = new FormData($("#uploadContent")[0]);
    ajaxPostFileData("updateMedia", formData).success(function (data) {
        alert(data);
    //TODO refreshCell content
    });
}

function showUploadMediaHtml(item, fileName, moduleName, itemId) {
    return '<form id="uploadContent"><input type="file" name="insurance" data="insurance,vehicles" onchange="uploadMediaInCell(this);" class="inputCell">'
        +'<input type="text" name="moduleName" hidden value="'+moduleName+'">'
        +'<input type="text" name="columnId" hidden value="'+itemId+'">'
        +'<input type="text" name="fileName" hidden value="'+fileName+'">'
        +'</form>';
}

function loadMediaContent(){
	var data = $("#test").serializeJSON();
	ajaxPost('media',data).success(function(response){
		console.log(response);
	});
}

$(function () {
    $("#content").modal('show');
    ajaxGet('loggeduser').success(function (response) {
        $("#loggeduser").html(response.username && response.username[0].toUpperCase() + response.username.slice(1));
    });

    $('#vehiclesLink').click(function(event){
        event.preventDefault();
        $('#mainContent').load($(this).attr('href'), function () {
            ajaxGet('vehicles').success(function (response) {
                $("#vehiclesGrid").jsGrid("option", "data", response);
            });
            initVehicles();
        });
        return false;
    });

    $('#driversLink').click(function(event){
        event.preventDefault();
        $('#mainContent').load($(this).attr('href'), function () {
            ajaxGet('drivers').success(function (response) {
                $("#driversGrid").jsGrid("option", "data", response);
            });
            initDrivers();
        });
        return false;
    });

    $('#accountsLink').click(function(event){
        event.preventDefault();
        $('#mainContent').load($(this).attr('href'), function () {
            //ajaxGet('vehicles').success(function (response) {
                //$("#accountDialog").jsGrid("option", "data", response);
            //});
            initAccounts();
        });
        return false;
    });

    $('#reportsLink').click(function(event){
        event.preventDefault();
        $('#mainContent').load($(this).attr('href'), function () {
            initTrip();
        });
        return false;
    });

    $("#content").modal('hide');
});