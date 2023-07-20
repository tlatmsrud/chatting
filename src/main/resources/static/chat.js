var stompClient = null;
var loginId = null;
var roomId = null;
function setConnected(connected) {
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/'+roomId, function (greeting) {
            showGreeting(greeting.body);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    var messageObj = new Object();

    messageObj.roomId = roomId
    messageObj.writerId = loginId
    messageObj.message = $("#message").val();

    stompClient.send("/app/send" ,{}, JSON.stringify(messageObj));
}

function getRoomId(){
    var queryString = window.location.search;
    var urlParams = new URLSearchParams(queryString);
    // query string에서 'userId' 파라미터 값을 가져옵니다.
    return urlParams.get('roomId');
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}
function getAccessTokenFromCookie() {
    var name = 'AccessToken'; // 쿠키 이름
    var decodedCookie = decodeURIComponent(document.cookie);
    var cookieArray = decodedCookie.split(';'); // 쿠키들을 ';'를 기준으로 배열로 분리

    // 쿠키 배열에서 JWT AccessToken을 찾음
    for (var i = 0; i < cookieArray.length; i++) {
        var cookie = cookieArray[i].trim();
        if (cookie.indexOf(name) === 0) {
            return cookie.substring(name.length, cookie.length);
        }
    }

    // JWT AccessToken이 쿠키에 없는 경우 null 반환
    return null;
}

function decodeJwtAccessToken(accessToken) {
    var base64Url = accessToken.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var decodedData = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(decodedData);
}

$(function () {
    var accessToken = getAccessTokenFromCookie();
    var jsonData = decodeJwtAccessToken(accessToken)

    loginId = jsonData["id"]
    roomId = getRoomId();
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    connect();
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});