package com.mjump.game.ws.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.mjump.game.ws.enums.ConnType;


/**
 * @author
 * @date 2018/3/29
 */
@Component
public class EventPublisher {

    @Autowired
    ApplicationContext applicationContext;

    public void publishOfflineEvent(String userNum, ConnType type, boolean offlineSelf) {
        applicationContext.publishEvent(new OfflineEvent(this, userNum, type, offlineSelf));
    }
}
