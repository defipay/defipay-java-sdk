package com.defipay.api.client.config;

public class Env {
    //todo
    public static Env SANDBOX = new Env("https://api-test.defipay.biz/", "0314c127b69c03545b49aaf365c56575ce9b1640cad8eb6ca47c34c8322fe9f4d4");
    public static Env PROD = new Env("https://api-test.defipay.biz/", "0314c127b69c03545b49aaf365c56575ce9b1640cad8eb6ca47c34c8322fe9f4d4");

    public String host;
    public String defipayPub;

    public Env(String host, String defipayPub) {
        this.host = host;
        this.defipayPub = defipayPub;
    }

}
