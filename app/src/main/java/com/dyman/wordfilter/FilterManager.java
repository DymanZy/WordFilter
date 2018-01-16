package com.dyman.wordfilter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.dyman.wordfilter.bean.BaseSearch;
import com.dyman.wordfilter.bean.FuzzySearch;
import com.dyman.wordfilter.bean.StringSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyman on 2018/1/16.
 */

public class FilterManager {
    private static final String TAG = "FilterManager";
    private static Context mContext;
    private BaseSearch baseSearch;

    public enum FilterType {Base, Fuzzy}

    private static FilterManager instance = null;
    public synchronized static FilterManager getInstance(Context context, FilterType type) {
        if (instance == null) {
            mContext = context;
            instance = new FilterManager(type);
        }
        return instance;
    }

    private FilterManager(FilterType type) {
        switch (type) {
            case Base:
                baseSearch = new StringSearch();
                break;
            case Fuzzy:
                baseSearch = new FuzzySearch();
                break;
        }
    }

    /** 默认加载自带敏感词组 */
    public void loadDefaultKeyWords() {
        InputStream is = mContext.getResources().openRawResource(R.raw.badword);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            List<String> mlist = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null) {
                mlist.add(line);
            }
            setKeyWords(mlist);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Failure to load default KeyWords.");
        }
    }

    /** 自定义设置敏感词词组 */
    public void setKeyWords(List<String> list) {
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
