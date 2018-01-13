package com.dyman.wordfilter.util;

import java.math.BigDecimal;

/**
 * Created by dyman on 2018/1/13.
 *  文字处理帮助类
 */

public class WordHelper {

    /** 半角转全角 */
    public static String toSBC(String text) {
        return text;
    }

    /** 全角转半角 */
    public static String toDBC(String text) {
        return text;
    }

    /** 转为繁体中文 */
    public static String toTraditionalChinese(String text) {
        return text;
    }

    /** 转为简体中文 */
    public static String toSimplifiedChinese(String text) {
        return text;
    }

    /** 数字转中文大写（应用于中文金额） */
    public static String toChineseRMB(double x) {
        return ""+x;
    }

    public static long toNumber(String chineseString) {
        return 0;
    }

}
