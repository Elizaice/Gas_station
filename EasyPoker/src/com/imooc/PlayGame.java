package com.imooc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

public class PlayGame {
	public List<Poker> pokerToSelect;
	public List<Gamer> gamer;
	
	public PlayGame(){
		this.pokerToSelect = new ArrayList();
		this.gamer = new ArrayList();
	}

	public void createPoker(){
		String[] stringnum={"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        String[] stringcolor={"方块","梅花","红桃","黑桃"};
        System.out.println("-------创建扑克牌--------");
        for(int i = 0 ; i < 4 ; i++)
        	for(int j = 0 ;j < 13;j++ )
        	{
        		
        		Poker poker = new Poker(stringcolor[i],stringnum[j],i*13+j+1);
        		pokerToSelect.add(poker);
        	}
      for(Object po : pokerToSelect)
        {
        	Poker poke = (Poker) po;
        	System.out.print(poke.color+":"+poke.number+" ");
        }
      System.out.println();
      System.out.println("-------创建扑克牌成功-----------");
	}
	/*
	 * 洗牌
	 */
	public void shuffle(){
		Random rand = new Random();
		System.out.println("--------开始洗牌---------");
		for(int i = 0 ; i < pokerToSelect.size(); i++)
		{
			
			int k = rand.nextInt(pokerToSelect.size()-1);
			Poker poke = (Poker)pokerToSelect.get(i);
			pokerToSelect.set(i, pokerToSelect.get(k));
			pokerToSelect.set(k, poke);
		}
		for(Object po : pokerToSelect)
        {
        	Poker poke = (Poker) po;
        	System.out.print(poke.color+":"+poke.number+" ");
        }
		System.out.println();
		System.out.println("--------洗牌完成---------");
		
	}
	
	public void createGamer(){
		Scanner sc= new Scanner(System.in);
		for(int i = 0 ; i < 2; i++)
		{
			
					System.out.println("请输入玩家ID");
					int id = sc.nextInt();
						System.out.println("请输入玩家昵称：");
						String name = sc.next();
						//Gamer newGamer = new Gamer(id,name);
						gamer.add(new Gamer(id,name));
						System.out.println("成功创建玩家："+gamer.get(i).name);
						
					}
		
			
		System.out.println("玩家创建成功：");
		
		for(Object obj:gamer){
			Gamer gm = (Gamer) obj;
			System.out.println("玩家ID："+gm.id+"玩家昵称："+gm.name);
			
		}
	}
	/*
	 * 发牌，将洗牌之后的扑克牌集合，从第一张开始发给两名玩家，按照一人一张的方式，每人发两张
	 */
	public void dealPoker(){
		System.out.println("--------开始发牌---------");
		int size = gamer.size();
				int i =0;
				while(i<2){
					gamer.get(0).poker.add(pokerToSelect.get(2*i));
					gamer.get(1).poker.add(pokerToSelect.get(2*i+1));
					i++;
					}
				
				for(Object obj:gamer){
					Gamer gm = (Gamer) obj;
					System.out.println("玩家ID："+gm.id+"玩家手牌："+gm.poker.get(0).color+":"+gm.poker.get(0).number);
					System.out.println("玩家ID："+gm.id+"玩家手牌："+gm.poker.get(1).color+":"+gm.poker.get(1).number);
					
				}
				
				
		System.out.println("--------发牌结束----------");
							}
	public void comparePoker(){
		System.out.println("--------开始游戏----------");
		//List<Poker> poke = new ArrayList<Poker>();
		Gamer gm1 = gamer.get(0);
		Gamer gm2 = gamer.get(1);
		Collections.sort(gm1.poker);
		System.out.println("玩家1最大手牌："+gm1.poker.get(1).color+":"+gm1.poker.get(1).number);
		Collections.sort(gm2.poker);
		System.out.println("玩家1最大手牌："+gm2.poker.get(1).color+":"+gm2.poker.get(1).number);
		
		/*for(Poker po : poke){
			System.out.println(po.color+";"+po.number);
		}
		*/
		if(gm1.poker.get(1).compare>gm2.poker.get(1).compare){
			System.out.println("恭喜玩家："+gm1.name+"获胜！");
			}
		else if(gm1.poker.get(1).compare<gm2.poker.get(1).compare)
			System.out.println("恭喜玩家："+gm2.name+"获胜！");
		else
			System.out.println("平局");
		}
			

	public static void main(String[] args){
		PlayGame pg = new PlayGame();
		pg.createPoker();
		pg.shuffle();
		pg.createGamer();
		pg.dealPoker();
		pg.comparePoker();
	}
}
