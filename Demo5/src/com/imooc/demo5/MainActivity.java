package com.imooc.demo5;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity {
	private CheckBox checkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化
        checkbox = (CheckBox) findViewById(R.id.checkBox1);
        //通过设置checkbox的监听事件来判断checkbox是不是被选中
        checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// 匿名内部类通过 onCheckedChanged来监听checkbox是否被选中
				Log.i("tag", arg1+"");
				if(arg1){
					//获得checkbox的文本内容
					String text = checkbox.getText().toString();
					Log.i("tag", text);
				}
				
			}
		});
    }

}
