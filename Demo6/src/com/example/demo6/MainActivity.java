package com.example.demo6;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity implements OnCheckedChangeListener {
		private RadioGroup rg;
		private RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*
         * 初始化
         * 实现radioGroup监听事件(通过实现接口的方式实现监听器，一共有三种！)
         */
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rg.setOnCheckedChangeListener(this);
   
    }
	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch(arg1){
		case R.id.radio0:Log.i("tag", "你当前是一个男孩");
			break;
		case R.id.radio1:Log.i("tag","你当前是一个女孩");
		
		}
		
	}


}
