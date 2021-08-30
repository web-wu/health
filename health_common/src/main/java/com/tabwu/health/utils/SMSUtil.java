package com.tabwu.health.utils;

public class SMSUtil {
    //用户名
    private static String Uid = "tabwu";

    //接口安全秘钥
    private static String Key = "d41d8cd98f00b204e980";

    //手机号码，多个号码如13800000000,13800000001,13800000002
    //private static String smsMob = "13800000000";

    //短信内容
    //private static String smsText = "验证码：8888";

    public static Boolean sendSMSShortMessage(String smsMob,String code) {

        HttpClientUtil client = HttpClientUtil.getInstance();

        String smsText = "您的验证码为:" + code + "，3分钟内有效，请勿泄露他人！！！";

        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    public static Boolean sendSMSOrderSuccess(String smsMob,String name,String OrderData) {

        HttpClientUtil client = HttpClientUtil.getInstance();

        String smsText = name + "---您已经成功在线预约体检套餐，请在" + OrderData + "准时达到，以免耽误计划安排";
        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

}
