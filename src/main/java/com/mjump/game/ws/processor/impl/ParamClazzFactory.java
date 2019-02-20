package com.mjump.game.ws.processor.impl;

import com.mjump.game.ws.enums.ConnType;
import com.mjump.game.ws.param.GameMsgType;

/**
 * 参数类型工厂
 * @author
 * @date 2018/3/14
 */
public class ParamClazzFactory {

    public static Class getParamClass(ConnType connType, GameMsgType msgType) {
        switch (connType) {
            case MINDPK:
                return getMindpkParamClass(msgType);
        }
        return null;
    }

    /**
     * 头脑pk游戏参数类型
     * @param msgType
     * @return
     */
    private static Class getMindpkParamClass(GameMsgType msgType) {
        switch (msgType) {
            case ONLINE:
                return null;

            case READY:
                return null;

            case START:
                return null;

            case SUBMIT:
                return null;

            case REJECT:
                return null;

            case ABANDON:
                return null;

            case TIMEOUT:
                return null;
        }
        return null;
    }

}
