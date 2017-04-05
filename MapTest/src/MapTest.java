import java.util.*;
public class MapTest {
					   //V0,V1,V2,V3,V4,V5,V6,V7,V8,V9,V10,V11,V12,V13,V14,V15,V16	
					 //两点之间距离，单位千米，车速60km/h = 1km/min;
	private static int max = Integer.MAX_VALUE;
	int[][] GasPoint = {{0,120,110,max,max,max,max,max,max,max,100,max,max,max,max,max,max},
						{120,0,max,max,max,max,max,max,max,max,max,150,max,max,max,max,max},
						{110,max,0,max,max,max,max,max,max,max,50,max,max,max,max,max,max},
						{max,max,max,0,max,max,max,max,max,max,110,max,150,max,max,max,max},
						{max,max,max,max,0,max,max,max,max,max,max,60,max,max,200,max,max},
						{max,max,max,max,max,0,max,max,max,max,max,60,60,max,max,max,max},
						{max,max,max,max,max,max,0,max,max,max,max,max,50,max,90,max,max},
						{max,max,max,max,max,max,max,0,max,max,max,max,max,90,70,max,max},
						{max,max,max,max,max,max,max,max,0,max,max,max,max,max,70,max,200},
						{max,max,max,max,max,max,max,max,max,0,max,max,max,max,max,60,70},
						{100,max,50,110,max,max,max,max,max,max,0,110,max,max,max,max,max},
						{max,150,max,max,60,60,max,max,max,max,110,0,60,max,max,max,max},
						{max,max,max,150,max,60,50,max,max,max,max,60,0,50,max,max,max},
						{max,max,max,max,max,max,max,90,max,max,max,max,50,0,80,max,max},
						{max,max,max,max,200,max,90,70,70,max,max,max,max,80,0,70,max},
						{max,max,max,max,max,max,max,max,max,60,max,max,max,max,70,0,80},
						{max,max,max,max,max,max,max,max,200,70,max,max,max,max,max,80,0}};	
	int size = GasPoint.length;
	private int[][] dist = new int[size][size];
	private int[][] path = new int[size][size];
	int[] gas_station = {1,2,3,4,5,6,7,8,9};	//加油站的下标
	int[]shortest_path = new int[gas_station.length];//到各个加油站的最短路径
	int[] shortest_choice = new int[gas_station.length];//选择前一个节点还是后一个节点
	int distance = 0;	//车已经走过的路程
	int predist = 0,nextdist = 0 ; 	//相邻两点距离（前后）
	ArrayList mypath = new ArrayList();	//前后两点
	ArrayList mylist = new ArrayList();	//存路径
	ArrayList lists = new ArrayList();	//存更新之后的路径
	ArrayList list = new ArrayList();
	ArrayList curpath = new ArrayList();//存当前已走过的路径和选择加油站之后要走的路径
	ArrayList temppath = new ArrayList();
	public void cheapestPath(){
		Floyd(GasPoint);
		//print();
		initial();
	}
	public void Floyd(int[][] GasPoint){
		//弗洛伊德算法求两点间最短路径
		
		for(int i = 0 ; i < size; i ++)
		{
			for(int j = 0 ; j < size ; j ++){
				dist[i][j] = GasPoint[i][j];
				path[i][j] = j;
				
			}
		}
		for(int k = 0 ; k < size ; k ++)
			for(int i = 0 ; i < size ; i ++)
				for(int j = 0 ; j < size ; j ++){
					int ij = dist[i][j];
					int ik = dist[i][k];
					int kj = dist[k][j];
					int add = (ik==max||kj==max)?max:ik+kj;
					if(ij>add)
					{
						dist[i][j] = add;
						path[i][j] = path[i][k];
					}
				}
	}
	public void print(){
		for(int m = 0 ; m < size ; m ++ )
			for(int n = m+1; n < size ; n ++)
			{
				int k = path[m][n];
				System.out.print("("+m+","+n+")"+dist[m][n]+":");
				System.out.print(m);
				while(k!=n){
					System.out.print("->"+k);
					k = path[k][n];
				}
			System.out.println("->"+n);
			}
		System.out.println();
		
		for(int i = 0 ; i < size ; i ++)
		{
			{
				for(int j = 0 ; j <size ; j ++)
					System.out.print(path[i][j]+",");
			}
			System.out.println();
		}
	}
	//初始化路径mylist
	public void initial(){
		int k = 0 ;
		mylist.clear();
		mylist.add(path[k][k]);
		curpath.add(path[k][k]);
		temppath.add(path[k][k]);
		while(k!=size-1){
			mylist.add(path[k][size-1]);
			k = path[k][size-1];
		}
	}
	//获得当前汽车行驶的位置
	public void getPosition(int speed){
		//int k = 0;
		int dists = 0 ;	//整段距离
		mypath.clear();
		distance = distance+speed*1;	//行驶距离
		for(int i = 0 ; i < mylist.size()-1;i++){
			//System.out.print(mylist.get(i)+"->");		
				dists = dist[(int) mylist.get(i)][(int) mylist.get(i+1)]+dists ;	
			if(dists>distance)
			{
				
				//车在此路段
				mypath.add(mylist.get(i));
				mypath.add(mylist.get(i+1));
				System.out.println("the car in path:"+mylist.get(i)+","+mylist.get(i+1));
				
				nextdist = dists-distance;
				predist =dist[(int) mylist.get(i)][(int) mylist.get(i+1)]-nextdist;
				break;
			}
		}
		if(mypath.get(0)!=temppath.get(temppath.size()-1)&&(int)temppath.get(temppath.size()-1)!= size-1){
			temppath.add(mypath.get(0));
		}
		if(mypath.get(0)!=curpath.get(curpath.size()-1)&&((int)curpath.get(curpath.size()-1)!=size-1))
		{
			curpath.add(mypath.get(0));
			
		}
//		for(int i = 0 ; i < temppath.size() ; i ++)
//			System.out.println(temppath.get(i));
	}
	public void getDistance(){
		int pretemp = 0 ; int nexttemp = 0;
		//求此点经过加油站加油站到终点的最短距离
				for(int i = 0 ; i < gas_station.length; i++)
				{
					pretemp = dist[(int) mypath.get(0)][gas_station[i]]+dist[gas_station[i]][size-1]+predist;
					nexttemp = dist[(int)mypath.get(1)][gas_station[i]]+dist[gas_station[i]][size-1]+nextdist;
					shortest_path[i] = (pretemp>nexttemp)?nexttemp:pretemp;
					if(pretemp>nexttemp)
					{
						shortest_choice[i] =(int) mypath.get(1);						
					}
					else{
						shortest_choice[i] = (int) mypath.get(0);
					}
					
				}
				//冒泡排序，从小到大
				for(int i = 0 ; i < shortest_path.length ; i ++)
				{
					for(int j = 0 ; j < shortest_path.length-1-i ; j++){
						if(shortest_path[j]>shortest_path[j+1]){
							int temp1 = shortest_path[j];
							shortest_path[j] = shortest_path[j+1];
							shortest_path[j+1] = temp1;
							int temp2 = gas_station[j];
							gas_station[j] = gas_station[j+1];
							gas_station[j+1] = temp2;
							int temp3 = shortest_choice[j];
							shortest_choice[j] = shortest_choice[j+1];
							shortest_choice[j+1] = temp3;
						}
					}
				}
			
	}
	
	//更新路径,当前距离各个加油站的最短路径长度以及路径
	public void updatePath(){
		lists.clear();
		for(int i = 0 ; i < shortest_path.length;i++){
			
			int tmp = shortest_choice[i];
			list.add(shortest_choice[i]);
			while(tmp != gas_station[i]){
				list.add(path[tmp][gas_station[i]]);
				tmp = path[tmp][gas_station[i]];
			}
			tmp = gas_station[i];
			while(tmp != size-1)
			{
				list.add(path[tmp][size-1]);
				tmp = path[tmp][size-1];
			}
			lists.addAll(list);
			lists.add("\n");
			list.clear();
			
		}
	}
	//输出给用户备选路径
	public void printPath(){
		for(int i = 0 ; i < shortest_path.length ; i ++){
			System.out.println("near gas station is "+gas_station[i]+",the distance is :"+shortest_path[i]+" ");
		}
	}
	//选择路径
	public void choice_path(int path ){
//		for(int i = 0 ; i < lists.size() ; i ++){
//			System.out.print(lists.get(i));
//		}
		//System.out.println("到达加油站"+path+"路线为");
		curpath.clear();
		mylist.clear();
		curpath.addAll(temppath);
		
		int j = 0 ,enter = 0,i = 0 ;
		for(i = 0 ; i < gas_station.length ; i ++){
			if(path == gas_station[i]){
			break;
			}
		}
		for(j = 0 ; j <lists.size() ; j ++)
		{
			
			if(lists.get(j).equals("\n")){
				enter++;
			}
			if(enter == i&&lists.get(j)!="\n"&&lists.get(j)!=curpath.get(curpath.size()-1) ){	 
				curpath.add(lists.get(j));				
				}
			if(enter>9)
				enter = 0;
		}
			
		mylist.addAll(curpath);
		}
		
	public void timer(){
		getPosition(60);
		getDistance();
		updatePath();
//			Timer timer = new Timer();
//			timer.schedule(
//			new TimerTask() { 
//			public void run()
//			{ 
//				 
//				getPosition(50);
//				getDistance();
//				updatePath();
//				choice_path((int) (Math.random()*8+1));
//              lists.clear();
//				if((int)mypath.get(mypath.size()-1)==(GasPoint.length-1))
//			{
//					System.out.println("到达终点");
//					System.gc();
//					cancel();
//				}
//			}
//			}, 0, 1*1000);
		}
}	
