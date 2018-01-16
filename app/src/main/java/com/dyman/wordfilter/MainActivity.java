package com.dyman.wordfilter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        example();
    }

    private void example() {

        String text = "待检测的语句";

        FilterManager filterManager = FilterManager.getInstance(this, FilterManager.FilterType.Base);
        filterManager.loadDefaultKeyWords();
        List<String> result = filterManager.findAll(text);

        Log.d("TAG", " 检测到敏感词数：" + result.size());
    }

}
