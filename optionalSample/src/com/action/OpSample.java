package com.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class OpSample {
	private int max = Integer.MAX_VALUE;
//	Point[] p = {new Point(0,0),new Point(100,150),new Point(200,0),new Point(400,200),new Point(450,650),new Point(550,300),new Point(800,420),new Point(800,1200),
//	new Point(1300,700),new Point(1200,1500),new Point(200,200),new Point(400,400),new Point(600,600),new Point(800,800),new Point(1000,1000),new Point(1200,1200),
//	new Point(1500,1500)};
	int[] generate = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
	ArrayList<Point> Gasproperty = new ArrayList<Point>();
	double[][]Gv_e = new double[GasPoint.point.length][GasPoint.point.length];
	//存储每个点对应的最近生成点集
	double[][] near = new double[Gv_e.length][GasPoint.point.length];//generate[generate.length-1]
	//边界点与相邻端点距离
	double[][]bound = new double[GasPoint.point.length][GasPoint.point.length];
	//边界点坐标
	ArrayList<Point> bd = new ArrayList<Point>();
	//floyd
	double[][] fdist = new double[GasPoint.point.length][GasPoint.point.length];
	int[][] fpath = new int[GasPoint.point.length][GasPoint.point.length];
	//存储相邻生成点之间的距离
	double[][] neardist = new double[generate.length][generate.length];
	//候选集 结果集
	ArrayList<Point> ResultSet = new ArrayList<Point>();
	Set<Point> CandidateSet = new HashSet<Point>();
	int[] prev = new int[GasPoint.point.length];
	double[] dist = new double[GasPoint.point.length];
	ArrayList Gas = new ArrayList();
	ArrayList mypath = new ArrayList();	//前后两点
	ArrayList mylist = new ArrayList<Comparable>();	//存路径
	ArrayList curpath = new ArrayList();//存当前已走过的路径和选择加油站之后要走的路径
	ArrayList temppath = new ArrayList();
	ArrayList list = new ArrayList();
	ArrayList lists = new ArrayList();
	int[] shortest_choice = new int[generate.length];//选择前一个节点还是后一个节点
	double[]shortest_path = new double[generate.length];//到各个加油站的最短路径距离
	Point cur = new Point();
	ArrayList jsonarray = new ArrayList();
	double distance = 0;	//车已经走过的路程
	double predist = 0,nextdist = 0 ; 	//相邻两点距离（前后）
	static int ch_val = 0;
	public long totaltime = 0 ;
	static int count = 0;
	public void Initial(){
		long start = System.currentTimeMillis();
		for(int i = 0 ; i < Gv_e.length ; i++){
			for(int j = 0 ; j < Gv_e.length ; j ++){
				Gv_e[i][j] = max;	
				if(i==j)Gv_e[i][j]=0;
				bound[i][j] = max;
				
			}
		}
		for(int i = 0 ; i < GasPoint.point.length ; i++){
			for(int j = 0 ; j < generate.length ; j++){
				near[i][j] = max;
				
			}
		}

		 Gv_e[0][30] = 1;  Gv_e[0][32] =1; 
		 Gv_e[1][30] = 1;  Gv_e[1][31] =1;
		 Gv_e[2][31] =1;   Gv_e[2][33] = 1;
		 Gv_e[3][32] = 1;  Gv_e[3][35] = 1;
		 Gv_e[4][32] = 1;  Gv_e[4][33] = 1;
		 Gv_e[5][33] = 1;  Gv_e[5][35] = 1;
		 Gv_e[6][34] = 1;  Gv_e[6][35] = 1;
		 Gv_e[7][35] = 1;  Gv_e[7][37] = 1;
		 Gv_e[8][36] = 1;  Gv_e[8][37] = 1;
		 Gv_e[9][31] = 1; Gv_e[9][32] = 1;
		 Gv_e[10][31] = 1; Gv_e[10][33] = 1;
		 Gv_e[11][30] = 1; Gv_e[11][32] = 1;
		 Gv_e[12][31] = 1; Gv_e[12][32] = 1;Gv_e[12][33] = 1;
		 Gv_e[13][33] = 1;
		 Gv_e[14][33] = 1; Gv_e[14][34] =1;
		 Gv_e[15][38] =1;Gv_e[15][39] =1;
		 Gv_e[16][34] =1;
		 Gv_e[17][32] =1; Gv_e[17][34] =1;
		 Gv_e[18][33] =1;Gv_e[18][35] =1;
		 Gv_e[19][37] = 1;Gv_e[19][38] = 1;
		 Gv_e[20][34] =1; Gv_e[20][35] = 1;
		 Gv_e[21][34] =1;Gv_e[21][36] = 1;
		 Gv_e[22][35] = 1;
		 Gv_e[23][35] =1 ;Gv_e[23][36] = 1;
		 Gv_e[24][36] = 1;
		 Gv_e[25][36] =1; Gv_e[25][37] = 1;
		 Gv_e[26][34] =1;Gv_e[26][37] = 1;
		 Gv_e[27][36] = 1;Gv_e[27][38] = 1;
		 Gv_e[28][37] = 1; Gv_e[28][39] = 1;
		 Gv_e[29][35] = 1; Gv_e[29][37] = 1;
		 
		 Gv_e[30][0] = 1;  Gv_e[30][1] = 1;  Gv_e[30][31] =1;Gv_e[30][11] =1;
		 
		 Gv_e[31][30] = 1; Gv_e[31][1] = 1; Gv_e[31][2] = 1;Gv_e[31][32] = 1;Gv_e[31][9] = 1; Gv_e[31][10] = 1;Gv_e[31][12] = 1;
		 
		 Gv_e[32][0] = 1;  Gv_e[32][3] = 1; Gv_e[32][4] = 1; Gv_e[32][31] = 1;Gv_e[32][33] = 1;Gv_e[32][9] = 1;Gv_e[32][11] = 1;
		 Gv_e[32][12] = 1; Gv_e[32][17] =1;
		 
		 Gv_e[33][2] = 1;  Gv_e[33][4] = 1; Gv_e[33][5] = 1; Gv_e[33][32] = 1; Gv_e[33][34] = 1;Gv_e[33][10] = 1;Gv_e[33][12] = 1;
		 Gv_e[33][13] = 1; Gv_e[33][14] =1;Gv_e[33][18] = 1;
		 
		 Gv_e[34][6] = 1;  Gv_e[34][33] = 1;Gv_e[34][35] = 1;Gv_e[34][14] = 1;Gv_e[34][16] =1;Gv_e[34][17]=1;Gv_e[34][20] =1;
		 Gv_e[34][21] = 1;Gv_e[34][26] = 1;
		 
		 Gv_e[35][3] = 1;  Gv_e[35][5] = 1; Gv_e[35][6] = 1; Gv_e[14][7] = 1; Gv_e[35][34] = 1;Gv_e[35][36] = 1;Gv_e[35][18] =1;
		 Gv_e[35][20] = 1;Gv_e[35][22] = 1;Gv_e[35][23] = 1;Gv_e[35][29] = 1;
		 
		 Gv_e[36][8] = 1;  Gv_e[36][35] = 1;Gv_e[36][37] = 1; Gv_e[36][21] = 1;Gv_e[36][23] =1;Gv_e[36][24] = 1;Gv_e[36][25] =1;
		 Gv_e[36][27] = 1;
		 
		 Gv_e[37][7] = 1;  Gv_e[37][8] = 1; Gv_e[37][36] = 1;Gv_e[37][38] = 1;Gv_e[37][19] =1;Gv_e[37][25] = 1;Gv_e[37][26] = 1;
		 Gv_e[37][28] =1;Gv_e[37][29] = 1;
		 
		 Gv_e[38][37] = 1;Gv_e[38][39] = 1;Gv_e[38][15] =1;Gv_e[38][19] =1;Gv_e[38][27] = 1;
		 
		 Gv_e[39][38] = 1;Gv_e[39][15] = 1;Gv_e[39][28] = 1;
		for(int i = 0 ; i < Gv_e.length ; i++){
			for(int j = 0 ; j < Gv_e.length ; j++){
				if(Gv_e[i][j]!=max&&Gv_e[i][j]!=0){
					Gv_e[i][j] = Math.sqrt(Math.pow(Math.abs(GasPoint.point[i].getX()-GasPoint.point[j].getX()), 2)+Math.pow(Math.abs(GasPoint.point[i].getY()-GasPoint.point[j].getY()),2));

				}
			}
		}
		
		Floyd(Gv_e);
		int k = 30;
		
		mylist.clear();
		mylist.add(fpath[k][k]);
		curpath.add(fpath[k][k]);
		temppath.add(fpath[k][k]);
		while(k!=Gv_e.length-1){
			mylist.add(fpath[k][Gv_e.length-1]);
			k = fpath[k][Gv_e.length-1];
		}
		for(int i = 0 ; i < generate.length ; i++){
			Gasproperty.add(GasPoint.point[generate[i]]);
		}
		for(int i = 0 ; i < generate.length ; i+=5){
			Gasproperty.get(generate[i]).setProperty("中国石油");
			Gasproperty.get(generate[i]).setPrice("6.5-7.0元/升");
		}
		for(int i = 1 ; i < generate.length ; i+=5){
			Gasproperty.get(generate[i]).setProperty("中国石化");
			Gasproperty.get(generate[i]).setPrice("6.5-7.0元/升");
		}
		for(int i = 2 ; i < generate.length ; i+=5){
			Gasproperty.get(generate[i]).setProperty("中国石化");
			Gasproperty.get(generate[i]).setPrice("6.0-6.5元/升");
		}
		for(int i = 3 ; i < generate.length ; i+=5){
			Gasproperty.get(generate[i]).setProperty("中国石油");
			Gasproperty.get(generate[i]).setPrice("6.0元/升以下");
		}
		for(int i = 4 ; i < generate.length ; i+=5){
			Gasproperty.get(generate[i]).setProperty("中国石化");
			Gasproperty.get(generate[i]).setPrice("7.0元/升以上");
		}
		long end = System.currentTimeMillis();
		totaltime +=(end-start);
		System.out.println("initial "+totaltime);
	}
	
	public void Floyd(double[][] Gv_e){
		//弗洛伊德算法求两点间最短路径
		
		for(int i = 0 ; i < Gv_e.length; i ++)
		{
			for(int j = 0 ; j < Gv_e.length ; j ++){
				fdist[i][j] = Gv_e[i][j];
				fpath[i][j] = j;
				
			}
		}
		
		for(int k = 0 ; k < Gv_e.length ; k ++)
			for(int i = 0 ; i < Gv_e.length ; i ++)
				for(int j = 0 ; j < Gv_e.length ; j ++){
					double ij = fdist[i][j];
					double ik = fdist[i][k];
					double kj = fdist[k][j];
					double add = (ik==max||kj==max)?max:ik+kj;
					if(ij>add)
					{
						fdist[i][j] = add;
						fpath[i][j] = fpath[i][k];
					}
				}
	
	}

	//根据属性筛选
	public void choiceProperty(int prop){
		int[] ptemp = new int[generate.length];

		int k = 0;
		System.out.println("prop "+prop);
			
				if(prop!=0){
					count++;
					if(prop==3)
					{
						for(int i = 0 ; i < generate.length ; i++)
						{
							if(Gasproperty.get(generate[i]).getProrperty()=="中国石油")
							{
								ptemp[k++] = generate[i];
							}
						}
					}
					else if(prop==4)
					{
						for(int i = 0 ; i < generate.length ; i++)
						{
							if(Gasproperty.get(generate[i]).getProrperty()=="中国石化")
							{
								//ptemp[k++] = GasPoint.point[generate[i]];
								ptemp[k++] = generate[i];
							}
						}
					}
					else if(prop==5)
					{
						for(int i = 0 ; i < generate.length ; i++)
						{
							if(Gasproperty.get(generate[i]).getProrperty()=="其他")
							{
								ptemp[k++] = generate[i];
							}
						}
					}
					else if(prop==6)
					{
						for(int i = 0 ; i < generate.length ; i++)
						{
							if(Gasproperty.get(generate[i]).getPrice()=="6.0元/升以下")
							{
								ptemp[k++] = generate[i];
							}
						}
					}
					else if(prop==7)
					{
						for(int i = 0 ; i < generate.length ; i++)
						{
							if(Gasproperty.get(generate[i]).getPrice()=="6.0-6.5元/升")
							{
								ptemp[k++] = generate[i];
							}
						}
					}
					else if(prop==8)
					{
						for(int i = 0 ; i < generate.length ; i++)
						{
							if(Gasproperty.get(generate[i]).getPrice()=="6.5-7.0元/升")
							{
								//System.out.println("generate "+generate[i]);
							//	System.out.println(GasPoint.point[generate[i]]+" "+ GasPoint.point[generate[i]].getX()+","+GasPoint.point[generate[i]].getY());
								ptemp[k++] = generate[i];
								
							}
						}
					
					}
					else if(prop==9)
					{
						for(int i = 0 ; i < generate.length ; i++)
						{
							if(Gasproperty.get(generate[i]).getPrice()=="7.0元/升以上")
							{
								ptemp[k++] = generate[i];
							}
						}
					}
					for( int j = 0 ; j < k ; j++){
						generate[j] = ptemp[j];
						
					}
					
					
					for( int j = k ; j < generate.length ; j++){
						generate[j] = -1;
					}
					for(int i = 0 ; i < generate.length ; i++){
						System.out.println("    " +generate[i]);
					}
				}
//				if(prop!=0){
//					
//					
//					for(int i = 0 ; i < generate.length ; i++)
//						System.out.println("generate "+generate[i]);
//				}
		
//	if(prop!=0){
//		for(int j = k ; j < generate.length ; j++){
//			generate[j] = -1;
//		}
//	}
//		for(int i = 0 ; i < GasPoint.point.length ; i++){
//			System.out.println("          "+GasPoint.point[i].getX()+","+GasPoint.point[i].getY());
//		}
	}
			//获得当前汽车行驶的位置 
	public void getPosition(int speed){

		
		
		long start = System.currentTimeMillis();
		if(count<1){
			choiceProperty(ch_val);
		}
					//int k = 0;
					double dists = 0 ;	//整段距离
					mypath.clear();
					distance = distance+speed*1;	//行驶距离
					for(int i = 0 ; i < mylist.size()-1;i++){
						//System.out.print(mylist.get(i)+"->");		
							dists = fdist[(int) mylist.get(i)][(int) mylist.get(i+1)]+dists ;	
						if(dists>distance)
						{
							
							//车在此路段
							mypath.add(mylist.get(i));
							mypath.add(mylist.get(i+1));
							System.out.println("the car in path:"+mylist.get(i)+","+mylist.get(i+1));
							
							nextdist = dists-distance;
							predist =fdist[(int) mylist.get(i)][(int) mylist.get(i+1)]-nextdist;
							//System.out.println("predist:"+predist);
							break;
							//后端要传给前端的是mylist.get(i),mylist.get(j),predist,
						}
					}
					if(mypath.get(0)!=temppath.get(temppath.size()-1)&&(int)temppath.get(temppath.size()-1)!= Gv_e.length-1){
						temppath.add(mypath.get(0));
					}
					if(mypath.get(0)!=curpath.get(curpath.size()-1)&&((int)curpath.get(curpath.size()-1)!=Gv_e.length-1))
					{
						curpath.add(mypath.get(0));
						
					}
					double k = 1;
					if(GasPoint.point[(int) mypath.get(0)].getX()-GasPoint.point[(int)mypath.get(1)].getX()!=0)
					{
						k = (GasPoint.point[(int) mypath.get(0)].getY()-GasPoint.point[(int)mypath.get(1)].getY())/(GasPoint.point[(int) mypath.get(0)].getX()-GasPoint.point[(int)mypath.get(1)].getX());
					}
					double distance = Math.sqrt((Math.pow((GasPoint.point[(int) mypath.get(0)].getY()-GasPoint.point[(int)mypath.get(1)].getY()),2)+Math.pow((GasPoint.point[(int) mypath.get(0)].getX()-GasPoint.point[(int)mypath.get(1)].getX()),2)));
					
						if(k >= 0){
							cur.setX((float)( GasPoint.point[(int) mypath.get(0)].getX()+ predist/distance*Math.abs(GasPoint.point[(int) mypath.get(0)].getX()-GasPoint.point[(int)mypath.get(1)].getX()))); 
							cur.setY((float)( GasPoint.point[(int) mypath.get(0)].getY()+ predist/distance*Math.abs(GasPoint.point[(int) mypath.get(0)].getY()-GasPoint.point[(int)mypath.get(1)].getY())));  
						}
						else{
							//cur.setX((float)( GasPoint.point[(int) mypath.get(0)].getX()-predist/distance*Math.abs(GasPoint.point[(int) mypath.get(0)].getX()-GasPoint.point[(int)mypath.get(1)].getX())));
							//cur.setY((float)( GasPoint.point[(int) mypath.get(0)].getY()+ predist/distance*Math.abs(GasPoint.point[(int) mypath.get(0)].getY()-GasPoint.point[(int)mypath.get(1)].getY())));
							cur.setX((float) (GasPoint.point[(int) mypath.get(1)].getX()-nextdist/distance*Math.abs(GasPoint.point[(int)mypath.get(0)].getX()-GasPoint.point[(int)mypath.get(1)].getX())));
							cur.setY((float) (GasPoint.point[(int) mypath.get(1)].getY()+nextdist/distance*Math.abs(GasPoint.point[(int)mypath.get(0)].getY()-GasPoint.point[(int)mypath.get(1)].getY())));
						}
					cur.setFront((int) mypath.get(0));
					cur.setBeyond((int)mypath.get(1));
				sendMap();
				long end = System.currentTimeMillis();
				totaltime +=(end-start);
				System.out.println("getposition  "+totaltime);
				}
	public void sendMap(){
		jsonarray.clear();
		jsonarray.add(cur.getX());
		jsonarray.add(cur.getY());
	}
	public void getDistance(Point q){
		long start = System.currentTimeMillis();
		double tmdist1 = Math.sqrt(Math.pow(GasPoint.point[q.getFront()].getX()-q.getX(),2)+Math.pow(GasPoint.point[q.getFront()].getY()-q.getY(), 2));
	    double tmdist2 = Math.sqrt(Math.pow(GasPoint.point[q.getBeyond()].getX()-q.getX(),2)+Math.pow(GasPoint.point[q.getBeyond()].getY()-q.getY(), 2));
		
			for(int i = 0 ; i < generate.length ; i++)
			{
				if(generate[i]!=-1){
					double front = tmdist1+fdist[q.getFront()][generate[i]]+fdist[generate[i]][GasPoint.point.length-1];
					double behind = tmdist2+fdist[q.getBeyond()][generate[i]]+fdist[generate[i]][GasPoint.point.length-1];
					
					if(front>behind){
						 shortest_path[i] = behind;
						 shortest_choice[i] = q.getBeyond();
					}
					else {
						shortest_path[i] = front;
						shortest_choice[i] = q.getFront();
					}
					
				}
				}	
			
		
		//冒泡排序，从小到大
		for(int i = 0 ; i < shortest_path.length ; i ++)
		{
			for(int j = 0 ; j < shortest_path.length-1-i ; j++){
				if(shortest_path[j]>shortest_path[j+1]){
					double temp1 = shortest_path[j];
					shortest_path[j] = shortest_path[j+1];
					shortest_path[j+1] = temp1;
					int temp2 = generate[j];
					generate[j] = generate[j+1];
					generate[j+1] = temp2;
					int temp3 = shortest_choice[j];
					shortest_choice[j] = shortest_choice[j+1];
					shortest_choice[j+1] = temp3;
				}
			}
		}
		long end = System.currentTimeMillis();
		totaltime +=(end-start);
		System.out.println("getDistance "+totaltime);
	}
	public void getGas(){
		long start = System.currentTimeMillis();
		int k = 0;
		Gas.clear();
		for(int i = 0 ; i < shortest_path.length ; i ++){
			if(generate[i]!=-1){
				Gas.add(generate[i]);
			}
		}
		for(int i = 0 ; i < Gas.size() ; i++){
			System.out.println("the nearest gas is "+Gas.get(i)+",the distance is :"+shortest_path[i]);
		}
		long end = System.currentTimeMillis();
		totaltime +=(end-start);
		System.out.println("getGas "+totaltime);
	}
	
	
	public void updatePath(){
		long start = System.currentTimeMillis();
		lists.clear();
		//System.out.println("ss"+ss);
		for(int i = 0 ; i < generate.length ; i++){
			if(generate[i]!=-1){
				int tmp = shortest_choice[i];
				list.add(shortest_choice[i]);
				while(tmp!=generate[i]){
					list.add(fpath[tmp][generate[i]]);
					tmp = fpath[tmp][generate[i]];
				}
				tmp = generate[i];
		
				while(tmp!=GasPoint.point.length-1){
					list.add(fpath[tmp][GasPoint.point.length-1]);
					tmp = fpath[tmp][GasPoint.point.length-1];
				}
				lists.addAll(list);
				lists.add("\n");
				list.clear();
			}
		}
		long end = System.currentTimeMillis();
		totaltime +=(end-start);
		System.out.println("updatePath "+totaltime);
	}
	public void choice_path(int path ){
		long start = System.currentTimeMillis();
		curpath.clear();
		mylist.clear();
		curpath.addAll(temppath);
		int j = 0 ,enter = 0,i = 0 ;
		for(i = 0 ; i < generate.length ; i ++){
			if(path == generate[i]){
			break;
			}
		}
		for(j = 0 ; j <lists.size() ; j ++)
		{
			
			if(lists.get(j).equals("\n")){
				enter++;
			}
	
			if(enter == i&&lists.get(j)!="\n"&&lists.get(j)!=curpath.get(curpath.size()-1) ){	 
				
				curpath.add( lists.get(j));		
				
				}
			if(enter>generate.length)
				enter = 0;
		}
		
		mylist.addAll(curpath);
		long end = System.currentTimeMillis();
		totaltime +=(end-start);
		System.out.println("choice_path "+totaltime);
		}	
}
