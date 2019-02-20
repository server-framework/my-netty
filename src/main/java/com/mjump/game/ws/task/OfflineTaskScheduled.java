package com.mjump.game.ws.task;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mjump.game.ws.channel.ChannelManager;
import com.mjump.game.ws.channel.OfflineChannel;
import com.mjump.game.ws.event.EventPublisher;

/**
 * @author
 * @date 2018/3/29
 */
@Component
public class OfflineTaskScheduled {

    @Autowired
    private EventPublisher eventPublisher;

    @Scheduled(fixedDelay = 1, initialDelay=1)
    public void run() {
        Map<String, OfflineChannel> offlineChannels = ChannelManager.getOfflineChannels();
        Set<String> userNums = offlineChannels.keySet();

        for (String userNum : userNums) {
            OfflineChannel offlineChannel = offlineChannels.get(userNum);

            Date offlineTime = offlineChannel.getOfflineTime();

            // 超过3秒不重连则认为离线
            if (System.currentTimeMillis() - offlineTime.getTime() >= 2000) {
                ChannelManager.removeOfflineChannel(userNum);
                eventPublisher.publishOfflineEvent(userNum, offlineChannel.getType(), offlineChannel.isOfflineSelf());
                continue;
            }
        }
    }
}
