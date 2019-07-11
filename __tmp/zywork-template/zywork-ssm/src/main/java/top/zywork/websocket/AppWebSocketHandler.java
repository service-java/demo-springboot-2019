package top.zywork.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 应用WebSocket文本消息处理类<br />
 * 创建于2017-09-21
 *
 * @author 王振宇
 * @version 1.0
 */
public class AppWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("AppWebSocketHandler connected...");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        System.out.println(message.getPayload());
        session.sendMessage(new TextMessage("hi!!!"));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("AppWebSocketHandler handle transport error");
        if(session.isOpen()){
            session.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("AppWebSocketHandler closed...");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
