package com.zengfull.myfragme.util;

import android.content.Context;
import android.content.Intent;

import com.zengfull.myfragme.GlobalApp;
import com.zengfull.myfragme.model.RspResultModel;

/**
 * Created by asus on 2015/12/21.
 */
public class CommonUI {

    public static boolean checkRsp(RspResultModel rsp) {
        return checkRsp(GlobalApp.getInstance(), rsp, true);
    }

    public static boolean checkRsp(Context context, RspResultModel rsp) {
        return checkRsp(context, rsp, true);
    }

    public static boolean checkRsp(RspResultModel rsp, boolean showTips) {
        return checkRsp(GlobalApp.getInstance(), rsp, showTips);
    }

    public static boolean checkRsp(Context context, RspResultModel rsp, boolean showTips) {
        String msg = "";
        if (rsp == null) {
            msg = "网络不给力哦~";
        } else {

            if (!"0".equals(rsp.retcode)) {
                msg = FuncUtil.isEmpty(rsp.retmsg) ? "网络不给力哦~" : rsp.retmsg;
            }

        }

        if (FuncUtil.isEmpty(msg)) {
            return true;
        } else {
            if (showTips) {
//                DialogUtil.showToast(context, msg);
            }

            if ("I004".equals(rsp.retcode) || "1999".equals(rsp.retcode)) {
//                GlobalApp.getInstance().getCoreService().loginOut();
//                Intent intent = new Intent(context, UserLoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                context.startActivity(intent);
            }
            return false;
        }
    }

}
