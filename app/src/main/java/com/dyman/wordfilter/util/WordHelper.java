package com.dyman.wordfilter.util;

import com.dyman.wordfilter.internal.Dict;

import java.math.BigDecimal;

/**
 * Created by dyman on 2018/1/13.
 *  文字处理帮助类
 */

public class WordHelper {

    /** 检测是否含有中文 */
    public static boolean isContainChinese(String text) {
        char[] charArray = text.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
                return true;
            }
        }
        return false;
    }

    /** 检测是否全为中文 */
    public static boolean isAllChinese(String text) {
        char[] charArray = text.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if (charArray[i] < 0x4e00 || charArray[i] > 0x9fbb) {
                return false;
            }
        }
        return true;
    }

    /** 半角转全角 */
    public static String toSBC(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            char c = text.toCharArray()[i];
            if (c == 32) {
                sb.setCharAt(i, (char)12288);
            } else if (c < 127) {
                sb.setCharAt(i, (char)(c + 65248));
            }
        }
        return sb.toString();
    }

    /** 全角转半角 */
    public static String toDBC(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            char c = text.toCharArray()[i];
            if (c == 12288) {
                sb.setCharAt(i, (char)32);
            } else if (c > 65280 && c < 65375) {
                sb.setCharAt(i, (char)(c - 65248));
            }
        }
        return sb.toString();
    }

    /** 转为繁体中文 */
    public static String toTraditionalChinese(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            char c = text.toCharArray()[i];
            if (c >= 0x4e00 && c <= 0x9fa5) {
                char k = Dict.Traditional.toCharArray()[c - 0x4e00];
                if (k != c) {
                    sb.setCharAt(i, k);
                }
            }
        }
        return sb.toString();
    }

    /** 转为简体中文 */
    public static String toSimplifiedChinese(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            char c = text.toCharArray()[i];
            if (c >= 0x4e00 && c <= 0x9fa5) {
                char k = Dict.Simplified.toCharArray()[c - 0x4e00];
                if (k != c) {
                    sb.setCharAt(i, k);
                }
            }
        }
        return sb.toString();
    }
}
