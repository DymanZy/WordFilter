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

        String text = "法轮功和毒品在中国是被禁止的。";
        readBadWord();
        List<String> result = stringSearch.findAll(text);
        for (String s : result) {
            System.out.println(s);
        }

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
}