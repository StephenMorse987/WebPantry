function removeItem (itemIndex) {
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST","/lib/DeleteItem",true);
    xhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
    xhttp.send("itemindex=" + itemIndex);
    location.reload();
}