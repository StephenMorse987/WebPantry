$(document).ready(function() {
    $(window).keydown(function(event){
        if (event.keyCode == 13) {
            event.preventDefault();
            return false;
        }
    });
});

let checkList;

async function getItemNames() {
    const response = await fetch('/lib/GetItemNames');
    const nameList = await response.json();
    checkList = nameList;
}

getItemNames();
let valid = false;
let failmessage = "";

function notSame(value) {
    // values must not match and valid must remain true to submit
    valid = valid && !(value === $("#namefield").val());
}

function validateForm() {
    valid = true;
    if ($("#amountfield").val() == "") {
        valid = false;
        failmessage = "The Amount field is empty.";
    } else if ($("#measurefield").val() == null) {
        valid = false;
        failmessage = "A Measure has not been selected.";
    } else if ($("#namefield").val() == "") {
        valid = false;
        failmessage = "The Item Name is blank.";
    } else {
        Array.prototype.forEach.call(checkList,value => notSame(value));
        if (!valid) failmessage = "An item with that name already exists in the Pantry.";
    }
    
    if (valid) {
        $("#validated-form").submit();
    }
    else {
        alert(failmessage);
    }
}
