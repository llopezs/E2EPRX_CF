package com.btp.e2e.servlets.Structures4Jsons;

import com.btp.e2e.servlets.Metadata;

public class OdataStructure {
	private Metadata __metadata = new Metadata();
	private String ClassPrx = "ZCL_E2E_SOL_COT";
	private String Content = "";
	private String File = "";
	private String Method = "";
	private String Userid = "";
	private String Item = "";

	public String getClassPrx() {
		return ClassPrx;
	}

	public void setClassPrx(String classPrx) {
		ClassPrx = classPrx;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getFile() {
		return File;
	}

	public void setFile(String file) {
		File = file;
	}

	public String getMethod() {
		return Method;
	}

	public void setMethod(String method) {
		Method = method;
	}

	public String getUserid() {
		return Userid;
	}

	public void setUserid(String userid) {
		Userid = userid;
	}

	public String getItem() {
		return Item;
	}

	public void setItem(String item) {
		Item = item;
	}

	public Metadata get__metadata() {
		return __metadata;
	}

	public void set__metadata(Metadata __metadata) {
		this.__metadata = __metadata;
	}

}
