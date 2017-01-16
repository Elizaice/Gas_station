package com.imooc;

public class Poker implements Comparable<Poker> {
	public String color;
	public String number;
	int compare;

	public Poker(String color, String number,int compare){
		this.color = color;
		this.number = number;
		this.compare = compare;
	}
	@Override
	
	public int compareTo(Poker o) {
		if (this.compare < o.compare) {
			return -1; } 
		else { return 1; }
		
	}
	
}
