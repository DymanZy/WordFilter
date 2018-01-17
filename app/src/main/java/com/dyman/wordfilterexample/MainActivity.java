package com.dyman.wordfilterexample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.dyman.wordfilter.FilterManager;

import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        example();
    }

    private void example() {

        String text = "￥黑@社#会%大佬来了。。。";

        FilterManager filterManager = FilterManager.getInstance(this, FilterManager.Type.BASE);
        filterManager.loadDefaultKeyWords();
        List<String> result = filterManager.findAll(text);

        Log.d("TAG", " 检测到敏感词数：" + result.size());
        Log.d("TAG", " 检测到敏感词数：" + result.get(0));
    }

}
