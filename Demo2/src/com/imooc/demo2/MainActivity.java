package com.imooc.demo2;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private AutoCompleteTextView acTextView;
	private String[] res = {"beijing1","beijing2","beijing3","shanghai1","shanghai2","android"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * 第一步：初始化控件
         * 第二步：需要一个适配器（用这个适配器匹配输入内容）
         * 第三步，初始化数据源--这数据源去匹配文本框输入的内容
         * 第四步：将adepter与当前AutoCompleteTextView控件绑定
         */
        acTextView =(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
       //最简单的适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
        acTextView.setAdapter(adapter);
    }

}
