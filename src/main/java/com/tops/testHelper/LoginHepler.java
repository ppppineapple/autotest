package com.tops.testHelper;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

public class LoginHepler {
    public String loginHepler(Map<String, String> map, String responseCookie) {


        //指定http服务地址
        String httpUrl = "http://user.test.apitops.com/Authorization/Login";

        //字符串和字符串数组
        //Map<String,String> map = new HashMap<String,String>();
        //map = c.getCasePreset();
        List<String> list = new ArrayList<String>();

        String mystr = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add(entry.getKey() + entry.getValue());
            //转换成字符串
            mystr += entry.getKey() + "=" + entry.getValue() + "&";
        }
        if (mystr.lastIndexOf("&") > 0) {
            mystr = mystr.substring(0, mystr.length() - 1);
        }

        //System.out.println("拼装好的请求:"+mystr);
        //调用验证服务
        String ret = TopsUtil.sendPost(httpUrl, mystr);


        //打印结果
        JSONObject sobj = new JSONObject();
        sobj = JSONObject.fromObject(ret);
        JSONObject data = (JSONObject) sobj.get("Data");
        String Authorization = data.getString("AccessToken");
        return Authorization;

    }

}
