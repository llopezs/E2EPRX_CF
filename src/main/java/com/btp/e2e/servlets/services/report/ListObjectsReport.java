package com.btp.e2e.servlets.services.report;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ListObjectsReport {
	String lineException = "";
	String classFile = "";
	String getMessage = "";
	String getLocalizedMessage = "";
	List<Object> objects = new ArrayList<Object>();
	public ListObjectsReport() {
		super();
	}
	public ListObjectsReport(String lineException, String classFile, String getMessage, String getLocalizedMessage) {
		super();
		this.lineException = lineException;
		this.classFile = classFile;
		this.getMessage = getMessage;
		this.getLocalizedMessage = getLocalizedMessage;
	}
	public String getLineException() {
		return lineException;
	}
	public void setLineException(String lineException) {
		this.lineException = lineException;
	}
	public String getClassFile() {
		return classFile;
	}
	public void setClassFile(String classFile) {
		this.classFile = classFile;
	}
	public String getGetMessage() {
		return getMessage;
	}
	public void setGetMessage(String getMessage) {
		this.getMessage = getMessage;
	}
	public String getGetLocalizedMessage() {
		return getLocalizedMessage;
	}
	public void setGetLocalizedMessage(String getLocalizedMessage) {
		this.getLocalizedMessage = getLocalizedMessage;
	}
	public List<Object> getObjects() {
		return objects;
	}
	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}
	public void addObject(Object object) {
//		this.objects.add(new Gson().toJson(object));
//		this.objects.add(object);
	}
	public void addObjects(Object[] objects) {
//		for (int i = 0; i < objects.length; i++) {
//			this.objects.add(new Gson().toJson(objects[i]));
//			this.objects.add(objects[i]);
//		}
	}
	public String toString() {
		return new Gson().toJson(this);
	}
}
