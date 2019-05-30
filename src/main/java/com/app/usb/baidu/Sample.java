//package com.app.usb.baidu;
//
//import com.baidu.aip.speech.AipSpeech;
//import com.baidu.aip.speech.TtsResponse;
//import com.baidu.aip.util.Util;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//public class Sample {
//    //设置APPID/AK/SK
//    public static final String APP_ID = "16347492";
//    public static final String API_KEY = "0rv4RB6wkjFds6azGlkGzgXP";
//    public static final String SECRET_KEY = "jdzNszQa8P62jIac1zA0rVCrR90Z5HS2";
//
//    public static void main(String[] args) {
//        // 初始化一个AipSpeech
//        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
////
////        // 可选：设置网络连接参数
////        client.setConnectionTimeoutInMillis(2000);
////        client.setSocketTimeoutInMillis(60000);
//
////        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
////        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
////        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
////
//        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
//        // 也可以直接通过jvm启动参数设置此环境变量
//       // System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");
//
//        // 调用接口
//        TtsResponse res = client.synthesis("你好百度", "zh", 1, null);
//        byte[] data = res.getData();
//        JSONObject res1 = res.getResult();
//        if (data != null) {
//            try {
//                Util.writeBytesToFileSystem(data, "output.mp3");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (res1 != null) {
//            System.out.println(res1.toString(2));
//        }
//
//    }
//
////    public void synthesis(AipSpeech client)
////    {
////        TtsResponse res = client.synthesis("你好百度", "zh", 1, null);
////        System.out.println(res.getErrorCode());
////
////        // 设置可选参数
////        HashMap<String, Object> options = new HashMap<String, Object>();
////        options.put("spd", "5");
////        options.put("pit", "5");
////        options.put("per", "4");
////        TtsResponse res = client.synthesis("你好百度", "zh", 1, options);
////        System.out.println(res.getErrorCode());
////        JSONObject result = res.getResult();    //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
////        byte[] data = res.getData();            //生成的音频数据
////    }
//}