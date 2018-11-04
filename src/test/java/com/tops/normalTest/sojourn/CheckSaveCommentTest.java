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
import MysqlOperation.*;

import net.sf.json.JSONObject;

public class CheckSaveCommentTest {
    protected String caseExcelPath = System.getProperty("user.dir") + "/javaexcel/CheckSaveCommentTest.xlsx";

    @DataProvider(name = "dataInfo")
    protected Object[][] dataInfo1() throws IOException {

        Object[][] myObj = null;
        List<Map<String, String>> list = ReadExcel.readXlsx(caseExcelPath);
        System.out.println(caseExcelPath);
        myObj = CaseHelper.getObjArrByList(list);
        return myObj;
    }

    @Test(dataProvider = "dataInfo")
    public void CheckSaveCommentTest(CaseInfo c) throws Exception {

        //登陆
        LoginHepler loginHelper = new LoginHepler();
        String authorization = loginHelper.loginHepler(c.getCasePreset(), "");
        System.out.println("凭证:" + authorization);

        //指定http服务地址
        String httpUrl = "http://broker-service.test.apitops.com/broker-service-web/v1/sojourn/comment/save";

        //字符串和字符串数组
        Map<String, String> map = new HashMap<String, String>();
        map = c.getCaseParam();

        JSONObject mystr = new JSONObject();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                //System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                mystr.put(entry.getKey(), entry.getValue());
            }
        }
        String ret = TopsUtil.sendPost(httpUrl, mystr, authorization);


        //打印结果
        System.out.println("服务返回结果:" + ret);
        JSONObject sobj = new JSONObject();
        sobj = JSONObject.fromObject(ret);
        String result = sobj.getString("Code");
        Assert.assertEquals("0", result);
//        String sql = "";
//        SqlOperation.query(sql);


    }
}





