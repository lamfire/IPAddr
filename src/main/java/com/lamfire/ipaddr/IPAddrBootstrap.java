package com.lamfire.ipaddr;

import com.lamfire.warden.HttpServer;

/**
 * Created with IntelliJ IDEA.
 * User: linfan
 * Date: 16-1-5
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
public class IPAddrBootstrap {
    public static void main(String[] args) throws Exception{
        HttpServer http = new HttpServer("0.0.0.0",8899);
        http.registerAll("com.lamfire.ipaddr");
        http.startup();
    }
}
