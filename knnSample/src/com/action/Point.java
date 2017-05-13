package com.action;


import java.util.ArrayList;

public class Point {
	private float x ; 
	private float y ;
	//�����ɵ�
	private ArrayList gen = new ArrayList();
	private int front;
	private int beyond;
	private String name;
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
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
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
	public void setProperty(String property){
		this.property = property;
	}
	public String getProperty(){
		return property;
	}
	public void setPrice(String price){
		this.price = price;
	}
	public String getPrice(){
		return price;
	}
}


