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
         * ��ʼ��
         * ʵ��radioGroup�����¼�(ͨ��ʵ�ֽӿڵķ�ʽʵ�ּ�������һ�������֣�)
         */
        rg = (RadioGroup) findViewById(R.id.radioGroup1);
        rg.setOnCheckedChangeListener(this);
   
    }
	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch(arg1){
		case R.id.radio0:Log.i("tag", "�㵱ǰ��һ���к�");
			break;
		case R.id.radio1:Log.i("tag","�㵱ǰ��һ��Ů��");
		
		}
		
	}


}
