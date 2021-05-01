let users = [];
let webSocket = new WebSocket("ws://" + window.location.host + window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2)) + "/users");
let handUp = false;
if (window.sessionStorage.getItem("hand") === "true") {
    handUp = true;
}

let name = window.sessionStorage.getItem("name");

function logout() {
    let json = JSON.stringify({
        "user": name,
        "action": "logout"
    });
    console.log(json);
    webSocket.send(json)
}

function connect() {

    webSocket.onopen = function () {
        let json = JSON.stringify({
            "user": name,
            "action": "login"
        });
        webSocket.send(json)
    };
}

connect();

function sendHand() {
    if (window.sessionStorage.getItem("hand") === "true") {
        handUp = true;
    }
    if (window.sessionStorage.getItem("hand") === "false") {
        handUp = false;
    }
    let json = JSON.stringify({
        "name": name,
        "handUp": handUp,
        "action": "hand"
    })
    console.log(json);
    webSocket.send(json)
}

function init(name) {
    console.log(name);
    window.sessionStorage.setItem("name", name);
    window.sessionStorage.setItem("hand", "false");
}

function messageHandler(event) {
    let message = JSON.parse(event.data);
    users = message.users;
    alert(users);
    if (users) {
        document.getElementById("table").value = users;
    }
}

webSocket.onmessage = function (event) {
    messageHandler(event);
};
webSocket.onclose = logout;