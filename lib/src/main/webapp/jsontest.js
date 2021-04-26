function makeRow(value) {
    document.getElementById("output-table").appendChild(document.createElement("tr")).appendChild(document.createElement("td")).innerText = value;
}

async function getItemNames() {
    const response = await fetch('/lib/GetItemNames');
    const nameList = await response.json();
    nameList.forEach(makeRow);
}

getItemNames();
