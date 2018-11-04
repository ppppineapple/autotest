package com.tops.normalTest;

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

public class CheckLoginTest {
    protected String caseExcelPath = System.getProperty("user.dir") + "/javaexcel/CheckLoginTest.xlsx";

    @DataProvider(name = "dataInfo")
    protected Object[][] dataInfo1() throws IOException {

        Object[][] myObj = null;
        List<Map<String, String>> list = ReadExcel.readXlsx(caseExcelPath);
        System.out.println(caseExcelPath);
        myObj = CaseHelper.getObjArrByList(list);
        return myObj;
    }

    @Test(dataProvider = "dataInfo")
    public void checkLoginTest(CaseInfo c) throws Exception {

        System.out.println("-------------登陆------------");

        //指定http服务地址
        String httpUrl = "http://user.test.apitops.com/Authorization/Login";

        //字符串和字符串数组
        Map<String, String> map = new HashMap<String, String>();
        map = c.getCasePreset();
        List<String> list = new ArrayList<String>();


        String mystr = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            list.add(entry.getKey() + entry.getValue());
            //转换成字符串
            mystr += entry.getKey() + "=" + entry.getValue() + "&";
        }
        if (mystr.lastIndexOf("&") > 0) {
            mystr = mystr.substring(0, mystr.length() - 1);
        }

        System.out.println("拼装好的请求:" + mystr);
        System.out.println(httpUrl);
        //调用验证服务
        String ret = TopsUtil.sendPost(httpUrl, mystr);


        //打印结果
        System.out.println("服务返回结果:" + ret);
        JSONObject sobj = new JSONObject();
        sobj = JSONObject.fromObject(ret);
        System.out.println(sobj);
        JSONObject data = (JSONObject) sobj.get("Data");
        System.out.println(data);
        String Authorization = data.getString("AccessToken");
        System.out.println(Authorization);

    }
}
