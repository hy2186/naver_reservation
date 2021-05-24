$(document).ready(function() {
    let loginUnit = new LoginUnit();
    loginUnit.activateLoginButton();
});

function LoginUnit() {
}

LoginUnit.prototype = {
    "activateLoginButton": function() {
        $(".login_btn").on("click", function() {
            this.login();
        }.bind(this));
    },

    "login": function() {
        let reservationEmail = $(".login_input").val();

        if (this.checkEmailValidation(reservationEmail)) {
            $("#form1").submit();
            return;
        }

        $("#warning_msg").show();
    },

    "checkEmailValidation": function(reservationEmail) {
        return (/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i).test(reservationEmail);
    },
}