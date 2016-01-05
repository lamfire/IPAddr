package com.lamfire.ipaddr;

import com.lamfire.json.JSON;
import com.lamfire.logger.Logger;
import com.lamfire.qqwryparser.AddrInfo;
import com.lamfire.qqwryparser.QQwryUtils;
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
        String remoteAddr = context.getRealRemoteAddr();
        AddrInfo addr = QQwryUtils.getAddrInfo(remoteAddr);
        String result = JSON.toJSONString(addr);
        LOGGER.info(remoteAddr +" -> " + result);
        context.setResponseHeader("Content-Type","text/plain;charset=UTF-8");
        context.writeResponse(result,"utf-8");

    }
}
