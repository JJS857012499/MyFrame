package com.zengfull.myfragme.api;

import android.util.Base64;

import com.zengfull.myfragme.model.CustomerModel;
import com.zengfull.myfragme.model.IdentityParamReq;
import com.zengfull.myfragme.model.LoginParamReq;
import com.zengfull.myfragme.model.RspResultModel;
import com.zengfull.myfragme.util.FuncUtil;
import com.zengfull.myfragme.util.rsa.SecurityUtil;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;


public class GlobalHttpApi {

    public static final String NAMESPACE_IDENTITY = "http://auth.service.cxf.pro.zf.com/";
    public static final String NAMESPACE_TRADE = "http://trade.service.cxf.pro.zf.com/";
    public static final String NAMESPACE_NOTICE = "http://push.service.cxf.pro.zf.com/";

    //测试
    public static final String APP_API_DOMAIN = "http://10.252.1.166:8090/fastpay/iservice/";

    public static final String URL_API_IDENTITYSERVICE = "IdentityService";
    public static final String URL_API_TRADE = "Trade_Service";
    public static final String URL_API_NOTICE = "PushMessageService";


    private IdentityParamReq idPrama;

    public RspResultModel<String> getServiceKey() {
        RspResultModel rsp = new RspResultModel();
        try {
            SoapObject body = new SoapObject(NAMESPACE_IDENTITY, "GetServerPublicKey");
            SoapObject object = doRequest(fullUrl(URL_API_IDENTITYSERVICE), null, body);
            String key = object.getProperty(0).toString();
            if (FuncUtil.isEmpty(key) || key.length() < 32) {
                rsp.retcode = "1";
                rsp.retmsg = "获取公钥失败";
            } else {
                rsp.retcode = "0";
                rsp.retmsg = "获取公钥成功";
                rsp.source = key;
            }
        } catch (Exception e) {
            // TODO: handle exception
            rsp.retcode = "1";
            rsp.retmsg = "获取公钥失败";
        }
        return rsp;
    }


    public RspResultModel<CustomerModel> login(String name, String password, byte[] serverkey) {
        RspResultModel<CustomerModel> rsp = new RspResultModel<CustomerModel>();
        try {
            SoapObject body = new SoapObject(NAMESPACE_IDENTITY, "LoginExt");
            byte[] loginname = SecurityUtil.getRasCoder(name, serverkey);
            byte[] loginpwd = SecurityUtil.getRasCoder(password, serverkey);
            LoginParamReq req = new LoginParamReq();
            //
            req.clientPublicKey = SecurityUtil.getCoder().getPublicKey();
            req.loginname = loginname;
            req.loginpasswd = loginpwd;
            req.logintype = 15 == name.trim().length() ? "0" : "1";

            body.addProperty("LoginRequest", req);

            SoapObject object = doRequest(fullUrl(URL_API_IDENTITYSERVICE), null, body);
            if (object == null || object.getPropertyCount() == 0 || FuncUtil.isEmpty(object.getProperty(0).toString())) {
                rsp.retcode = "1";
                rsp.retmsg = "登录失败";
            } else {
                SoapObject retObject = (SoapObject) object.getProperty(0);
                rsp.retcode = retObject.getProperty("retcode").toString();
                rsp.retmsg = retObject.getProperty("retmsg").toString();
                if ("000".equals(rsp.retcode)) {
                    CustomerModel user = new CustomerModel();
                    user.name = SecurityUtil.getRasDecryptString(retObject.getPropertyAsString("loginname"));
                    user.serverpwd = SecurityUtil.getRasDecryptString(retObject.getProperty("loginpasswd").toString());
                    user.mobile = SecurityUtil.getRasDecryptString(retObject.getProperty("loginphone").toString());
                    user.cusName = retObject.getProperty("merchant_name").toString();
                    user.sessionId = retObject.getProperty("mobilecode").toString();
                    user.loginType = retObject.getProperty("usertype").toString();
                    user.version = retObject.getProperty("versioncode").toString();
                    user.pwd = password;
                    rsp.source = user;
                    rsp.retcode = "0";
                    rsp.retmsg = "登录成功";
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            rsp.retcode = "1";
            rsp.retmsg = "登录失败";
        }
        return rsp;

    }


    private String fullUrl(String url) {
        return APP_API_DOMAIN + url;
    }


    public Element buildHead(CustomerModel user, byte[] serverkey) throws Exception {
        if (user == null || user.isUserNull()) {
            return null;
        }
        Element header = new Element();
        header.setName("authHeader");
        byte[] loginname = SecurityUtil.getRasCoder(user.name, serverkey);
        byte[] loginpwd = SecurityUtil.getRasCoder(user.serverpwd, serverkey);
        byte[] phone = SecurityUtil.getRasCoder(user.mobile, serverkey);
        byte[] session = SecurityUtil.getRasCoder(user.sessionId, serverkey);
        byte[] version = SecurityUtil.getRasCoder(user.version, serverkey);
        Element userType = new Element();
        userType.setName("usertype");
        userType.addChild(Node.TEXT, user.loginType);
        header.addChild(Node.ELEMENT, userType);


        Element phoneEle = new Element();
        phoneEle.setName("phone");
        phoneEle.addChild(Node.TEXT, android.util.Base64.encodeToString(phone, Base64.DEFAULT));
        header.addChild(Node.ELEMENT, phoneEle);

        Element nameEle = new Element();
        nameEle.setName("username");
        nameEle.addChild(Node.TEXT, android.util.Base64.encodeToString(loginname, Base64.DEFAULT));
        header.addChild(Node.ELEMENT, nameEle);

        Element pwdEle = new Element();
        pwdEle.setName("password");
        pwdEle.addChild(Node.TEXT, android.util.Base64.encodeToString(loginpwd, Base64.DEFAULT));
        header.addChild(Node.ELEMENT, pwdEle);

        Element mobileCodeEle = new Element();
        mobileCodeEle.setName("mobilecode");
        mobileCodeEle.addChild(Node.TEXT, android.util.Base64.encodeToString(session, Base64.DEFAULT));
        header.addChild(Node.ELEMENT, mobileCodeEle);

        Element versionEle = new Element();
        versionEle.setName("versioncode");
        versionEle.addChild(Node.TEXT, android.util.Base64.encodeToString(version, Base64.DEFAULT));
        header.addChild(Node.ELEMENT, versionEle);
//		log.info("session:"+session.length);
        idPrama = new IdentityParamReq();
        idPrama.setProperty(0, session);
        idPrama.setProperty(1, loginname);
        idPrama.setProperty(2, loginpwd);
        idPrama.setProperty(3, phone);
        idPrama.setProperty(4, user.loginType);
        return header;
    }


    public SoapObject doRequestHttps(String url, Element header, SoapObject body) throws Exception {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.bodyOut = body;
        envelope.dotNet = false;
        if (header != null) {
            Element[] headerOut = new Element[1];
            headerOut[0] = header;
            envelope.headerOut = headerOut;
        }
        envelope.setOutputSoapObject(body);
        new MarshalBase64().register(envelope);
        SSLConection.allowAllSSL();
        HttpTransportSE httpTransportSE = new HttpTransportSE(url, 10000);
        httpTransportSE.debug = true;
        httpTransportSE.call("", envelope);
        SoapObject object = (SoapObject) envelope.bodyIn;
        return object;
    }

    public SoapObject doRequest(String url, Element header, SoapObject body) throws Exception {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.bodyOut = body;
        envelope.dotNet = false;
        if (header != null) {
            Element[] headerOut = new Element[1];
            headerOut[0] = header;
            envelope.headerOut = headerOut;
        }
        envelope.setOutputSoapObject(body);
        new MarshalBase64().register(envelope);
        HttpTransportSE httpTransportSE = new HttpTransportSE(url, 10000);
        httpTransportSE.debug = true;
        httpTransportSE.call("", envelope);
        SoapObject object = (SoapObject) envelope.bodyIn;
        return object;
    }

    private RspResultModel buildRsp(String retcode, String retmsg) {
        RspResultModel rsp = new RspResultModel();
        rsp.retcode = retcode;
        rsp.retmsg = retmsg;
        return rsp;
    }

}
