package com.lamfire.ipaddr;

import com.lamfire.logger.Logger;
import com.lamfire.warden.HttpServer;

/**
 * User: linfan
 * Date: 16-1-5
 * Time: 下午2:54
 */
public class IPAddrBootstrap {
    private static final Logger LOGGER = Logger.getLogger("IPAddrBootstrap");
    public static void main(String[] args) throws Exception{

        HttpServer http = new HttpServer("0.0.0.0",8899);
        http.registerAll("com.lamfire.ipaddr");
        http.startup();
        LOGGER.info("startup - 0.0.0.0:8899");
    }
}
