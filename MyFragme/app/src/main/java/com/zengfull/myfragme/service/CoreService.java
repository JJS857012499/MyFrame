package com.zengfull.myfragme.service;

import android.util.Base64;

import com.zengfull.myfragme.GlobalApp;
import com.zengfull.myfragme.api.GlobalHttpApi;
import com.zengfull.myfragme.model.CustomerModel;
import com.zengfull.myfragme.model.RspResultModel;
import com.zengfull.myfragme.util.CommonUI;
import com.zengfull.myfragme.util.FuncUtil;
import com.zengfull.myfragme.util.SharePreferenceUtil;

/**
 * Created by asus on 2015/12/21.
 */
public class CoreService {

    private static CoreService instance;
    private GlobalHttpApi api;
    private CustomerModel customer;

    private CoreService(){
        api = new GlobalHttpApi();
    }

    public static CoreService getInstance(){
        if(instance == null){
            synchronized (CoreService.class){
                if(instance == null){
                    instance = new CoreService();
                }
            }
        }
        return instance;
    }

    public byte[] getServiceKey(boolean isServer){
        String key = "";
        if(isServer){
            RspResultModel<String> rsp = api.getServiceKey();
            if(CommonUI.checkRsp(rsp,false)){//如果获取成功，则进行
                GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_KEY, rsp.source);
                key = rsp.source;
            }
            else{//如果获取失败，则获取缓存
                key = GlobalApp.getInstance().getSpUtil().getValue(SharePreferenceUtil.SPKEY_KEY);
            }
        }
        else{
            key = GlobalApp.getInstance().getSpUtil().getValue(SharePreferenceUtil.SPKEY_KEY);
        }
        if(FuncUtil.isEmpty(key)){
            return null;
        }
        else{
            return android.util.Base64.decode(key, Base64.DEFAULT);
        }

    }

    public RspResultModel login(String name, String pwd, boolean isSavePwd){
        byte[] serverKey = getServiceKey(true);

        RspResultModel<CustomerModel> rsp = api.login(name, pwd, serverKey);
        if(CommonUI.checkRsp(rsp,false)){//如果获取成功，则进行缓存
            CustomerModel user = rsp.source;
            GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_LOGINTYPE, user.loginType);
            GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_NAME, user.name);
            GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_PHONE, user.mobile);
            GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_SERVERPWD, user.serverpwd);
            GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_CUSNAME, user.cusName);
            GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_SAVENAME, name);
            customer = new CustomerModel();
            customer.loginType = user.loginType;
            customer.name = user.name;
            customer.mobile = user.mobile;
            customer.serverpwd = user.serverpwd;
            customer.cusName = user.cusName;
            customer.sessionId = user.sessionId;
            customer.version = user.version;
            if(isSavePwd){
                GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_ISSAVEPWD, "1");
                GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_SAVEPWD, pwd);
            }
            else{
                GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_ISSAVEPWD, "0");
                GlobalApp.getInstance().getSpUtil().setParam(SharePreferenceUtil.SPKEY_SAVEPWD, "");
            }
        }
        return rsp;
    }



}
