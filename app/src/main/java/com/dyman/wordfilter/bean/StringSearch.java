package com.dyman.wordfilter.bean;

import com.dyman.wordfilter.internal.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyman on 2018/1/13.
 */

public class StringSearch extends BaseSearch {

    /** 找出文本中第一个敏感词 */
    public String findFirst(String text) {
        TrieNode ptr = null;
        for(char t : text.toCharArray()) {
            TrieNode tn;
            if (ptr == null) {
                tn = _first[t];
            } else {
                tn = ptr.tryGetValue(t);
                if (tn == null) {
                    tn = _first[t];
                }
            }

            if (tn != null) {
                if (tn.isEnd) {
                    return tn.getResults().get(0);
                }
            }
            ptr = tn;
        }
        return null;
    }

    /** 找出文本中所有的敏感词 */
    public List<String> findAll(String text) {
        TrieNode ptr = null;
        List<String> list = new ArrayList<>();

        for(char t : text.toCharArray()) {
            TrieNode tn = null;
            if (ptr == null) {
                tn = _first[t];
            } else {
                tn = ptr.tryGetValue(t);
                if (tn == null) {
                    tn = _first[t];
                }
            }

            if (tn != null) {
                if (tn.isEnd) {
                    for (String str : tn.getResults()) {
                        list.add(str);
                    }
                }
            }
            ptr = tn;
        }
        return list;
    }

    /** 检查文本是否含有敏感词 */
    public boolean containsAny(String text) {
        TrieNode ptr = null;
        for(char t : text.toCharArray()) {
            TrieNode tn;
            if (ptr == null) {
                tn = _first[t];
            } else {
                tn = ptr.tryGetValue(t);
                if (tn == null) {
                    tn = _first[t];
                }
            }

            if (tn != null) {
                if (tn.isEnd) {
                    return true;
                }
            }
            ptr = tn;
        }
        return false;
    }

    /** 将文本中的敏感词替换为'*' */
    public String replace(String text) {
        return replace(text, '*');
    }

    /** 将文本中的敏感词替换为 replaceChar */
    public String replace(String text, char replaceChar) {
        StringBuilder sb = new StringBuilder(text);
        boolean isChanged = false;
        TrieNode ptr = null;

        int length = text.toCharArray().length;
        for (int i = 0; i < length; i++) {
            char t = text.toCharArray()[i];
            TrieNode tn;
            if (ptr == null) {
                tn = _first[t];
            } else {
                tn = ptr.tryGetValue(t);
                if (tn == null) {
                    tn = _first[t];
                }
            }

            if (tn != null) {
                if (tn.isEnd) {
                    isChanged = true;
                    int maxLength = tn.getResults().get(0).length();
                    int start = i + 1 - maxLength;
                    for (int j = start; j <= i; j++) {
                        sb.setCharAt(j, replaceChar);
                    }
                }
            }
            ptr = tn;
        }
        return isChanged == true ? sb.toString() : null;
    }

}
