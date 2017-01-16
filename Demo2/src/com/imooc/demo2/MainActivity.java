package com.imooc.demo2;
/*
 * AutoCompletemTextView��MultiAutoCompleteTextView����ǰ��ֻ��ƥ��һ�������߿�ƥ������ÿ���÷ָ���
 * �ֿ�
 */
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
import android.widget.MultiAutoCompleteTextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private AutoCompleteTextView acTextView;
	private MultiAutoCompleteTextView macTextView;
	private String[] res = {"beijing1","beijing2","beijing3","shanghai1","shanghai2","android"};
   
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * ��һ������ʼ���ؼ�
         * �ڶ�������Ҫһ���������������������ƥ���������ݣ�
         * ����������ʼ������Դ--������Դȥƥ���ı������������
         * ���Ĳ�����adepter�뵱ǰAutoCompleteTextView�ؼ���
         */
        acTextView =(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
       //��򵥵�������
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, res);
        acTextView.setAdapter(adapter);
        
        /*MultiAutoCompleteTextView
         * ��һ������ʼ���ؼ�
         * �ڶ�������Ҫһ���������������������ƥ���������ݣ�
         * ����������ʼ������Դ--������Դȥƥ���ı������������
         * ���Ĳ�����adepter�뵱ǰMultiAutoCompleteTextView�ؼ���
         * ���岽�����÷ָ���
         */
        macTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
        macTextView.setAdapter(adapter);
        //���÷ָ���������
        macTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}

}
