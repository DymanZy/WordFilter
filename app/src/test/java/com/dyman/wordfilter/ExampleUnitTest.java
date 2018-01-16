package com.dyman.wordfilter;

import android.util.Log;

import com.dyman.wordfilter.bean.StringSearch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    StringSearch stringSearch = new StringSearch();

    @Test
    public void addition_isCorrect() throws Exception {

        long time = System.currentTimeMillis();

        String text = "法?轮/功和毒品在中国是被禁止的。";
        readBadWord();
//        findAll(text);
//        findFirst(text);
        replaceWord(text);

        System.out.println("耗时： " + (System.currentTimeMillis() - time));
    }

    private void readBadWord() {
        String[] badWord = new String[]{"法轮功", "党", "中国", "毒品"};

        List<String> list = new ArrayList<>();
        for (String word : badWord) {
            list.add(word);
        }

        stringSearch.setKeywords(list);
    }

    private void findAll(String text) {
        List<String> result = stringSearch.findAll(text);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private void findFirst(String text) {
        String filterWord = stringSearch.findFirst(text);
        System.out.println(filterWord == null ? "没有敏感词" : "第一个敏感词： " + filterWord);
    }

    private void replaceWord(String text) {
        String result = stringSearch.replace(text);
        System.out.println(result == null ? "没有检测出敏感词" : result);
    }
}