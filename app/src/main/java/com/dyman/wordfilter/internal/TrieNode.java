package com.dyman.wordfilter.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dyman on 2018/1/13.
 * 字典树结构体
 */

public class TrieNode {

    public boolean isEnd;
    private List<String> results;
    public HashMap<Character, TrieNode> m_value;
    //  0~65535
    private int minFlag = Character.MIN_VALUE;
    private int maxFlag = Character.MAX_VALUE;

    public TrieNode() {
        m_value = new HashMap<>();
        results = new ArrayList<>();
    }

    public TrieNode tryGetValue(char c) {
        if (minFlag <= (int)c && maxFlag >= (int)c) {
            if (m_value.containsKey(c)) {
                return m_value.get(c);
            }
        }
        return null;
    }

    public Collection<TrieNode> transitions() {
        return m_value.values();
    }

    public TrieNode add(char c) {
        TrieNode node;

        if (m_value.containsKey(c)) {
            return m_value.get(c);
        }

        if (minFlag > (int)c) {minFlag = (int)c;}
        if (maxFlag < (int)c) {maxFlag = (int)c;}

        node = new TrieNode();
        m_value.put(c, node);
        return node;
    }

    public void setResults(String text) {
        if (!isEnd) {
            isEnd = true;
        }
        if (!results.contains(text)) {
            results.add(text);
        }
    }

    public void merge(TrieNode node, HashMap<TrieNode, TrieNode> links) {
        if (node.isEnd) {
            if (!isEnd) {
                isEnd = true;
            }
            for(String item : node.getResults()) {
                if (!results.contains(item)) {
                    results.add(item);
                }
            }
        }

        for (char key : node.m_value.keySet()) {
           if (!this.m_value.containsKey(key)) {
               if (minFlag > (int)key) {minFlag = (int)key;}
               if (maxFlag < (int)key) {maxFlag = (int)key;}
               this.m_value.put(key, node.m_value.get(key));
           }
        }

        TrieNode node2;
        if (links.containsKey(node)) {
            node2 = links.get(node);
            merge(node2, links);
        }
    }

    public List<String> getResults() {
        return results;
    }

    public TrieNode[] ToArray() {
        TrieNode[] first = new TrieNode[Character.MAX_VALUE + 1];
        for(char item : m_value.keySet()) {
            first[item] = m_value.get(item);
        }
        return first;
    }
}
