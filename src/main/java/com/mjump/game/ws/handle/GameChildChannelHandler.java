package com.mjump.game.ws.handle;

import java.util.concurrent.TimeUnit;

import com.mjump.game.ws.MemberWsConfig;
import com.mjump.game.ws.processor.GameRevProcessorDispatch;
import com.mjump.game.ws.processor.SendProcessor;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author
 * @date 2018/3/9
 */
public class GameChildChannelHandler extends ChannelInitializer<SocketChannel> {

    private GameRevProcessorDispatch gameRevProcessorDispatch;

    private SendProcessor sendProcessor;

    private MemberWsConfig memberWsConfig;

    public GameChildChannelHandler(GameRevProcessorDispatch gameRevProcessorDispatch, SendProcessor sendProcessor, MemberWsConfig memberWsConfig) {
        this.gameRevProcessorDispatch = gameRevProcessorDispatch;
        this.sendProcessor = sendProcessor;
        this.memberWsConfig = memberWsConfig;
    }

    @Override
    protected void initChannel(SocketChannel e) throws Exception {
    	// 将请求和应答消息解码为HTTP消息
        e.pipeline().addLast("http-codec", new HttpServerCodec());
        // 将HTTP消息的多个部分合成一条完整的HTTP消息
        e.pipeline().addLast("aggregator", new HttpObjectAggregator(64 * 1024));
        // 向客户端发送HTML5文件
        e.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        // 超时检查
        e.pipeline().addLast("ping", new IdleStateHandler(memberWsConfig.getReaderIdleTime(), memberWsConfig.getWriterIdleTime(), memberWsConfig.getAllIdleTime(), TimeUnit.SECONDS));
        e.pipeline().addLast("handler", new WebSocketServerHandler(gameRevProcessorDispatch, sendProcessor));
    }
}