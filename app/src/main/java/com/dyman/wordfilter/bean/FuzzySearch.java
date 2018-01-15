package com.dyman.wordfilter.bean;

import com.dyman.wordfilter.internal.TrieNode;
import com.dyman.wordfilter.util.WordHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dyman on 2018/1/13.
 * 模糊搜索，文字转拼音，再进行搜索
 */

public class FuzzySearch extends BaseSearch{

    @Override
    public void setKeywords(List<String> keywords) {
        TrieNode[] first = new TrieNode[Character.MAX_VALUE + 1];
        TrieNode root = new TrieNode();

        for(String item : keywords) {
            if (item == null || item == "") continue;

            if (WordHelper.isContainChinese(item)) {
                //  TODO: 检测到中文，转为拼音再操作
            }

            TrieNode tn = _first[item.toCharArray()[0]];
            if (tn == null) {
                tn = root.add(item.toCharArray()[0]);
                first[item.toCharArray()[0]] = tn;
            }
            for (int i = 1; i < item.length(); i++) {
                tn = tn.add(item.toCharArray()[i]);
            }
            tn.setResults(item);
        }
        this._first = first;

        HashMap<TrieNode, TrieNode> links = new HashMap<>();
        for (char c : root.m_value.keySet()) {
            tryLinks(root.m_value.get(c), null, links);
        }

        for (TrieNode item : links.keySet()) {
            item.merge(links.get(item), links);
        }
        _root = root;
    }

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

}
