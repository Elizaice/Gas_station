package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonAction extends ActionSupport{
	double[][]GasPoint = new double[40][40];
	Point[] p = new Point[40];
	
	public UserThread tt = new UserThread();
	private String result;
//	public String testJson(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		String json = request.getParameter("myJSON");
//		JSONArray jsonarray =JSONArray.fromObject(json);
//		
//		for(int i = 0 ; i < jsonarray.size();i++)
//			for(int j = 0 ; j < jsonarray.size() ; j++){
//				GasPoint[i][j] = jsonarray.getJSONArray(i).getDouble(j);
//			}
//		for(int i = 0 ; i < jsonarray.size();i++)
//		{
//			for(int j = 0 ; j < jsonarray.size() ; j++){
//				System.out.print(GasPoint[i][j]+" ");
//			}
//			System.out.println();
//		}
//		new GasPoint(GasPoint);
//		tt.begin();
//		return SUCCESS;
//	}
	public String receivePoint(){
		for(int i = 0 ; i < p.length ; i++){
			p[i] = new Point();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		String point = request.getParameter("pointJson");
		JSONArray json = JSONArray.fromObject(point);
		for(int i = 0 ; i < p.length ; i++){
			p[i].setX((float)json.getJSONArray(i).getDouble(0));
			p[i].setY((float)json.getJSONArray(i).getDouble(1));
		//	System.out.println(json.getJSONArray(i).getDouble(0)+" "+json.getJSONArray(i).getDouble(1));
			
		}
		new GasPoint(p);
		tt.begin();

		return SUCCESS;
	}
	
	static int flag = 0;
	public  String inputFlag_go(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String value = request.getParameter("flag_go");
		flag = Integer.parseInt(value);
	    ShareData.flag_go = flag;
		return SUCCESS;
	}
	
	public String inputIfornot(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String value = request.getParameter("ifornot");
	    ShareData.ifornot = Integer.parseInt(value);    
		return SUCCESS;
	}

	public String receiveGas(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String number = request.getParameter("gas_number");
		ShareData.gas_number = Integer.parseInt(number);
		System.out.println("¼ÓÓÍÕ¾±àºÅÎª£º"+ShareData.gas_number);
		return SUCCESS;
	}
	public String sendJson() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String jsonString = JSONArray.fromObject(UserThread.array).toString();
		System.out.println(jsonString);
		this.result = jsonString;
		return "success";
	}
	
	public String sendGps() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String gpsString = JSONArray.fromObject(UserThread.gps).toString();
		this.result = gpsString;
		return "success";
	}
	
	public String receiveVal(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String number = request.getParameter("value");
		knn.ch_val = Integer.parseInt(number);	
		return SUCCESS;
	}
	
	public String getResult(){
		return result;
		
	}
}
