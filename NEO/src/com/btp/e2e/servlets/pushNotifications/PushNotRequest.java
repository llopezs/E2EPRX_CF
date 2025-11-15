package com.btp.e2e.servlets.pushNotifications;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
public class PushNotRequest {
	@SerializedName("notification")
    private PushNotNotification mNotification;
	@SerializedName("data")
    private PushNotData mData;
    @SerializedName("to")
    private String mTo;
    @SerializedName("android")
    private AndroidStructures android;

    public PushNotData getData() {
        return mData;
    }

    public void setData(PushNotData data) {
        mData = data;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        mTo = to;
    }
    
    public PushNotNotification getNotification() {
        return mNotification;
    }

    public void setNotification(PushNotNotification notification) {
        mNotification = notification;
    }

	public AndroidStructures getAndroid() {
		return android;
	}

	public void setAndroid(AndroidStructures android) {
		this.android = android;
	}
    
    
    
}