<%--
  Created by IntelliJ IDEA.
  User: Wang Genshen
  Date: 2017-09-21
  Time: 08:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WebSocket测试页</title>
</head>
<body>
<a href="javascript:void(0);" onclick="sendMsg();">发送Socket消息</a>
<a href="javascript:void(0);" onclick="closeSocket();">关闭Socket</a>
</body>
<script>

    var webSocket;
    if ('WebSocket' in window) {
        webSocket = new WebSocket('ws://localhost:8080/socket/test');
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket('ws://localhost:8080/socket/test');
    } else {
        alert('不支持Web Socket')
    }
    webSocket.onerror = function (event) {
        alert(event.data);
    };

    webSocket.onopen = function (event) {
        alert("open!");
    };

    webSocket.onmessage = function (event) {
        alert("message: " + event.data);
    };

    webSocket.onclose = function (event) {
        alert("close!");
        webSocket.close();
    };

    function sendMsg() {
        webSocket.send('hello');
    }

    function closeSocket() {
        webSocket.close();
    }
</script>
</html>
