package com.action;


import java.util.ArrayList;
import java.util.Scanner;

public class UserThread extends Thread {
	//创建两个线程，一个用来计算最短路径，一个用来接收用户输入
	//当前车所在路段，在此路段行走距离，以及由近及远的加油站
		static ArrayList array = new ArrayList();
		//全程的路线
		static ArrayList gps = new ArrayList();
		private ShareData share = null;
		public UserThread (){
			
		}
		public UserThread(ShareData share){
			this.share = share;
		}
	public void begin(){
	
		ShareData share = new ShareData();
		UserThread tt = new UserThread();
		RunThread rt = new RunThread(share);
		WaitThread wt = new WaitThread(share);
		Thread thread1 = new Thread(rt);
		Thread thread2 = new Thread(wt);
		thread1.start();
		thread2.start();
	}
	
}
class ShareData {
	static int gas_number = 0 ;
	//K个加油站
	static int flag_go = 0 ;
static int ifornot = 0;
	
	public int getGas_number(){
		return gas_number;
	}
	public void setGas_number(int gas_number){
		ShareData.gas_number = gas_number;
	}
	public int getFlag_go(){
		return flag_go;
	}
	public void setFlag_go(int flag_go){
		ShareData.flag_go = flag_go;
	}
	public int getIfornot(){
		return ifornot;
	}
	public void setIfornot(int ifornot){
		ShareData.ifornot = ifornot;
	}
}
class RunThread  implements Runnable{
	private ShareData share1 = null;
	knn map = new knn();
	public RunThread(ShareData share1){
		this.share1 = share1;
	}
		public void run(){
				try{
					map.Initial();
					map.Dijkstra();
				//	map.getPosition(50);
				//	Point q = new Point(map.cur.getX(),map.cur.getY(),map.cur.getFront(),map.cur.getBeyond());
					long startTime = System.currentTimeMillis();
					int count = 0;
					while(map.cur.getX()<=GasPoint.point[GasPoint.point.length-1].getX()-50&&map.cur.getY()<=GasPoint.point[GasPoint.point.length-1].getY())
					{		
						Thread.sleep(2000);	
						if(map.ch_val!=0&&count<1){
							count++;
							map.bd.clear();
							map.Dijkstra();
						}
						map.getPosition(50);
						    UserThread.array = map.jsonarray;
							map.Filter(map.cur,share1.getFlag_go());
							map.getDistance(map.cur);
							map.updatePath();
							map.getGas();							
								for(int i = 0 ; i < map.Gas.size() ; i++){
	 							
 								UserThread.array.add(map.Gas.get(i));
 	 							UserThread.array.add(map.Gasproperty.get((int) map.Gas.get(i)).getProrperty());
 	 							UserThread.array.add(map.Gasproperty.get((int)map.Gas.get(i)).getPrice());
 						}
						for(int i = 0 ; i < UserThread.array.size() ; i++)
							System.out.println("array "+UserThread.array.get(i));
					//	Thread.sleep(2000);	
						
						if(share1.getIfornot()!=0){
							
							
							while(true)
							{
								Thread.sleep(1000);		
								//System.out.println("getgasnumber111111111111111111 "+share1.getGas_number());
								if(share1.getGas_number()!=0){
								//	System.out.println("getgasnumber2222222222222222222222222222 "+share1.getGas_number());
										
									
									map.choice_path(share1.getGas_number());
									
									break;
								}
								
							}	
						System.out.print("完整的路径为:");
						for(int i = 0 ; i < map.mylist.size() ; i ++){
							System.out.print(map.mylist.get(i)+"->");
						} 
						//share1.setFlag_go(3);
						share1.setGas_number(0);
						share1.setIfornot(0);
						UserThread.array.clear();
						
					}		
						else{
						System.out.print("完整的路径为：");
						for(int i = 0 ; i < map.mylist.size() ; i ++){
							System.out.print(map.mylist.get(i)+"->");
						}
						
					}
						UserThread.gps.clear();
						UserThread.gps.addAll(map.mylist);
					
					System.out.println();
					continue;
					}	
					long endTime = System.currentTimeMillis();
					long time = endTime - startTime;
					System.out.println("user运行时间"+time);
					System.out.println("算法运行"+map.totaltime);
				}catch(Exception e ){
					e.printStackTrace();
				}
			
	}
}

class WaitThread   implements Runnable{  
	private ShareData share2 = null;
	public WaitThread(ShareData share2){
		this.share2 = share2;
	}
	public void run(){
		try{
			while(true){
				if(ShareData.flag_go!=0)
					break; 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}