function addInto(current,tag,setClass) {
    let newElement = current.appendChild(document.createElement(tag));
    newElement.setAttribute("class",setClass);
    return newElement;
}

function addLinkInto(current,toLink) {
    let newElement = current.appendChild(document.createElement("a"));
    newElement.setAttribute("href",toLink);
    return newElement;
}

function addEnd(current,tag,text) {
    let newElement = current.appendChild(document.createElement(tag));
    newElement.innerText = text;
}

function appendInput(current,inputLabel,inputType,defaultText,identifier) {
    let newElement = current.appendChild(document.createElement("label"));
    newElement.setAttribute("for",identifier);
    newElement.innerText = inputLabel;
    newElement = current.appendChild(document.createElement("input"));
    newElement.setAttribute("type",inputType);
    newElement.setAttribute("class","form-control");
    newElement.setAttribute("placeholder",defaultText);
    newElement.setAttribute("name",identifier);
}

document.body.innerHTML = "";
addEnd(addInto(document.body,"div","container"),"h1","WebPantry");

let curr = addInto(document.body,"div","container-fluid").appendChild(document.createElement("form"));
curr.setAttribute("action","Login");
curr.setAttribute("method","POST");
addEnd(curr,"h3","Please Login");
appendInput(addInto(curr,"div","form-group"),"Username:","text","Enter Username","username");
appendInput(addInto(curr,"div","form-group"),"Password:","password","Enter Password","pass");

curr = curr.appendChild(document.createElement("button"));
curr.setAttribute("type","submit");
curr.setAttribute("class","btn btn-primary");
curr.innerText = "Submit";

addEnd(addLinkInto(addInto(document.body,"div","container"),"/lib/Activity"),"h1","Activity");
addEnd(addLinkInto(addInto(document.body,"div","container"),"/lib/Logout"),"h1","Logout");
