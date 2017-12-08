var testResultModal = "<a href=\"#\" data-toggle=\"modal\" data-target=\"#testResultModal\">Test Result</a>"
        + "<div class=\"modal fade\" id=\"testResultModal\" role=\"dialog\">"
        + '<div class="modal-dialog">'
        + '<div class="modal-content">'
        + '<div class="modal-header">'
        + '<button type="button" class="close" data-dismiss="modal">&times;</button>'
        + '<h4 class="modal-title">Test Result</h4>'
        + '</div>'
        + '<div class="modal-body">'
        + '<p class="testResult"></p>'
        + '</div></div></div></div>';

$(document).ready(function () {
    $("#registrationForm").submit(function (e) {

        var pass = true;

        if (this.repassword.value !== this.password.value) {
            $("#pwMisMatch").html("<span class=\"error\">Passwords do not match</span>")
            pass = false;
        }
        if (this.reemail.value !== this.email.value) {
            $("#emailMisMatch").html("<span class=\"error\">Emails do not match</span>")
            pass = false;
        }

        if (!pass)
            e.preventDefault();
    });

    /**
     * bind on "View" button click to submit request to view previous tests
     */
    $(".savedWorkForm").submit(function (e) {
//    $("#savedWorkFormBtn").click(function (e) {
        e.preventDefault();
        var s = this.state.value;
        var y = this.year.value;
        var t = this.test.value;
        console.log(s);
        console.log(y);
        console.log(t);

        /** TODO: 
         redirect to main page, 
         load state, 
         send request to get data, 
         upon success, load test */


        // work around
        if (t) {
            $(".testResult").html("Loading...");
            $.ajax({
                url: "/view-measure/" + t,
                type: "GET",
                contentType: "application/json",
                data: {state: s, year: y},
                dataType: "json",
                success: function (response, status, xhr) {

                    //TODO: display measure result
                    // response is a TestResult object
                    var result = '<h1>' + y + ' ' + s + ' ' + t + ' Result</h1>';
                    var dataStr = "";
                    $.each(response, function (key, val) {
                        if (displayTestVar(key)) {
                            if (val === 0) {
                                return true;
                            }
                            dataStr += "<p>" + translateTestKeyName(key) + " : " + val + "</p>";
                        }
                    });
                    result += dataStr;

                    //TEST
                    result += "Is This State Gerrymandered? " + response.gerrymandered + "<br/>";
                    result += (response.skipped ? "<p style=\"color:red\">Skipped</p>" : "");
                    $(".testResult").html(result);
                },
                error: function (xhr, textStatus, errorThrown) {
                    console.log(textStatus + ": " + errorThrown);
                    var result = '<h1>' + $('#gerrymanderingMeasure').val() + ' Not Available</h1>';
                    $(".testResult").html();
                }
            });
        } else {
            $(".testResult").html("No Test Saved");
        }

    });

});

