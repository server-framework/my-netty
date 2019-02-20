package com.mjump.game.ws.processor;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mjump.game.ws.channel.ChannelManager;
import com.mjump.game.ws.channel.GameNioSocketChannel;
import com.mjump.game.ws.enums.ConnType;
import com.mjump.game.ws.util.JsonUtil;
import com.mjump.game.ws.util.Result;
import com.mjump.game.ws.util.ResultCode;

import io.netty.channel.ChannelFuture;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @author
 * @date 2018/3/11
 */
@Service
public class SendProcessor {

    private Logger logger = LoggerFactory.getLogger(SendProcessor.class);

    /**
     * 推送消息给指定用户
     * @param result
     * @param channel
     * @return
     */
    public ChannelFuture send(Result result, GameNioSocketChannel channel) {

        String msg = JSON.toJSONString(result);

        TextWebSocketFrame tws = new TextWebSocketFrame(msg);
        if (channel != null) {
            ChannelFuture channelFuture = channel.writeAndFlush(tws);
            if (result.getRet() != ResultCode.GAME_WS_HEARTBEAT) {
                logger.debug("{}_{}_{}_{}-send msg: {}", channel.connTypeName(), channel.getGroupNum(), channel.id(), channel.getUserNum(), msg);
            }

            return channelFuture;
        }
        return null;
    }

    /**
     * 同步推送消息给指定用户
     * @param result
     * @param channel
     * @throws InterruptedException
     */
    public void sendSync(Result result, GameNioSocketChannel channel) throws InterruptedException {

        ChannelFuture future = send(result, channel);
        future.await(2, TimeUnit.SECONDS);
    }

    /**
     * 推送消息给指定用户
     * @param result
     * @param userNums
     */
    public void send(Result result, String... userNums) {
        String msg = JsonUtil.write(result);

        TextWebSocketFrame tws = new TextWebSocketFrame(msg);
        for (String userNum : userNums) {
            GameNioSocketChannel channel = ChannelManager.getChannel(userNum);
            if (channel != null) {
                channel.writeAndFlush(tws);

                logger.debug("{}_{}_{}_{}-send msg: {}", channel.connTypeName(), channel.getGroupNum(), channel.id(), channel.getUserNum(), msg);
            }
        }
    }

    /**
     * 同步发送消息给指定用户
     * @param result
     * @param userNums
     * @throws InterruptedException
     */
    public void sendSync(Result result, String... userNums) throws InterruptedException {

        String msg = JSON.toJSONString(result);

        TextWebSocketFrame tws = new TextWebSocketFrame(msg);
        for (String userNum : userNums) {
            GameNioSocketChannel channel = ChannelManager.getChannel(userNum);
            if (channel != null) {
                ChannelFuture channelFuture = channel.writeAndFlush(tws);
                logger.debug("{}_{}_{}_{}-send msg: {}", channel.connTypeName(), channel.getGroupNum(), channel.id(), channel.getUserNum(), msg);

                if (channelFuture.await(2, TimeUnit.SECONDS)) {
                    continue;
                }
            }
        }
    }

    /**
     * 推送消息给指定组别所有用户
     * @param result
     * @param groupNum 房间号/参与编号
     */
    public void sendToGroup(Result result, ConnType type, String groupNum) {
        ChannelGroup group = ChannelManager.getGroup(type, groupNum);
        if (group != null) {
            String msg = JSON.toJSONString(result);
            TextWebSocketFrame tws = new TextWebSocketFrame(msg);
            group.writeAndFlush(tws);

            logger.debug("{}_{}-groupSize: {}, send msg: {}", type.name(), groupNum, group.size(), msg);
        }
    }

    /**
     * 推送给指定用户所在组别所有用户
     * @param result
     * @param userNum
     */
    public void sendToGroupByUser(Result result, String userNum) {
        GameNioSocketChannel channel = ChannelManager.getChannel(userNum);
        if (channel == null) {
            return;
        }
        sendToGroup(result, channel.getConnType(), channel.getGroupNum());
    }
}
