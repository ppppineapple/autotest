package com.tops.testHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
* @author 栾宜成 
* @date : 2016年3月9日 下午7:31:37
*/
public class HttpHelper {
    
	//http帮助类
	public static String httpHelper(String httpUrl,JSONObject jsonObject){
		CloseableHttpClient httpclient = HttpClients.createDefault();//按默认配置创建一个Http客户端
	    HttpPost httppost = new HttpPost(httpUrl);//新建一个post请求
		String strResult = "";
		
		try {
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();//键值对数组
			nameValuePairs.add(new BasicNameValuePair("msg", getStringFromJson(jsonObject)));
			httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));
				
				HttpResponse response = httpclient.execute(httppost);
				if (response.getStatusLine().getStatusCode() == 200) {
					/*读返回数据*/
					String conResult = EntityUtils.toString(response.getEntity());
					JSONObject sobj = new JSONObject();
					//sobj = sobj.fromObject(conResult);
					sobj = JSONObject.fromObject(conResult);
					String result = sobj.getString("status");
					//String code = sobj.getString("code");
					if(result.equals("0")){
						strResult += "调用成功";
					}else{
						strResult += "调用失败";
					}
					
				} else {
					String err = response.getStatusLine().getStatusCode()+"";
					strResult += "调用失败:"+err;
				}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return strResult;
	}

	
	private static String getStringFromJson(JSONObject adata) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(Object key:adata.keySet()){
			sb.append("\""+key+"\":\""+adata.get(key)+"\",");
		}
		String rtn = sb.toString().substring(0, sb.toString().length()-1)+"}";
		return rtn;
	}
}
