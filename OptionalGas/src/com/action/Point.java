package com.action;


import java.util.ArrayList;

public class Point {
	private float x ; 
	private float y ;
	//´æÉú³Éµã
	private ArrayList gen = new ArrayList();
	private int front;
	private int beyond;
	private int name;
	private String property;
	private String price;
	public Point(){}
	public Point(float x, float y){
		this.x = x;
		this.y = y;
	}
	public Point(float x, float y, int front,int beyond){
		this.x = x;
		this.y = y;
		this.front = front;
		this.beyond = beyond;
	}
	public void setName(int name){
		this.name = name;
	}
	
	public int getName(){
		return name;
	}
	
	public void setFront(int front){
		this.front = front;
	}
	public int getFront(){
		return front;
	}
	
	public void setBeyond(int beyond){
		this.beyond = beyond;
	}
	public int getBeyond(){
		return beyond;
	}
	public void setGen(ArrayList gen){
		this.gen = gen;
	}
	public ArrayList getGen(){
		return gen;
	}
	public void setX(float x){
		this.x = x;
	}
	public float getX(){
		return x;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getY(){
		return y;
	}
	public String getProrperty(){
		return property;
	}
	public void setProperty(String property){
		this.property = property;
	}
	public String getPrice(){
		return price;
	}
	public void setPrice(String price){
		this.price = price;
	}
}


