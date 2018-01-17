package com.dyman.wordfilter.search;

import com.dyman.wordfilter.internal.TrieNode;
import com.dyman.wordfilter.util.WordHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyman on 2018/1/13.
 */

public class StringSearch extends BaseSearch {

    /** 找出文本中第一个敏感词 */
    @Override
    public String findFirst(String text) {
        text = WordHelper.stringFilter(text);

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
    @Override
    public List<String> findAll(String text) {
        text = WordHelper.stringFilter(text);

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
    @Override
    public boolean containsAny(String text) {
        text = WordHelper.stringFilter(text);

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
    @Override
    public String replace(String text) {
        return replace(text, '*');
    }

    /** 将文本中的敏感词替换为 replaceChar */
    @Override
    public String replace(String text, char replaceChar) {
        text = WordHelper.stringFilter(text);

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
