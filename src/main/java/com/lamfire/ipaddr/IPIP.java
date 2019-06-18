package com.lamfire.ipaddr;

import com.lamfire.logger.Logger;
import com.lamfire.qqwryparser.AddrInfo;
import com.lamfire.utils.ClassLoaderUtils;
import net.ipip.ipdb.City;
import net.ipip.ipdb.IPFormatException;
import net.ipip.ipdb.InvalidDatabaseException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class IPIP {
    private static final Logger LOGGER = Logger.getLogger("IPIP");
    private static final String IPIP_FREE = "ipipfree.ipdb";

    private static final IPIP instance = new IPIP();
    public static final IPIP getInstance(){
        return instance;
    }

    private City db;

    private IPIP(){
        try {
            db = new City(ClassLoaderUtils.getResourceAsFile(IPIP_FREE, IPIP.class).getAbsolutePath());
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
    }
    public  AddrInfo getAddrInfo(String ip) throws IPFormatException, InvalidDatabaseException, UnknownHostException {
        if(db == null){
            throw new InvalidDatabaseException("ipipfree.ipdb not found");
        }
        String[] info = db.find(ip, "CN");
        AddrInfo a = new AddrInfo();
        a.setCountry(info[0]);
        a.setProvince(info[1]);
        a.setCity(info[2]);
        a.setAddress(InetAddress.getByAddress(encode(ip)));
        a.setProviderName("未知");
        return a;
    }

    public static byte[] encode(String ip) {
        byte[] ret = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");

        try {
            ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 255);
            ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 255);
            ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 255);
            ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 255);
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
        }

        return ret;
    }
}
