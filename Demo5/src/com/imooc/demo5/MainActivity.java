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
        //��ʼ��
        checkbox = (CheckBox) findViewById(R.id.checkBox1);
        //ͨ������checkbox�ļ����¼����ж�checkbox�ǲ��Ǳ�ѡ��
        checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// �����ڲ���ͨ�� onCheckedChanged������checkbox�Ƿ�ѡ��
				Log.i("tag", arg1+"");
				if(arg1){
					//���checkbox���ı�����
					String text = checkbox.getText().toString();
					Log.i("tag", text);
				}
				
			}
		});
    }

}
