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
			//ÿһ��ˢ����ʱ��
			try {
			Thread.sleep(1000*60);//sleep����msΪ��λ
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
}
