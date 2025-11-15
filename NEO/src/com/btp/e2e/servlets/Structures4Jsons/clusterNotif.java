package com.btp.e2e.servlets.Structures4Jsons;



import java.util.List;

import com.btp.e2e.implementations.PojoPrimary;

public class clusterNotif extends PojoPrimary {
	private String type = "";
	private List<String> cluster; 
	private List<String> device; 
	private String minute = "";
	private String message = "";
	private String model = "";
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getCluster() {
		return cluster;
	}
	public void setCluster(List<String> cluster) {
		this.cluster = cluster;
	}
	public List<String> getDevice() {
		return device;
	}
	public void setDevice(List<String> device) {
		this.device = device;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	

}
