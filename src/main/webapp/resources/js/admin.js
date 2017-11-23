// script for admin management

$(document).ready(function () {
    var url;
    var action;
    
    $("input[name=operation]").click(function () {
        action = $(this).val;
        if (action === "Delete") {
            url = "/admin/delete";
        } else if (action === "Edit") {
            url = "/admin/edit";
        }
    });

    $(".adminManageForm").submit(function (e) {
        // prevent form from submitting
        e.preventDefault();

        var data = new Object();
        data["username"] = $(this).find("input[name=username]").val();
        console.log(data["username"]);
        console.log($(this).find("input"));

        // only use these for edit operation
        if (action === "edit") {
            data["canSave"] = $(this).find("input[name='canSave']").val();
            data["canDelete"] = $(this).find("input[name='canDelete']").val();
            data["canUpload"] = $(this).find("input[name='canUpload']").val();
        }

        $.ajax({
            url: url,
            type: "GET",
            contentType: "application/json",
            // data: {username: username},
            data: data,
            dataType: "json",
            success: function (response, status, xhr) {
                //TODO: redirect to a page?   
                alert("success");
            },
            error: function (xhr, textStatus, errorThrown) {
                alert("error");
            }
        });


    });

});
