package com.action;

public class GasPoint{
	static double[][]GasPoint = new double[40][40];
	public GasPoint(double[][]gaspoint){
		System.arraycopy(gaspoint, 0, GasPoint, 0, GasPoint.length);
	}
	static Point[] point = new Point[40];
	public GasPoint(Point[] Point){
		System.arraycopy(Point, 0, point, 0, point.length);
	}
}
