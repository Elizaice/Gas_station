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
        String[] stringcolor={"����","÷��","����","����"};
        System.out.println("-------�����˿���--------");
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
      System.out.println("-------�����˿��Ƴɹ�-----------");
	}
	/*
	 * ϴ��
	 */
	public void shuffle(){
		Random rand = new Random();
		System.out.println("--------��ʼϴ��---------");
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
		System.out.println("--------ϴ�����---------");
		
	}
	
	public void createGamer(){
		Scanner sc= new Scanner(System.in);
		for(int i = 0 ; i < 2; i++)
		{
			
					System.out.println("���������ID");
					int id = sc.nextInt();
						System.out.println("����������ǳƣ�");
						String name = sc.next();
						//Gamer newGamer = new Gamer(id,name);
						gamer.add(new Gamer(id,name));
						System.out.println("�ɹ�������ң�"+gamer.get(i).name);
						
					}
		
			
		System.out.println("��Ҵ����ɹ���");
		
		for(Object obj:gamer){
			Gamer gm = (Gamer) obj;
			System.out.println("���ID��"+gm.id+"����ǳƣ�"+gm.name);
			
		}
	}
	/*
	 * ���ƣ���ϴ��֮����˿��Ƽ��ϣ��ӵ�һ�ſ�ʼ����������ң�����һ��һ�ŵķ�ʽ��ÿ�˷�����
	 */
	public void dealPoker(){
		System.out.println("--------��ʼ����---------");
		int size = gamer.size();
				int i =0;
				while(i<2){
					gamer.get(0).poker.add(pokerToSelect.get(2*i));
					gamer.get(1).poker.add(pokerToSelect.get(2*i+1));
					i++;
					}
				
				for(Object obj:gamer){
					Gamer gm = (Gamer) obj;
					System.out.println("���ID��"+gm.id+"������ƣ�"+gm.poker.get(0).color+":"+gm.poker.get(0).number);
					System.out.println("���ID��"+gm.id+"������ƣ�"+gm.poker.get(1).color+":"+gm.poker.get(1).number);
					
				}
				
				
		System.out.println("--------���ƽ���----------");
							}
	public void comparePoker(){
		System.out.println("--------��ʼ��Ϸ----------");
		//List<Poker> poke = new ArrayList<Poker>();
		Gamer gm1 = gamer.get(0);
		Gamer gm2 = gamer.get(1);
		Collections.sort(gm1.poker);
		System.out.println("���1������ƣ�"+gm1.poker.get(1).color+":"+gm1.poker.get(1).number);
		Collections.sort(gm2.poker);
		System.out.println("���1������ƣ�"+gm2.poker.get(1).color+":"+gm2.poker.get(1).number);
		
		/*for(Poker po : poke){
			System.out.println(po.color+";"+po.number);
		}
		*/
		if(gm1.poker.get(1).compare>gm2.poker.get(1).compare){
			System.out.println("��ϲ��ң�"+gm1.name+"��ʤ��");
			}
		else if(gm1.poker.get(1).compare<gm2.poker.get(1).compare)
			System.out.println("��ϲ��ң�"+gm2.name+"��ʤ��");
		else
			System.out.println("ƽ��");
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
