 package com.action;

 import java.util.*;

 public class UserThread extends Thread {
 	//double[][] GasPoint = new double[17][17];
 	//创建两个线程，一个用来计算最短路径，一个用来接收用户输入
	
 	private ShareData share = null;
 	public UserThread (){
 		
 	}
 	public UserThread(ShareData share){
 		this.share = share;
 	}
 	//当前车所在路段，在此路段行走距离，以及由近及远的加油站
 	static ArrayList array = new ArrayList();
 	//全程的路线
 	static ArrayList gps = new ArrayList();

 	public void begin(){
 			ShareData share = new ShareData();
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
 	public void setIfornot(int ifornot){
 		ShareData.ifornot = ifornot;
 	}
 	public int getIfornot(){
 		return ifornot;
 	}
 }



 class RunThread  implements Runnable{
 	private ShareData share1 = null;
 	public samplenear map = new samplenear();
 	UserThread test = new UserThread();
 	public RunThread(ShareData share1){
 		this.share1 = share1;
 	}
 		public void run(){
 				try{

 					map.Initial();;
 					map.getPosition(50);
 					
 					long startTime=System.currentTimeMillis();
 					while(map.cur.getX()<=GasPoint.point[GasPoint.point.length-1].getX()-50&&map.cur.getY()<=GasPoint.point[GasPoint.point.length-1].getY())
 					{
 				
 						Thread.sleep(2000);
 						map.getPosition(50);
 						map.getDistance(map.cur);
 						System.out.println(map.cur.getX()+" ,"+map.cur.getY());
 	 					map.updatePath();
 						UserThread.array = map.jsonarray;
 						map.getGas();
 						for(int i = 0 ; i < map.Gas.size() ; i++){
 							
								UserThread.array.add(map.Gas.get(i));
	 							UserThread.array.add(map.Gasproperty.get((int) map.Gas.get(i)).getProperty());
	 							UserThread.array.add(map.Gasproperty.get((int)map.Gas.get(i)).getPrice());
						}
//// 							if(share1.getFlag_go()!= 0)
//// 								{
// 							
// 								//备选加油站
// 								map.getGas();
// 							
// 							
//// 								if(share1.getGas_number() == 0)
//// 								{
// 									while(true)
// 									{
// 										Thread.sleep(3000);
// 										if(share1.getGas_number() != 0)
// 										{
// 											map.choice_path(share1.getGas_number());
// 											break;
// 										}
// 									}	
// 								System.out.print("完整的路径为：");
// 								for(int i = 0 ; i < map.mylist.size() ; i ++){
// 									System.out.print(map.mylist.get(i)+"->");
// 									
// 								}
// 								share1.setFlag_go(0);
// 								share1.setGas_number(0);
// 								
// 								UserThread.array.clear();
// 				//				}
// 				//			}
 						System.out.println("share1,dd"+share1.getIfornot());
 						if(share1.getIfornot()!=0){
							
							
							while(true)
							{
								Thread.sleep(1000);		
								if(share1.getGas_number()!=0){
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
 					long endTime=System.currentTimeMillis();
 					long time = endTime - startTime;
 					System.out.println("user运行时间："+time);
 					System.out.println("算法运行时间"+map.totaltime);
 				}catch(Exception e ){
 					
 					e.printStackTrace();
 				}		
 	}
 }
 class WaitThread implements Runnable{  
 	private ShareData share2 = null;
 	//JsonAction ja = new JsonAction();
 	public WaitThread(ShareData share2){
 		this.share2 = share2;
 	}
 	

 	public void run(){
 		try{
 			while(true){
// 				if(ShareData.flag_go == 1)
// 					break;
 				Scanner sc = new Scanner(System.in);
 			//	int flag = sc.nextInt();
 			//	share2.setFlag_go(flag);
 				int gasnum = sc.nextInt();
 				share2.setGas_number(gasnum);
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 	}
 	
 }



