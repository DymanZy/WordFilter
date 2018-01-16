package com.dyman.wordfilter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.dyman.wordfilter.bean.StringSearch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        example();
    }


    private void example() {
        long time = System.currentTimeMillis();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.talk)));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FilterManager filterManager = FilterManager.getInstance(this, FilterManager.FilterType.Base);

        long temp = System.currentTimeMillis();
        filterManager.loadDefaultKeyWords();
        Log.d("SpendTime", "加载词库耗时：" + (System.currentTimeMillis() - temp));

        temp = System.currentTimeMillis();
        List<String> result = filterManager.findAll(sb.toString());
        Log.d("SpendTime", "过滤耗时：" + (System.currentTimeMillis() - temp));

        for (String str : result) {
            Log.d("敏感词", str);
        }

        Log.d("SpendTime", "总耗时：" + (System.currentTimeMillis() - time));
    }

}
