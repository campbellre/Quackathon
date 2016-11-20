//Establish the WebSocket connection and set up event handlers
var ws = new WebSocket("ws://" + location.hostname + ":" + location.port + "/images/");
ws.onmessage = function (msg) { updateChat(msg); };
ws.onclose = function () { };

var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/");
webSocket.onmessage = function (msg) { updateChat(msg); };
webSocket.onclose = function () { alert("WebSocket connection closed") };

//Send message if "Send" is clicked
id("send").addEventListener("click", function () {
    sendMessage(id("message").value);
});

function httpGetAsync(theUrl, callback)
{
    alert("heey");
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
            callback(xmlHttp.responseText);
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous
    xmlHttp.send(null);
}
//Send message if enter is pressed in the input field
id("message").addEventListener("keypress", function (e) {
    if (e.keyCode === 13) { sendMessage(e.target.value); }
});

id("images").addEventListener("click", function () {
   httpGetAsync("http://" + location.hostname + ":" + location.port + "/images", loadImages);
});


function loadImages(obj) {
    console.log("hello");
    data = JSON.parse(obj);
    id("imagelist").innerHTML = "";

    for(var i = 0; i < data.length; i++)
    {
        //var tmp = data[i].name.replace(/['"]+/g, '')
        id("imagelist").insertAdjacentHTML("afterbegin", '<li> <img src="/images/'
            + data[i].name
            + '" onclick="sendMessage(\''
            + data[i].name
            + '\');" /> </li>');
    }
}


//Send a message if it's not empty, then clear the input field
function sendMessage(message) {
        var dummyTest = "0,2,3,1,4;"
        var splitTest = dummyTest.split(",");
        window.alert(splitTest[0]);
        if (message !== "") {
        webSocket.send(message);
        id("message").value = "";
    }
}

//Send a message if it's not empty, then clear the input field
function sendMessage(message) {
    if (message !== "") {
        ws.send(message);
        id("message").value = "";
    }
}

//Update the chat-panel, and the list of connected users
function updateChat(msg) {

    var data = JSON.parse(msg.data);
    insert("chat", data.userMessage);
    id("userlist").innerHTML = "";
    data.userlist.forEach(function (user) {
        insert("userlist", "<li>" + user + "</li>");
    });
}

//Helper function for inserting HTML as the first child of an element
function insert(targetId, message) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
}

//Helper function for selecting element by id
function id(id) {
    return document.getElementById(id);
}

