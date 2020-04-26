package com.platform.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/webSocket")
@Component
public class WebSocketServer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //存放每个客户端对应的WebSocketServer对象。
    private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session){
        //sessionPools.put(userName, session);
        logger.info("websocket连接成功!");
    }

    //关闭连接时调用
    @OnClose
    public void onClose(){
        //sessionPools.remove(userName);
    }

    //收到客户端信息
    @OnMessage
    public void onMessage(String message) throws IOException{

    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable){
        logger.error("websocket出错:"  + throwable.getMessage());
    }

    //发送消息
    public void sendMessage(Session session, String message) {
        if(session != null){
            try {
                session.getBasicRemote().sendText(message);
            }
            catch (Exception ex){
                logger.error("发送websocket消息失败:" + ex.getMessage());
            }
        }
    }

    //给指定用户发送信息
    public void sendInfo(String userName, String message){
        Session session = sessionPools.get(userName);
        sendMessage(session, message);
    }
}