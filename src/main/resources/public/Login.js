/**
 * Created by Ryan on 19/11/2016.
 */
var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/");
webSocket.onmessage = function (msg) { updateChat(msg); };
webSocket.onclose = function () { alert("WebSocket connection closed") };

id("login").addEventListener("click", function(){
    attemptLogin(id("username").value(), id("password").value());
});

function attemptLogin(username, password)
{
    if(username !== "")
    {
        if(password !== "")
        {
            var jsonObject = {};
            jsonObject.Username = username;
            jsonObject.Password = password;
            webSocket.send(JSON.stringify(jsonObject));
        }
        else{ alert("no password")}
    }
    else{ alert("no username")}

    id("password").value = "";
}