package demo1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test1 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		int i = 0;
		while(true){
			
			Date date=new Date(System.currentTimeMillis());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(date);
			i++;
			System.out.println(i);
			//每一秒刷新下时间
			try {
			Thread.sleep(1000*60);//sleep是以ms为单位
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}
