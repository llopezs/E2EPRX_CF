package com.btp.e2e.servlets.pushNotifications;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
public class PushNotData {
	@SerializedName("\"json\":")
	private String mJson;
	
	public String getJson(){
		return mJson;
	}
	
	public void setJson(String json){
		this.mJson = json;
	}
	
}
