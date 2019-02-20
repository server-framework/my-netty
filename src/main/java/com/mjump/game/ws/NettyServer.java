package com.mjump.game.ws;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mjump.game.ws.channel.MemberNioServerSocketChannel;
import com.mjump.game.ws.handle.GameChildChannelHandler;
import com.mjump.game.ws.processor.GameRevProcessorDispatch;
import com.mjump.game.ws.processor.SendProcessor;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author
 * @date 2018/3/13
 */
@Component
public class NettyServer {

    private static Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private Channel channel;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    @Autowired
    private MemberWsConfig memberWsConfig;

    @Autowired
    private GameRevProcessorDispatch gameRevProcessorDispatch;

    @Autowired
    private SendProcessor sendProcessor;

    @PostConstruct
    public void start() throws InterruptedException {
        logger.info("begin to start netty server");
        //配置服务端的NIP线程组
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup(memberWsConfig.getIoThreadNum());

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(MemberNioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, memberWsConfig.getBacklog())
                // 注意是childOption
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new GameChildChannelHandler(gameRevProcessorDispatch, sendProcessor, memberWsConfig));

        channel = serverBootstrap.bind(memberWsConfig.getPort()).sync().channel();

        logger.info("netty server listening on port {} and ready for connections...", memberWsConfig.getPort());
    }

    @PreDestroy
    public void stop() {
        logger.info("destroy netty server resources");
        if (null == channel) {
            logger.error("server netty channel is null");
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
        bossGroup = null;
        workerGroup = null;
        channel = null;
    }

}
