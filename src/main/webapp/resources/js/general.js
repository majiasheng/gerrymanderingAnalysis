
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
});
