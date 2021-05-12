let webSocket = new WebSocket("ws://" + window.location.host + window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/users");
let handUp = false;

let name;
let loginForm = document.getElementsByClassName('loginForm')[0];

if (loginForm) {
    loginForm.onsubmit = (e) => initName(e);
}


function openAction(evt, action) {
    // Declare all variables
    let i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

        // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(action).style.display = "block";
    evt.currentTarget.className += " active";
}

function logout() {
    let json = JSON.stringify({
        "name": sessionStorage.getItem("name"),
        "action": "logout"
    });
    console.log(json);
    webSocket.send(json);
}

function initName(e) {
    name = document.getElementsByClassName('InputName')[0].value;
    let json = JSON.stringify({
        "action": "login"
    });
    webSocket.send(json);
    sessionStorage.setItem("name", name);
}

function sendHand() {
    event.preventDefault();
    if (window.sessionStorage.getItem("hand") === "true") {
        handUp = true;
    }
    if (window.sessionStorage.getItem("hand") === "false") {
        handUp = false;
    }
    let json = JSON.stringify({
        "name": sessionStorage.getItem("name"),
        "handUp": handUp,
        "action": "hand"
    })
    webSocket.send(json);
}

function messageHandler(event) {
    let message = JSON.parse(event.data);
    renderTable(message.userSet);
}

function renderTable(userSet){
    let tableNode = document.createElement('table');
    tableNode.className = 'table__users';
    let int = 0;

    // creating labels
    let labelRowNode = document.createElement('tr');
    for(const label in userSet[0]){
        let labelNode = document.createElement('th');
        if (int === 0){
            labelNode.textContent = "Name";
        } else if(int === 1){
            labelNode.textContent = " ";
        }
        int++;
        labelRowNode.appendChild(labelNode);
    }
    tableNode.appendChild(labelRowNode);

    // inserting content
    userSet.forEach((entry) => {
       let entryRowNode = document.createElement('tr');
       for(const label in entry) {
           let entryNode = document.createElement('td');
           let content = entry[label];

           if(typeof entry[label] === "boolean"){
               content = entry[label] ? '🤚' : '';
           }

           entryNode.textContent = content;
           entryRowNode.appendChild(entryNode);
       }
       tableNode.appendChild(entryRowNode);
    });

    let tableWrapper = document.getElementsByClassName('table__wrapper')[0];
    while (tableWrapper.firstChild) {
        tableWrapper.removeChild(tableWrapper.lastChild);
    }
    tableWrapper.appendChild(tableNode);


}

webSocket.onmessage = function (event) {
    messageHandler(event);
};
webSocket.onclose = logout;