package com.lamfire.test;

import com.lamfire.ipaddr.IPIP;
import com.lamfire.ipaddr.ShowIPAction;
import com.lamfire.json.JSON;
import com.lamfire.logger.Logger;
import com.lamfire.qqwryparser.AddrInfo;
import com.lamfire.qqwryparser.QQwryUtils;

/**
 * Created with IntelliJ IDEA.
 * User: linfan
 * Date: 16-1-5
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    private static AddrInfo getAddrInfo(String ipaddr){
        AddrInfo addr = null;

        try {
            addr = IPIP.getInstance().getAddrInfo(ipaddr);
            LOGGER.info("IPIP[" + ipaddr +"] -> " + addr);
        }catch (Exception e){
            LOGGER.error("IPIP[" + ipaddr +"] -> " + e.getMessage(),e);
        }

        if(addr == null){
            addr = QQwryUtils.getAddrInfo(ipaddr);
            LOGGER.info("CZ88[" + ipaddr +"] -> " + addr);
        }
        return addr;
    }
    public static void main(String[] args) {
        AddrInfo addr = getAddrInfo("120.132.144.56");
        System.out.println(JSON.toJSONString(addr));
    }
}
