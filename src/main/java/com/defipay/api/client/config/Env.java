package com.defipay.api.client.config;

public class Env {
    //todo
    public static Env SANDBOX = new Env("http://192.168.50.144:8887/", "3056301006072a8648ce3d020106052b8104000a03420004a8a111a6c040765fa13fffcd498d3d7326ad98b84e6858807a73a01fcc529287183c909dcd3a695ba0003abdfccfcc47aad6994ad7c9a4ed20463ad98656083e");
    public static Env PROD = new Env("http://47.97.49.47:8888/api-service/", "3056301006072a8648ce3d020106052b8104000a03420004a8a111a6c040765fa13fffcd498d3d7326ad98b84e6858807a73a01fcc529287183c909dcd3a695ba0003abdfccfcc47aad6994ad7c9a4ed20463ad98656083e");

    public String host;
    public String defipayPub;

    public Env(String host, String defipayPub) {
        this.host = host;
        this.defipayPub = defipayPub;
    }

}
