import java.util.Scanner;

public class ThreadTest extends Thread {
	//创建两个线程，一个用来计算最短路径，一个用来接收用户输入

	public static void main(String[] args){
		ShareData share = new ShareData();
		ThreadTest tt = new ThreadTest();
		RunThread rt = new RunThread(share);
		WaitThread wt = new WaitThread(share);
		Thread thread1 = new Thread(rt);
		Thread thread2 = new Thread(wt);
		thread1.start();
		thread2.start();
	}
	}
class ShareData {
	private int gas_number = 0 ;
	private int flag_go = 0 ;
	
	public int getGas_number(){
		return gas_number;
	}
	public void setGas_number(int gas_number){
		this.gas_number = gas_number;
	}
	public int getFlag_go(){
		return flag_go;
	}
	public void setFlag_go(int flag_go){
		this.flag_go = flag_go;
	}
}
class RunThread extends MapTest implements Runnable{
	private ShareData share1 = null;
	public RunThread(ShareData share1){
		this.share1 = share1;
	}
		public void run(){
				try{
					MapTest map = new MapTest();
					map.cheapestPath();
					map.timer();
					while((int)map.mypath.get(map.mypath.size()-1)!=(map.GasPoint.length-1))
					{
						Thread.sleep(3000);
						map.timer();
						if(share1.getFlag_go()!= 0)
						{
						//备选加油站
						map.printPath();
						
						if(share1.getGas_number() == 0)
							while(true)
							{
								Thread.sleep(2000);
								if(share1.getGas_number() != 0)
								{
									map.choice_path(share1.getGas_number());
									break;
								}
							}	
						System.out.print("完整的路径为：");
						for(int i = 0 ; i < map.mylist.size() ; i ++){
							System.out.print(map.mylist.get(i)+"->");
						}
						share1.setFlag_go(0);
						share1.setGas_number(0);
					}
						
						else{
						System.out.print("完整的路径为：");
						for(int i = 0 ; i < map.mylist.size() ; i ++){
							System.out.print(map.mylist.get(i)+"->");
						}
						
					}
					System.out.println();
					continue;
					}						
				}catch(Exception e ){
					e.printStackTrace();
				}
			
	}
}

class WaitThread  extends MapTest implements Runnable{  
	private ShareData share2 = null;
	public WaitThread(ShareData share2){
		this.share2 = share2;
	}
	public  void inputFlag_go(){
		System.out.println("是否前去加油站");
		Scanner sc = new Scanner(System.in);
		share2.setFlag_go(sc.nextInt()); 
	}
	public void inputGas_number()
	{
		System.out.println("选择加油站的编号");
		Scanner sc = new Scanner(System.in);
		share2.setGas_number(sc.nextInt());
	}
	public void run(){
		try{
			MapTest map = new MapTest();
			while(true){
//				
				inputFlag_go();
				//输出备选加油站
				inputGas_number();
//				share2.setFlag_go(0);
//				share2.setGas_number(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}