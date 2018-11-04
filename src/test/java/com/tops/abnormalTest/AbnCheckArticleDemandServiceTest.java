package com.tops.abnormalTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tops.testHelper.CaseHelper;
import com.tops.testHelper.CaseInfo;
import com.tops.testHelper.Constant;
import com.tops.testHelper.TopsUtil;
import com.tops.testHelper.LoginHepler;
import com.tops.testHelper.ReadExcel;

import net.sf.json.JSONObject;

/**
 * 文章查询
 * @author: 苏琴
 * @date  : 2016年4月21日 下午2:05:50
 */
public class AbnCheckArticleDemandServiceTest {
	protected String caseExcelPath =System.getProperty("user.dir")+"/javaabnexcel/AbnCheckArticleDemandServiceTest.xlsx";
	
	@DataProvider(name = "dataInfo")
	protected Object[][] dataInfo1() throws IOException {

		Object[][] myObj = null;
		List<Map<String, String>> list = ReadExcel.readXlsx(caseExcelPath);
		System.out.println(caseExcelPath);
		myObj = CaseHelper.getObjArrByList(list);
		return myObj;
	}
	
	@Test(dataProvider="dataInfo")
	public void checkAllCityServiceTest(CaseInfo c) throws Exception{
	
		System.out.println("-------------文章查询------------");

		//调用登陆服务
		LoginHepler loginHelper = new LoginHepler();
		String responseCookie=loginHelper.loginHepler(c.getCasePreset(),"");
		System.out.println("responseCookie"+responseCookie);
		
		//指定http服务地址
		String httpUrl=Constant.broker_service_URL+"/article/demand.do";
		
		//由于服务的入参需要两种格式，即字符串和字符串数组，所以从excel读取的用例参数需要转换成这两种格式再入参
		Map<String,String> map = new HashMap<String,String>();
		map = c.getCaseParam();
		List<String> list=new ArrayList<String>();
		String mystr = "";
		
		for (Map.Entry<String, String> entry : map.entrySet()) {
			   //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			   list.add(entry.getKey()+entry.getValue());
			   //转换成字符串
			   mystr+=entry.getKey()+"="+entry.getValue()+"&";
		}
		if(mystr.lastIndexOf("&")>0){
			mystr=mystr.substring(0, mystr.length()-1);
		}
		
		System.out.println("拼装好的请求:"+mystr);
		//System.out.println("cookie="+responseCookie);
		//调用验证服务
        String ret=TopsUtil.sendPost(httpUrl, mystr);
        
            
        //打印结果
        System.out.println("服务返回结果:"+ret);
        JSONObject sobj = new JSONObject();
        sobj = JSONObject.fromObject(ret);
        String result = sobj.getString("status");
        Assert.assertNotEquals("1000001", result);
        Assert.assertNotEquals("-1", result);

	}
}
