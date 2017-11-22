// script for admin management

$(document).ready(function () {
    var url = "";
    $("input[name=delete]").click(function () {
        url = "/deleteUser";
    });
    $("input[name=edit]").click(function () {
        url = "/editUserPerm";
    });

    $(".adminManageForm").submit(function (e) {
        // prevent form from submitting
        e.preventDefault();


        var username = $(this).find("input[name='username']").val();

        //TODO: only use these for edit 
        var canSave = $(this).find("input[name='canSave']").val();
        var canDelete = $(this).find("input[name='canDelete']").val();
        var canUpload = $(this).find("input[name='canUpload']").val();

        $.ajax({
            url: url,
            type: "POST",
            contentType: "application/json",
            data: {username: username},
            dataType: "json",
            success: function (response, status, xhr) {
                //TODO: redirect to a page?            
            },
            error: function (xhr, textStatus, errorThrown) {
                //TODO: notify user of low bid
            }
        });


    });

});
