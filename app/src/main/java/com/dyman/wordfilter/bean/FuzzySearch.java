package com.dyman.wordfilter.bean;
import java.util.List;

/**
 * Created by dyman on 2018/1/13.
 * 模糊搜索，文字转拼音，再进行搜索
 */

public class FuzzySearch extends BaseSearch{

    @Override
    public String findFirst(String text) {
        return null;
    }

    @Override
    public List<String> findAll(String text) {
        return null;
    }

    @Override
    public boolean containsAny(String text) {
        return false;
    }

    @Override
    public String replace(String text) {
        return null;
    }

    @Override
    public String replace(String text, char replaceChar) {
        return null;
    }
}
