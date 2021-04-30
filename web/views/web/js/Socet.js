const webSocket = new WebSocket("ws://localhost:8080/Virtual_Classroom_war_exploded/users");


document.ready(connect());



function logout() {
    let json = JSON.stringify({
        "student": name,
        "content": name + " - disconnect",
        "action": "disconnect",
        "handStatus": false,
        "students": null
    });
    webSocket.send(json)
}

function check(){
    alert("Test")
}


function sendHand(text){
    alert("test");
    let json = JSON.stringify({
        "name": text,
    })
    alert(json);
    webSocket.send(json)
}

webSocket.onclose = logout;