package com.lamfire.test;

import com.lamfire.json.JSON;
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
    public static void main(String[] args) {
        AddrInfo addr = QQwryUtils.getAddrInfo("120.132.144.56");
        System.out.println(JSON.toJSONString(addr));
    }
}
