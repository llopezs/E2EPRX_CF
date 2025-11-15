package com.btp.e2e.entities;

import com.btp.e2e.implementations.PojoPrimary;

public class ImgTransmitionCierre extends PojoPrimary {
	private String id = "";
	private String id_cierre = "";
	private String id_img = "";
	private String parts = "";
	private String part = "";
	private String imgFragment = "";
	private String timestamp = "";

	public String getId() {
		
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_cierre() {
		
		return id_cierre;
	}

	public void setId_cierre(String id_cierre) {
		this.id_cierre = id_cierre;
	}

	public String getId_img() {
		
		return id_img;
	}

	public void setId_img(String id_img) {
		this.id_img = id_img;
	}

	public String getParts() {
		
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public String getPart() {
		
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

	public String getImgFragment() {
		
		return imgFragment;
	}

	public void setImgFragment(String imgFragment) {
		this.imgFragment = imgFragment;
	}

	public String getTimestamp() {
		
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
