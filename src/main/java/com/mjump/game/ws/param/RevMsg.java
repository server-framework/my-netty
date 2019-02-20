package com.mjump.game.ws.param;

import com.mjump.game.ws.enums.ConnType;

/**
 * 消息实体
 * @author
 * @date 2018/3/11
 */
public class RevMsg {

    private ConnType type;

    private GameMsgType msgType;

    private String token;

    private String content;

    public ConnType getType() {
        return type;
    }

    public void setType(ConnType type) {
        this.type = type;
    }

    public GameMsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(GameMsgType msgType) {
        this.msgType = msgType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
