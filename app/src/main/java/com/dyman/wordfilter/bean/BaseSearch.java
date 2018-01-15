package com.dyman.wordfilter.bean;

import com.dyman.wordfilter.internal.TrieNode;

import java.util.HashMap;
import java.util.List;

/**
 * Created by dyman on 2018/1/13.
 * 敏感词搜索类的基类
 */

public class BaseSearch {

    protected TrieNode _root = new TrieNode();
    protected  TrieNode[] _first = new TrieNode[Character.MAX_VALUE + 1];

    /** 设置关键字 */
    public void setKeywords(List<String> keywords) {

        TrieNode[] first = new TrieNode[Character.MAX_VALUE + 1];
        TrieNode root = new TrieNode();

        for(String item : keywords) {
            if (item == null || item == "") continue;

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

    public void tryLinks(TrieNode node, TrieNode node2, HashMap<TrieNode, TrieNode> links) {
        for (char c : node.m_value.keySet()) {
            TrieNode tn = null;
            if (node2 == null) {
                tn = _first[c];
                if (tn != null) {
                    links.put(node.m_value.get(c), tn);
                }
            } else {
                tn = node2.tryGetValue(c);
                if (tn != null) {
                    links.put(node.m_value.get(c), tn);
                }
            }
            tryLinks(node.m_value.get(c), tn, links);
        }
    }

}
