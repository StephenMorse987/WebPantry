$(document).ready(function() {
    $(window).keydown(function(event){
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
});

let checkList;
const emailFormat = RegExp("^.+@.+\..+$");

async function getUsernames() {
    const response = await fetch('/lib/GetUsernames');
    const nameList = await response.json();
    checkList = nameList;
}

getUsernames()
let valid = false;
let failmessage = "";

function notSame(value) {
    // values must not match and valid must remain true to submit
    valid = valid && !(value === $("#username").val());
}

function validateForm() {
    valid = true;
    if ($("#username").val() == "") {
        valid = false;
        failmessage = "The Username field is empty.";
    } else if ($("#email").val() == "") {
        valid = false;
        failmessage = "The Email field is empty.";
    } else if (!emailFormat.test($("#email").val())) {
        valid = false;
        failmessage = "The Email given does not match the expected format.";
    } else if ($("#pass").val() == "") {
        valid = false;
        failmessage = "The Password field is empty.";
    } else {
        Array.prototype.forEach.call(checkList,value => notSame(value));
        if (!valid) failmessage = "An User with that name already exists.";
    }
    
    if (valid) {
        $("#validated-form").submit();
    }
    else {
        alert(failmessage);
    }
}
