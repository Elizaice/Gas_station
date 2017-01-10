package com.imooc;

import java.util.ArrayList;
import java.util.List;


public class Gamer{
	public int id;
	public String name;
	public List<Poker> poker;
	
	public Gamer(int id,String name){
		this.id = id;
		this.name = name;
		this.poker = new ArrayList<Poker>();
	}
}
