package com.tops.testHelper;

public class Constant {
    private String cookie;

    //public final static String URL ="http://172.16.12.40:7005/test";
    public final static String broker_service_URL = "http://broker-service.test.apitops.com/broker-service-web/v1";

    /*public final static String broker_service_URL ="http://121.40.148.228";
    public final static String broker_service_URL ="http://121.40.148.228";
    public final static String broker_service_URL ="http://121.40.148.228";
    public final static String broker_service_URL ="http://121.40.148.228";*/
    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
