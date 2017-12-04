// script for admin management

$(document).ready(function () {
    var url;
    var action;

    $("input[name=operation]").click(function () {
        action = this.value;
        if (action === "Delete") {
            url = "/admin/delete";
        } else if (action === "Update") {
            url = "/admin/update";
        }
    });

    $("form").submit(function (e) {

        // prevent form from submitting
        e.preventDefault();

        var data = new Object();
        data["username"] = this.username.value;

        // only use these for edit operation
        if (action === "Update") {
            data["firstname"] = this.firstname.value;
            data["lastname"] = this.lastname.value;
            data["allowedToUpload"] = this.allowedToUpload.checked;
        }

        //TODO: ask for confirmation to send request

        $.ajax({
            url: url,
            type: "GET",
            contentType: "application/json",
            data: data,
            dataType: "json",
            success: function (response, status, xhr) {
                // reponse is a boolean
                if (response) {
                    if (action === "Delete") {
                        // remove row
                        $('#' + data["username"]).remove();
                        $("#msg").html("<p class=\"green\">Deleted successfully</p>");
                    } else if (action === "Update") {
                        $("#msg").html("<p class=\"green\">Updated successfully</p>");
                    } else {
                        //DEBUG
                        console.log("Response: " + response + ". Something went astray");
                    }

                } else {
                    console.log("response: " + JSON.stringify(response));

                    //mv.addObject("msg", "<p style=\"color:green\">Successfully deleted " + username + " from database</p>");

                    // mv.addObject("msg", "<p style=\"color:red\">Fail to delete " + username + " from database</p>");

                }
            },
            error: function (xhr, textStatus, errorThrown) {
                $("#msg").html("<p style=\"color:red;\">ERROR</p>");
            }
        });


    });

});
