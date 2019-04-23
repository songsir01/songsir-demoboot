package k.analysis.ctl;

import java.util.HashMap;
import java.util.Map;
import k.cc.uu.HttpUtil;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

public class SapCtl
{
    private static final String url = "http://192.168.110.239:10880/oms_sap";
    private static final Log log = Logs.get();

    @At({"/sap/index"})
    @Ok("jsp:jsp.sap.sap")
    public Object sap(String o)
    {
        return o;
    }

    @At({"/sap/send"})
    @Ok("json")
    public Object send(@Param("saleNos") String saleNos) {
        Map paramMap = new HashMap();
        paramMap.put("saleNos", saleNos);
        String result = HttpUtil.HttpPost("http://192.168.110.239:10880/oms_sap", "/sap/sendToSapByHand.rest", paramMap);
        log.info("send" + result);
        return result;
    }

    @At({"/sap/getCount"})
    @Ok("json")
    public Object getCount(@Param("flag") Integer flag) {
        Map paramMap = new HashMap();
        paramMap.put("flag", flag);
        String result = HttpUtil.HttpPost("http://192.168.110.239:10880/oms_sap", "/sap/getCount.rest", paramMap);
        log.info("getCount" + result);
        return result;
    }

    @At({"/sap/reSend"})
    @Ok("json")
    public Object reSend() {
        Map paramMap = new HashMap();
        String result = HttpUtil.HttpPost("http://192.168.110.239:10880/oms_sap", "/sap/reSend.rest", paramMap);
        log.info("reSend" + result);
        return result;
    }

    @At({"/sap/getRecord"})
    @Ok("json")
    public Object getRecord(@Param("saleNo") String saleNo) {
        Map paramMap = new HashMap();
        if (saleNo != null)
            saleNo = saleNo.trim();

        paramMap.put("saleNo", saleNo);
        String result = HttpUtil.HttpPost("http://192.168.110.239:10880/oms_sap", "/sap/getRecord.rest", paramMap);
        log.info("getRecord" + result);
        return result;
    }

    @At({"/sap/status"})
    @Ok("json")
    public Object status() {
        Map paramMap = new HashMap();
        String result = HttpUtil.HttpPost("http://192.168.110.239:10880/oms_sap", "/sapRelated/staus", paramMap);
        log.info("status" + result);
        return result;
    }

    @At({"/sap/operate"})
    @Ok("json")
    public Object operate(@Param("flag") String flag) {
        Map paramMap = new HashMap();
        paramMap.put("flag", flag);
        String result = HttpUtil.HttpPost("http://192.168.110.239:10880/oms_sap", "/sapRelated/operate", paramMap);
        log.info("operate" + result);
        return result;
    }
}