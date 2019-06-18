package com.lamfire.ipaddr;

import com.lamfire.json.JSON;
import com.lamfire.logger.Logger;
import com.lamfire.qqwryparser.AddrInfo;
import com.lamfire.qqwryparser.QQwryUtils;
import com.lamfire.utils.StringUtils;
import com.lamfire.warden.Action;
import com.lamfire.warden.ActionContext;
import com.lamfire.warden.anno.ACTION;

/**
 * Created with IntelliJ IDEA.
 * User: linfan
 * Date: 16-1-5
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
@ACTION(path = "/")
public class ShowIPAction implements Action {
    private static final Logger LOGGER = Logger.getLogger(ShowIPAction.class);
    @Override
    public void execute(ActionContext context) {
        String ipaddr = context.getHttpRequestParameters().getString("q");
        if(StringUtils.isBlank(ipaddr)){
            ipaddr = context.getHttpRequestParameters().getString("ip");
        }else if(StringUtils.isBlank(ipaddr)){
            ipaddr = context.getHttpRequestParameters().getString("addr");
        }

        if(StringUtils.isBlank(ipaddr)){
            ipaddr = context.getRealRemoteAddr();
        }

        AddrInfo addr = getAddrInfo(ipaddr);

        String result = JSON.toJSONString(addr);
        //LOGGER.info(ipaddr +" -> " + result);
        context.setResponseHeader("Content-Type","text/plain;charset=UTF-8");
        context.writeResponse(result,"utf-8");
    }

    private AddrInfo getAddrInfo(String ipaddr){
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
}
