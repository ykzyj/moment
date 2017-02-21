package com.yk.moments.httpUtil;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

public class HttpClientGetOperate {
	
	/**
	* @Title: httpClientGet 
	* @Description: httpclient的get方式取值
	* @param @param url
	* @param @return
	* @param @throws ClientProtocolException
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String httpClientGet(String url) 
			throws ClientProtocolException, IOException{
		String content = null;
		HttpGet httpGet=new HttpGet(url);
		HttpClient httpClient=CustomerHttpClient.getHttpClient();
		HttpResponse response=httpClient.execute(httpGet);
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK)
		{
			content=EntityUtils.toString(response.getEntity());
		}
		return content;
	}
	
}


