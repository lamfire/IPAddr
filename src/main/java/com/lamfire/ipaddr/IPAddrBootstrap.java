package com.lamfire.ipaddr;

import com.lamfire.logger.Logger;
import com.lamfire.utils.NumberUtils;
import com.lamfire.utils.StringUtils;
import com.lamfire.utils.TypeConvertUtils;
import com.lamfire.warden.HttpServer;

/**
 * User: linfan
 * Date: 16-1-5
 * Time: 下午2:54
 */
public class IPAddrBootstrap {
    private static final Logger LOGGER = Logger.getLogger("IPAddrBootstrap");
    public static void main(String[] args) throws Exception{
        String host = "0.0.0.0";
        int port = 8899;

        for(String a : args){
            if(StringUtils.isStartWithIgnoreCase(a,"-h")){
                host = StringUtils.substring(a,2);
            }else if(StringUtils.isStartWithIgnoreCase(a,"-p")){
                port = TypeConvertUtils.toInt(StringUtils.substring(a,2));
            }
        }

        HttpServer http = new HttpServer(host,port);
        http.registerAll("com.lamfire.ipaddr");
        http.startup();
        LOGGER.info("startup - "+host+":"+port);
    }
}
