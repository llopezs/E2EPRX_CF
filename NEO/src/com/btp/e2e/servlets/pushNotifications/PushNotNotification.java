package com.btp.e2e.servlets.pushNotifications;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
public class PushNotNotification {

	@SerializedName("title")
	private String mTitle;
	@SerializedName("body")
	private String mBody;
	@SerializedName("sound")
	private String mSound = "default";
	@SerializedName("icon")
	private String mIcon = "ic_icon_yellow";
	@SerializedName("click_action")
	private String mclick_action = ".Pedido.PONLINE.ListPedidoOnline";
	@SerializedName("channel")
	private String mChannel;

	public String getChannel() {
		return mChannel;
	}

	public void setChannel(String mChannel) {
		this.mChannel = mChannel;
	}
	public String getBody(){
		return mBody;
	}
	
	public void setBody(String body){
		this.mBody = body;
	}
	
	public String getTitle(){
		return mTitle;
	}
	
	public void setTitle(String title){
		this.mTitle = title;
	}
	
}
