package com.demon.virtualwork.util;

/**
 * @author DeMon
 * Date 2021/5/27.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
public class Log {
    public static void i(String TAG, String content) {
        System.out.println(TimeUtils.getNowTimeStr() + "\\" + TAG + ": " + content);
    }
}
