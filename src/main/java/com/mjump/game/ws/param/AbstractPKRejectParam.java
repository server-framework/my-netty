package com.mjump.game.ws.param;

public abstract class AbstractPKRejectParam {
    
    private String kickedUserNum;

    public String getKickedUserNum() {
        return kickedUserNum;
    }

    public void setKickedUserNum(String kickedUserNum) {
        this.kickedUserNum = kickedUserNum;
    }
}
