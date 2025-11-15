package com.btp.e2e.servlets.Structures4Jsons;

import java.util.List;

public class ODataPost {

	public class MSG{
		public String MSG;
	}
	
	public class DOCTOS{
		public String DOCTO;
		public String DTYPE;
	}
	
	public class Response{
		public List<ODataPost.MSG> MSGS;
		public List<ODataPost.DOCTOS> DOCTOS;
	}
	
	public static class ResultadoLiquidacion{
		public ODataPost.Response response1;
		public ODataPost.Response response2;
	}
	
	
}
