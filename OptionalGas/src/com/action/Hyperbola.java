package com.action;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class Hyperbola {
	private int max = Integer.MAX_VALUE;
	int[] generate = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
	ArrayList gas_station = new ArrayList();
	double[][]Gv_e = new double[GasPoint.point.length][GasPoint.point.length];

	//w到终点经过各个最优加油站的距离
	double[][]w_end = new double[GasPoint.point.length][generate[generate.length-1]+1];
	//边界点与相邻端点距离
	double[][]bound = new double[GasPoint.point.length][GasPoint.point.length];
	//边界点坐标
	ArrayList<Point> bd = new ArrayList<Point>();
	//floyd
	double[][] dist = new double[GasPoint.point.length][GasPoint.point.length];
	int[][] fpath = new int[GasPoint.point.length][GasPoint.point.length];
	//候选集 结果集
	
	ArrayList<Point> ResultSet = new ArrayList<Point>();
	Set<Point> CandidateSet = new HashSet<Point>();
	ArrayList Gas = new ArrayList();
	//存储相邻生成点之间的距离
	double[][] neardist = new double[generate.length][generate.length];
	ArrayList<Point> Gasproperty = new ArrayList<Point>();//加油站的公司以及价格
	ArrayList mypath = new ArrayList();	//前后两点
	ArrayList mylist = new ArrayList<Comparable>();	//存路径
	double distance = 0;	//车已经走过的路程
	double predist = 0,nextdist = 0 ; 	//相邻两点距离（前后）
	ArrayList curpath = new ArrayList();//存当前已走过的路径和选择加油站之后要走的路径
	ArrayList temppath = new ArrayList();
	ArrayList list = new ArrayList();
	ArrayList lists = new ArrayList();
	int[] shortest_choice = new int[generate.length];//选择前一个节点还是后一个节点
	Point cur = new Point();
	ArrayList jsonarray = new ArrayList();
	public long totaltime = 0;
	static int ch_val;
	static int count = 0;
	static int cc = 0;
	public void Initial(){
		long start = System.currentTimeMillis();
		for(int j = 0 ; j < generate.length;j++){
			gas_station.add(generate[j]);
		}
		for(int i = 0 ; i < Gv_e.length ; i++){
			for(int j = 0 ; j < Gv_e.length ; j ++){
				Gv_e[i][j] = max;	
				if(i==j)Gv_e[i][j]=0;
				bound[i][j] = max;
				
			}
		}
		for(int i = 0 ; i < GasPoint.point.length ; i++){
			for(int j = 0 ; j < generate.length ; j++){
				w_end[i][j] = max;
				
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
		int k = 30 ;
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
		totaltime+=(end-start);
		System.out.println("initial "+totaltime);
	}
	
public void Floyd(double[][] Gv_e){
		//弗洛伊德算法求两点间最短路径
		
		for(int i = 0 ; i < Gv_e.length; i ++)
		{
			for(int j = 0 ; j < Gv_e.length ; j ++){
				dist[i][j] = Gv_e[i][j];
				fpath[i][j] = j;
				
			}
		}
		
		for(int k = 0 ; k < Gv_e.length ; k ++)
			for(int i = 0 ; i < Gv_e.length ; i ++)
				for(int j = 0 ; j < Gv_e.length ; j ++){
					double ij = dist[i][j];
					double ik = dist[i][k];
					double kj = dist[k][j];
					double add = (ik==max||kj==max)?max:ik+kj;
					if(ij>add)
					{
						dist[i][j] = add;
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
						gas_station.clear();
						count++;
						for(int i = 0 ; i < GasPoint.point.length ; i++){
							for(int j = 0 ; j < gas_station.size() ; j++){
								w_end[i][j] = max;
								
							}
						}
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
							gas_station.add(ptemp[j]);
							
						}
					}
		}
	//获得当前汽车行驶的位置
	public void getPosition(int speed){
		if(count<1)
			choiceProperty(ch_val);
		long start = System.currentTimeMillis();
			//int k = 0;
			double dists = 0 ;	//整段距离
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
					cur.setX((float) (GasPoint.point[(int) mypath.get(1)].getX()-nextdist/distance*Math.abs(GasPoint.point[(int)mypath.get(0)].getX()-GasPoint.point[(int)mypath.get(1)].getX())));
					cur.setY((float) (GasPoint.point[(int) mypath.get(1)].getY()+nextdist/distance*Math.abs(GasPoint.point[(int)mypath.get(0)].getY()-GasPoint.point[(int)mypath.get(1)].getY())));
			
				}
				cur.setFront((int) mypath.get(0));
				cur.setBeyond((int)mypath.get(1));
		sendMap();
		long end = System.currentTimeMillis();
		totaltime+=end-start;
		System.out.println("getposition "+totaltime);
		}
	public void sendMap(){
		jsonarray.clear();
		jsonarray.add(cur.getX());
		jsonarray.add(cur.getY());
	}
	public void createHyper(){
		if(count<1)
			choiceProperty(ch_val);
		long start = System.currentTimeMillis();
		double[]temp = new double[gas_station.size()];
		for(int i = 0 ; i < GasPoint.point.length ; i++){
			double min = max;
			int flag = max;
			for(int j = 0 ; j < gas_station.size() ; j++){
				temp[j] = dist[i][(int) gas_station.get(j)]+dist[(int) gas_station.get(j)][GasPoint.point.length-1];
				if(temp[j]<min){
					min = temp[j];
					flag = (int)gas_station.get(j);
				}
			}
			w_end[i][flag] = min;
			//对于每一个点经过最优加油站到达终点的路段距离
			System.out.println("点：("+GasPoint.point[i].getX()+","+GasPoint.point[i].getY()+")的最优加油站是p"+flag+"总距离为："+w_end[i][flag]);
		}
		
		for(int i = 0 ; i < Gv_e.length ; i++)
		{
			double disti = 0 ,distj = 0;
			for(int j = 0 ; j < Gv_e.length ; j++)
			{
				if(Gv_e[i][j]!=max){
					int k ;
					for(k = 0 ; k < gas_station.size() ; k++)
					{
						if(w_end[j][(int) gas_station.get(k)]!=max&&w_end[i][(int) gas_station.get(k)]!=max)
							break;
					}
					if(k==gas_station.size())
					{
						for(int m = 0 ; m < gas_station.size() ; m++)
						{
							if(w_end[j][(int) gas_station.get(m)]!=max)
								distj = w_end[j][(int) gas_station.get(m)];
							if(w_end[i][(int) gas_station.get(m)]!=max)
								disti = w_end[i][(int) gas_station.get(m)];
						}
						bound[i][j] = Math.abs(Gv_e[i][j]+distj-disti)/2;
					}
				}
			}
		}
		for(int i = 0 ; i < Gv_e.length ; i++){
			int count = 0;
			for(int j = 0; j < gas_station.size(); j++){
				if(w_end[i][(int) gas_station.get(j)] != 0 &&w_end[i][(int) gas_station.get(j)] != max){
					count++;
				}
			}
			if(count > 1)
			{
			
				bound[i][i] = 0;
			}
		}

		
		for(int i = 0 ; i < bound.length; i ++){
			for(int j = 0 ; j < bound.length ; j++){
				float x = max,y = max;
				int front = i,behind = j;
				double k = 1;
				if(GasPoint.point[i].getX()-GasPoint.point[j].getX()!=0)
				{
					k = (GasPoint.point[i].getY()-GasPoint.point[j].getY())/(GasPoint.point[i].getX()-GasPoint.point[j].getX());
				}
				double distance = Math.sqrt((Math.pow((GasPoint.point[i].getY()-GasPoint.point[j].getY()),2)+Math.pow((GasPoint.point[i].getX()-GasPoint.point[j].getX()),2)));
				if(bound[i][j]!=max&&bound[i][j]!=0){
					if(k >= 0){
						x = (float)( GasPoint.point[i].getX()+ bound[i][j]/distance*Math.abs(GasPoint.point[i].getX()-GasPoint.point[j].getX()));
						y = (float)( GasPoint.point[i].getY()+ bound[i][j]/distance*Math.abs(GasPoint.point[i].getY()-GasPoint.point[j].getY()));
					}
					else{
						x = (float)( GasPoint.point[i].getX()- bound[i][j]/distance*Math.abs(GasPoint.point[i].getX()-GasPoint.point[j].getX()));
						y = (float)( GasPoint.point[i].getY()+ bound[i][j]/distance*Math.abs(GasPoint.point[i].getY()-GasPoint.point[j].getY()));
					}
				}
				
				Point tmp = new Point(x,y,front,behind);
				if((x>=GasPoint.point[i].getX()&&x<=GasPoint.point[j].getX()||x>=GasPoint.point[j].getX()&&x<=GasPoint.point[i].getX())&&(y>=GasPoint.point[i].getY()&&y<=GasPoint.point[j].getY()||y>=GasPoint.point[j].getY()&&y<=GasPoint.point[i].getY()))
				{	
					bd.add(tmp);
				}
			}
			
			
		}
		for(int i = 0 ; i < bd.size() ; i++)
			System.out.println(bd.get(i).getX()+"  "+bd.get(i).getY()+"边界点所在位置："+bd.get(i).getFront()+" "+bd.get(i).getBeyond());
	long end = System.currentTimeMillis();
	totaltime+=end-start;
	System.out.println("create "+totaltime);
	}
	public void Filter(Point q,int rknn){
		long start = System.currentTimeMillis();
		ResultSet.clear();
		for(int k = 0 ; k < gas_station.size(); k++){
			if(w_end[q.getFront()][(int) gas_station.get(k)]!=max&&w_end[q.getBeyond()][(int) gas_station.get(k)]!=max){
				System.out.println(gas_station.get(k));
				ResultSet.add(GasPoint.point[(int) gas_station.get(k)]);
				break;
			}
			else if(w_end[q.getFront()][(int) gas_station.get(k)]==max&&w_end[q.getBeyond()][(int) gas_station.get(k)]!=max||w_end[q.getFront()][(int) gas_station.get(k)]!=max&&w_end[q.getBeyond()][(int) gas_station.get(k)]==max){
				//有边界点，找到此边界点，然后找此点在边界点的哪里，判断他的生成点和前者一样还是后者一样
				
				for(int i = 0 ; i < bd.size() ; i++)
					
				{
					if(q.getFront()==bd.get(i).getFront()&&q.getBeyond()==bd.get(i).getBeyond())
					{
					
						if((q.getX()>=GasPoint.point[q.getFront()].getX()&&q.getX()<=bd.get(i).getX()||q.getX()<=GasPoint.point[q.getFront()].getX()&&q.getX()>=bd.get(i).getX())&&
						   (q.getY()>=GasPoint.point[q.getFront()].getY()&&q.getY()<=bd.get(i).getY()||q.getY()<=GasPoint.point[q.getFront()].getY()&&q.getY()>=bd.get(i).getY())&&w_end[q.getFront()][(int) gas_station.get(k)]!=max){
							
							ResultSet.add(GasPoint.point[(int) gas_station.get(k)]);
						}
						else if( (q.getX()>=GasPoint.point[q.getBeyond()].getX()&&q.getX()<=bd.get(i).getX()||q.getX()<=GasPoint.point[q.getBeyond()].getX()&&q.getX()>=bd.get(i).getX())&&
								(q.getY()>=GasPoint.point[q.getBeyond()].getY()&&q.getY()<=bd.get(i).getY()||q.getY()<=GasPoint.point[q.getBeyond()].getY()&&q.getY()>=bd.get(i).getY())&&w_end[q.getBeyond()][(int) gas_station.get(k)]!=max){
									
									ResultSet.add(GasPoint.point[(int) gas_station.get(k)]);
						}
					}
				}
			}
		}
	
		for(Point p:ResultSet){
			System.out.println("ResultSet:"+p.getX()+","+p.getY());
		}
		rknn--;
		while(rknn!=0){
			//确定与pi相邻NVP生成点pj
			//遍历边界点，找边界点最优加油站，若即包含pi又包含pj则pj加入候选集
			rknn--;
			//CandidateSet.clear();
			for(int k = 0 ; k < bd.size() ; k++)
			{int count = 0;
				
								ArrayList temp = new ArrayList();
								for(int n = 0 ; n < gas_station.size() ; n++){
									
									if(w_end[bd.get(k).getFront()][(int) gas_station.get(n)]!=max)
									{
										count++;
									
										temp.add((int) gas_station.get(n));
									}
									else if(w_end[bd.get(k).getBeyond()][(int) gas_station.get(n)]!=max)
									{
										count++;
									
										temp.add((int) gas_station.get(n));
									}
								}
								if(count>=2){
									bd.get(k).setGen(temp);
								}
							
			}
			for(int k = 0 ; k < bd.size() ; k++){
//				{
//					System.out.println(bd.get(k).getX()+" "+bd.get(k).getY()+" "+bd.get(k).getGen());
//					System.out.println(bd.get(k).getGen().size());
//				}
			for(int n = 0 ; n < bd.get(k).getGen().size(); n++){
			
				Iterator<Point> it = ResultSet.iterator();
				while(it.hasNext()){			
				    if(it.next()==GasPoint.point[(int) bd.get(k).getGen().get(n)]){ 					    	
				    	for(int i = 0 ; i < bd.get(k).getGen().size(); i++){
							CandidateSet.add(GasPoint.point[(int)bd.get(k).getGen().get(i)]);
						}
						CandidateSet.remove(GasPoint.point[(int)bd.get(k).getGen().get(n)]);
				    }					
				}
			}
		}
			Iterator<Point> result = ResultSet.iterator();
		//	Iterator<Point> candidate = CandidateSet.iterator();
			while(result.hasNext()){
				Point pp = result.next();
				for(Iterator<Point> candidate = CandidateSet.iterator();candidate.hasNext();){
					Point qq = candidate.next();
					if(pp.equals(qq)){
						
						candidate.remove();
						
					}
				}
			}
			
			for(Point p:CandidateSet){
				System.out.println("Candidate:"+p.getX()+","+p.getY());
			}
			Refine(q);
		}
		long end = System.currentTimeMillis();
		totaltime+=end-start;
		System.out.println("filter "+totaltime);
	}
	
	public void Refine(Point q){
		//计算查询点Q到新加入候选集px的距离，找到最短距离，d(px,q)然后加入结果集中
		
		//while(rknn!=0){
			double[][] q_end = new double[1][(int)gas_station.get(gas_station.size()-1)+1];
			
			for(int i = 0 ; i < gas_station.size() ; i++)
			{	
				q_end[0][(int) gas_station.get(i)] = max;
			}
		
			double tmdist1 = Math.sqrt(Math.pow(GasPoint.point[q.getFront()].getX()-q.getX(),2)+Math.pow(GasPoint.point[q.getFront()].getY()-q.getY(), 2));
		    double tmdist2 = Math.sqrt(Math.pow(GasPoint.point[q.getBeyond()].getX()-q.getX(),2)+Math.pow(GasPoint.point[q.getBeyond()].getY()-q.getY(), 2));
			for(Point candidate:CandidateSet){
				for(int i = 0 ; i < gas_station.size() ; i++)
				{
					if(candidate.getX()==GasPoint.point[(int) gas_station.get(i)].getX()&&candidate.getY()==GasPoint.point[(int) gas_station.get(i)].getY())
					{
						double front = tmdist1+dist[q.getFront()][(int) gas_station.get(i)]+dist[(int) gas_station.get(i)][GasPoint.point.length-1];
						double behind = tmdist2+dist[q.getBeyond()][(int) gas_station.get(i)]+dist[(int) gas_station.get(i)][GasPoint.point.length-1];
						
						if(front>behind){
							q_end[0][(int) gas_station.get(i)] = behind;
						}
						else {
							q_end[0][(int) gas_station.get(i)] = front;
					
						}
				
						System.out.println("查询点q和终点最短距离是："+q_end[0][(int) gas_station.get(i)]);
					}	
				}		
			}

			int flag_gene = GasPoint.point.length;
			double minq = max;
			for(int i = 0 ; i < gas_station.size() ; i++){
				if(q_end[0][(int) gas_station.get(i)]<minq)
				{
					minq = q_end[0][(int) gas_station.get(i)];
					flag_gene = (int) gas_station.get(i);
				}
			}
			if(flag_gene!=GasPoint.point.length){
				ResultSet.add(GasPoint.point[flag_gene]);
				CandidateSet.remove(GasPoint.point[flag_gene]);
			}
		
			for(Point point:ResultSet){
				System.out.println("ResultSet:"+point.getX()+","+point.getY());
			}
	
	}
	
	public void getDistance(Point q){
		long start = System.currentTimeMillis();
		double tmdist1 = Math.sqrt(Math.pow(GasPoint.point[q.getFront()].getX()-q.getX(),2)+Math.pow(GasPoint.point[q.getFront()].getY()-q.getY(), 2));
	    double tmdist2 = Math.sqrt(Math.pow(GasPoint.point[q.getBeyond()].getX()-q.getX(),2)+Math.pow(GasPoint.point[q.getBeyond()].getY()-q.getY(), 2));
		for(Point point:ResultSet){
			System.out.println("ResultSet:"+point.getX()+","+point.getY());
			for(int i = 0 ; i < gas_station.size() ; i++)
			{
				if(point.getX()==GasPoint.point[(int) gas_station.get(i)].getX()&&point.getY()==GasPoint.point[(int) gas_station.get(i)].getY())
				{
					
					double front = tmdist1+dist[q.getFront()][(int) gas_station.get(i)]+dist[(int) gas_station.get(i)][GasPoint.point.length-1];
					double behind = tmdist2+dist[q.getBeyond()][(int) gas_station.get(i)]+dist[(int) gas_station.get(i)][GasPoint.point.length-1];
					
					if(front>behind){
				
						 shortest_choice[i] = q.getBeyond();
					}
					else {
						shortest_choice[i] = q.getFront();
					}
				}	
			}
		}
		long end = System.currentTimeMillis();
		totaltime+=end-start;
		System.out.println("getdistance "+totaltime);
	}
	public void getGas(){
		long start = System.currentTimeMillis();
		int k = 0;
		Gas.clear();
		for(int i = 0 ; i < ResultSet.size() ; i++){
			for(int j = 0 ; j < GasPoint.point.length ; j++){
				if(ResultSet.get(i).getX()==GasPoint.point[j].getX()&&ResultSet.get(i).getY()==GasPoint.point[j].getY()){
					Gas.add(j);
					//Gas.get(k++).setName(j);
				}
			}
		}
		for(int i = 0 ; i < Gas.size() ; i++){
			System.out.println("the nearest gas is "+Gas.get(i));
		}
		long end = System.currentTimeMillis();
		totaltime+=end-start;
		System.out.println("getgas "+totaltime);
	}
	
	public void updatePath(){
		long start = System.currentTimeMillis();
		lists.clear();
		//System.out.println("ss"+ss);
		for(int i = 0 ; i < gas_station.size(); i++){
			int tmp = shortest_choice[i];
			list.add(shortest_choice[i]);
			while(tmp!=(int) gas_station.get(i)){
				list.add(fpath[tmp][(int) gas_station.get(i)]);
				tmp = fpath[tmp][(int) gas_station.get(i)];
			}
			tmp = (int) gas_station.get(i);
			while(tmp!=GasPoint.point.length-1){
				list.add(fpath[tmp][GasPoint.point.length-1]);
				tmp = fpath[tmp][GasPoint.point.length-1];
			}
			lists.addAll(list);
			lists.add("\n");
			list.clear();		
		}
		long end = System.currentTimeMillis();
		totaltime+=end-start;
		System.out.println("updatepath "+totaltime);
	}
	public void choice_path(int path ){
		long start = System.currentTimeMillis(); 
		curpath.clear();
		mylist.clear();
		curpath.addAll(temppath);
		int j = 0 ,enter = 0,i = 0 ;
		for(i = 0 ; i < gas_station.size() ; i ++){
			if(path == (int) gas_station.get(i)){
				System.out.println((int) gas_station.get(i)+" "+i);
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
		totaltime+=end-start;
		System.out.println("choicepath "+totaltime);
		}

}
