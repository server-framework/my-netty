package com.mjump.game.ws.util;

/**
 * 返回码
 *
 * @author
 * @date 2017/3/13
 */
public class ResultCode extends BaseResultCode {
    
    // 公共代码
    public static final int NOT_FOUND_DATA = 1100;
    public static final int RESOURCE_NOT_FOUND = 1002;
    public static final int ID_EMPTY = 1003;
    public static final int REQUIRED_PARM_EMPTY = 1004;

    // 游戏
    public static final int GAME_WS_USER_ONLINE = 11000;
    public static final int GAME_WS_USER_READY = 11001;
    public static final int GAME_WS_USER_START = 11002;
    public static final int GAME_WS_USER_CHECK_NOTDONE = 11003;
    public static final int GAME_WS_USER_CHECK_DONE = 11004;
    public static final int GAME_WS_USER_SUBMIT_SINGLE = 11005;
    public static final int GAME_WS_USER_SUBMIT_MULTI = 11006;
    public static final int GAME_WS_USER_SUBMIT_DONE = 11007;
    public static final int GAME_WS_USER_OFFLINE = 11008;
    public static final int GAME_WS_USER_SUBMIT_OTHER = 11009;
    public static final int GAME_WS_REJECT_FAIL = 11011;
    public static final int GAME_WS_ABANDON_FAIL = 11012;
    public static final int GAME_WS_USER_ONLINE_ERROR = 11014;
    public static final int GAME_ATTEND_SUCCESS = 11015;
    public static final int GAME_ATTEND_FAIL = 11016;
    public static final int GAME_ATTEND_REPEAT = 11017;
    public static final int GAME_WS_HEARTBEAT = 11100;

    
    // 初始化状态码与文字说明
    static {

        // 公共代码 1xxx
        messageMap.put(NOT_FOUND_DATA, "数据不存在");
        messageMap.put(RESOURCE_NOT_FOUND, "ID对应数据不存在");
        messageMap.put(ID_EMPTY, "ID不能为空");
        messageMap.put(REQUIRED_PARM_EMPTY, "非法参数");

        messageMap.put(GAME_WS_USER_ONLINE, "用户上线");
        messageMap.put(GAME_WS_USER_READY, "准备游戏");
        messageMap.put(GAME_WS_USER_START, "开始游戏");
        messageMap.put(GAME_WS_USER_CHECK_NOTDONE, "积分扣除未完成");
        messageMap.put(GAME_WS_USER_CHECK_DONE, "积分扣除完成");
        messageMap.put(GAME_WS_USER_SUBMIT_SINGLE, "提交单关单人成绩完成");
        messageMap.put(GAME_WS_USER_SUBMIT_MULTI, "提交单关多人成绩完成");
        messageMap.put(GAME_WS_USER_SUBMIT_DONE, "提交所有关卡成绩完成");
        messageMap.put(GAME_WS_USER_OFFLINE, "用户离线");
        messageMap.put(GAME_WS_USER_SUBMIT_OTHER, "提交单关其他人成绩完成");
        messageMap.put(GAME_WS_REJECT_FAIL, "踢人失败");
        messageMap.put(GAME_WS_ABANDON_FAIL, "放弃失败");
        messageMap.put(GAME_ATTEND_SUCCESS, "参与成功");
        messageMap.put(GAME_ATTEND_FAIL, "参与失败");
        messageMap.put(GAME_ATTEND_REPEAT, "重复参与");
        messageMap.put(GAME_WS_USER_ONLINE_ERROR, "用户上线异常");
        messageMap.put(GAME_WS_HEARTBEAT, "heartbeat");
    }
}
