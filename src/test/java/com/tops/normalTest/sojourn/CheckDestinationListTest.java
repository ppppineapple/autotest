package com.tops.normalTest.sojourn;

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

public class CheckDestinationListTest {
    protected String caseExcelPath =System.getProperty("user.dir")+"/javaexcel/CheckDestinationDetailTest.xlsx";

    @DataProvider(name = "dataInfo")
    protected Object[][] dataInfo1() throws IOException {

        Object[][] myObj = null;
        List<Map<String, String>> list = ReadExcel.readXlsx(caseExcelPath);
        System.out.println(caseExcelPath);
        myObj = CaseHelper.getObjArrByList(list);
        return myObj;
    }

    @Test(dataProvider="dataInfo")
    public void CheckDestinationListTest(CaseInfo c) throws Exception{

        //登陆
        LoginHepler loginHelper = new LoginHepler();
        String authorization = loginHelper.loginHepler(c.getCasePreset(), "");
        System.out.println("凭证:"+authorization);

        //指定http服务地址
        String httpUrl="http://broker-service.test.apitops.com/broker-service-web/v1/sojourn/destination/list";

        //字符串和字符串数组
        Map<String,String> map = new HashMap<String,String>();
        map = c.getCaseParam();
        List<String> list=new ArrayList<String>();


        String mystr = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add(entry.getKey()+entry.getValue());
            //转换成字符串
            mystr+=entry.getKey()+"="+entry.getValue()+"&";
        }
        if(mystr.lastIndexOf("&")>0){
            mystr=mystr.substring(0, mystr.length()-1);
        }
        System.out.println(mystr);
        //调用验证服务
        String ret=TopsUtil.sendGet(httpUrl, mystr, authorization);

        //打印结果
        System.out.println("服务返回结果:"+ret);
        JSONObject sobj = new JSONObject();
        sobj = JSONObject.fromObject(ret);
        //String result = sobj.getString("Code");
        //Assert.assertEquals("0", result);

    }
}





