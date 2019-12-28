$("#resetPasswordForm").hide();
function confirmUser() {
    $('#resetusername').attr("value",$("#username").val());
    $.ajax({
        type: "POST",
        url: 'confirmuser',
        data: JSON.stringify($("#forgotPasswordForm").serializeJSON()),
        contentType: "application/json",
        dataType : 'json',
        success: function (response) {
            if( response ){
                $("#resetPasswordForm").show();
                $("#forgotPasswordForm").hide();
            }
        },
        error: function (response) {
            $("#resetusername").val("");
            alert(response.responseText + "Kindly Register!!!");
            window.location.href="/registration.html";
        }
    });
}

function resetPassword() {
    var password = $('input[name="password"]').val();
    var confirmPassword = $('input[name="confirmPassword"]').val();

    if (password !== confirmPassword ) {
        $.toast({
            title: 'Reset password',
            // subtitle: '11 mins ago',
            content: 'Confirm password does not match!',
            type: 'error',
            container: $("#forgotPasswordForm"),
            delay: 5000
        });
    } else {
        $.ajax({
            type: "POST",
            url: 'updatePassword',
            data: JSON.stringify($("#resetPasswordForm").serializeJSON()),
            contentType: "application/json",
            success: function (response) {
                if( response ){
                    $("#resetusername").val("");
                    alert('User "' + response.toUpperCase() + '" password updated. Please login!');
                    window.location.href = "login.html";
                }
            },
            error: function (response) {
                alert(response.responseText);
            }
        });
    }
}