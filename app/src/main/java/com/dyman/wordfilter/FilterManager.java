package com.dyman.wordfilter;

import android.content.Context;
import android.util.Log;

import com.dyman.wordfilter.search.BaseSearch;
import com.dyman.wordfilter.search.StringSearch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyman on 2018/1/16.
 */

public class FilterManager {

    private BaseSearch baseSearch;
    private Context mContext;
    private List<String> badWordList;
    public enum FilterType {Base, /*Fuzzy*/}

    private static FilterManager instance = null;
    public synchronized static FilterManager getInstance(Context context, FilterType type) {
        if (instance == null) {
            instance = new FilterManager(context, type);
        }
        return instance;
    }

    private FilterManager(Context context, FilterType type) {
        this.mContext = context;
        switch (type) {
            case Base:
                baseSearch = new StringSearch();
                break;
//            case Fuzzy:
//                baseSearch = new FuzzySearch();
//                break;
        }
    }

    /** 加载默认敏感词组 */
    public void loadDefaultKeyWords() {
        badWordList = new ArrayList<>();
        InputStream is = mContext.getResources().openRawResource(R.raw.badword);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while((line = reader.readLine()) != null) {
                badWordList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(GlobalConfig.TAG, "Failure to load default KeyWords.");
        }

        baseSearch.setKeywords(badWordList);
    }

    /** 加载自定义敏感词词组 */
    public void loadCustomKeyWords(List<String> list) {
        baseSearch.setKeywords(list);
    }

    public String findFirst(String text) {
        return baseSearch.findFirst(text);
    }

    public List<String> findAll(String text) {
        return baseSearch.findAll(text);
    }

    public boolean containsAny(String text) {
        return baseSearch.containsAny(text);
    }

    public String replace(String text) {
        return baseSearch.replace(text);
    }

    public String replace(String text, char replaceChar) {
        return baseSearch.replace(text, replaceChar);
    }

}
