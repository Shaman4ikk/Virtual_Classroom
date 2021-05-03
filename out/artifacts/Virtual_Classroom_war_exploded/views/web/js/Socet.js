let users = [];
let webSocket = new WebSocket("ws://" + window.location.host + window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/users");
let url = "ws://" + window.location.host + window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/users";
let handUp = false;

let name;
let loginForm = document.getElementsByClassName('loginForm')[0];
loginForm.onsubmit = (e) => initName(e);

function logout() {
    alert(sessionStorage.getItem("name"));
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
        "name": name,
        "action": "login"
    });
    webSocket.send(json);
    sessionStorage.setItem("name", name);
}

function sendHand() {
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
    console.log(json);
    webSocket.send(json)
}

function messageHandler(event) {
    let message = JSON.parse(event.data);
    alert(message.name);
    users = message.users;
    if (users) {
        document.getElementById("table").value = users;
    }
    alert(users);

}

webSocket.onmessage = function (event) {
    messageHandler(event);
};
webSocket.onclose = logout;