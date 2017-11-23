// script for admin management

$(document).ready(function () {
    var url;
    var action;

    $("input[name=operation]").click(function () {
        action = this.value;
        if (action === "Delete") {
            url = "/admin/delete";
        } else if (action === "Edit") {
            url = "/admin/edit";
        }
    });

    $("form").submit(function (e) {
        // prevent form from submitting
        e.preventDefault();

        var data = new Object();
        data["username"] = this.username.value;

        // only use these for edit operation
        if (action === "Edit") {
            data["canSave"] = this.canSave.checked;
            data["canDelete"] = this.canDelete.checked;
            data["canUpload"] = this.canUpload.checked;
        }
        console.log(action);
        console.log(this.canSave.checked);
        console.log(data["username"]);

        $.ajax({
            url: url,
            type: "GET",
            contentType: "application/json",
            data: data,
            dataType: "json",
            success: function (response, status, xhr) {
                
            },
            error: function (xhr, textStatus, errorThrown) {
                
            }
        });


    });

});
