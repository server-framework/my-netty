package com.mjump.game.ws.util;

/**
 * @author
 * @date 2018/3/14
 */
public class ResultUtil {

    private static <T> Result<T> fillResult(int retCode, String message, String debug, T model) {
        Result<T> result = new Result();
        result.setRet(retCode);
        result.setMsg(message);
        result.setDebug(debug);
        result.setModel(model);
        return result;
    }

    public static <T> Result<T> getSuccess() {
        return fillResult(ResultCode.SUCCESS, ResultCode.get(ResultCode.SUCCESS), null, null);
    }

    public static <T> Result<T> getSuccess(T model) {
        return fillResult(ResultCode.SUCCESS, ResultCode.get(ResultCode.SUCCESS), null, model);
    }

    public static <T> Result<T> getFail(String debug) {
        return fillResult(ResultCode.FAIL, ResultCode.get(ResultCode.FAIL), debug, null);
    }

    public static <T> Result<T> get(int resultCode) {
        return fillResult(resultCode, ResultCode.get(resultCode), null, null);
    }
    
    public static <T> Result<T> get(int resultCode, String message) {
        return fillResult(resultCode, message, null, null);
    }

    public static <T> Result<T> get(int resultCode, T model) {
        return fillResult(resultCode, ResultCode.get(resultCode), null, model);
    }
    
    /**
     * 内部模块返回接口成功判断
     *
     * @param result
     * @return
     */
    public static boolean isSuccess(Result result) {
        return result != null ? result.getRet() == ResultCode.SUCCESS : false;
    }
}
