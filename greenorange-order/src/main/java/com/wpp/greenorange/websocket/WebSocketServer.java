package com.wpp.greenorange.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 吴鹏鹏ppp
 * WebSocktServer客户端
 */
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {
    /**
     * 与某个客户端的会话
     */
    private Session session;
    /**
     * 当前连接的用户id
     */
    private Integer userId;
    /**
     * 存储所有连接的
     */
    private static ConcurrentHashMap<Integer,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 连接成功服务端的方法
     * @param session
     * @param userId
     */
    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "userId") Integer userId){
        this.session = session;
        this.userId = userId;

        // name是用来表示唯一客户端，如果需要指定发送，需要指定发送通过name来区分
        webSocketMap.put(userId,this);
    }

    /**
     * 连接关闭的方法
     */
    @OnClose
    public void OnClose(){
        webSocketMap.remove(this.userId);
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") Integer userId) throws IOException {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            System.out.println(userId+"用户不在线");
        }
    }


    public static void massInfo(String message) throws IOException {
        ConcurrentHashMap.KeySetView<Integer, WebSocketServer> keySet = webSocketMap.keySet();
        for (Integer userId : keySet) {
            sendInfo(message, userId);
        }
    }
}
