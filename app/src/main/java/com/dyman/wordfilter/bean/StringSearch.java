package com.dyman.wordfilter.bean;

import com.dyman.wordfilter.internal.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyman on 2018/1/13.
 */

public class StringSearch extends BaseSearch {

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

    public String replace(String text) {
        return replace(text, '*');
    }

    //  TODO: 将文本中的敏感词替换为 replaceChar
    public String replace(String text, char replaceChar) {
        return text;
    }

}
