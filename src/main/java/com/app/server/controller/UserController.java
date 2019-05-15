package com.app.server.controller;

import com.app.server.BaseApplication;
import com.app.server.mapper.StudentMappper;
import com.app.server.mapper.UserMappper;
import com.app.server.model.StudentModel;
import com.app.server.model.response.BaseResponse;
import com.app.server.model.response.DataResponse;
import com.app.server.model.response.ConstResponse;
import com.app.server.model.UserModel;
import com.app.server.util.PatternUtil;
import com.app.server.util.SafeUtil;
import com.app.server.util.TextUtils;
import com.app.server.util.Util;
import com.app.usb.ui.MainFrame;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserMappper userMappper;

    @Autowired
    private StudentMappper studentMappper;


    /**
     * 点击按钮启动服务端（其实刚开始已经启动过了，点击按钮会让界面显示出来）
     * 并在文本区显示hello world
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        //启动gui
        BaseApplication.mainFrame.setVisible(true);
        BaseApplication.mainFrame.addMsgNextLine("Hello！欢迎来到ClockingIn服务端");
        return "启动成功";
    }

    /**
     * 公用方法：调用函数向服务端发送一天数据，并且添加到文本框中（追加形式）
     * @param msg
     * @return
     */
    @RequestMapping("/setText")
    public String appenfText(String msg){
        BaseApplication.mainFrame.addMsgNextLine(msg);
        System.out.println(msg);
        return "发送成功";
    }

    /**
     * 测试com端口
     * @return
     */
    @RequestMapping("/testPort")
    public String testSend(){
        BaseApplication.mainFrame.tsetPort();
        return "测试端口";
    }


    /**
     * 通过名字查询学生信息
     * @param name
     * @return
     */
    @RequestMapping("/selectStudentAll")
    public String selectStudentByName(String name){
        StudentModel model=studentMappper.findStuByName(name);
        if (model!=null){
            return model.toString();
        }
        return name;
    }


    @RequestMapping("/selectClassByTeacherName")
    public List selectCalss(String teacherName){

        List<Map<String,Object>> list= studentMappper.findClassByTeacherName( teacherName);
        return list;
    }




/****************     下列接口暂时未使用     ******************/


    @RequestMapping("/user/login")
    public BaseResponse login(String name, String password){
        System.out.println("phone="+name+" password="+password);
        if(name==null||password==null){
            return new BaseResponse(ConstResponse.DESC_PARAM, ConstResponse.STATUS_PARAM_ERROR);
        }
        UserModel userModel= userMappper.findUserByname(name);
        if(userModel==null){
            return new BaseResponse("用户名不存在", ConstResponse.STATUS_KNOWN_ERROR);
        }
        password = SafeUtil.shortMD5(password);
        if(!userModel.password.equals(password)){
            return new BaseResponse("用户名或密码错误", ConstResponse.STATUS_KNOWN_ERROR);
        }
        return new DataResponse(userModel,"登录成功", ConstResponse.STATUS_OK);
    }

    @RequestMapping("/user/register")
    public BaseResponse register(String name, String password){
        System.out.println("phone="+name+" password="+password);
        if(name==null||password==null){
            return new BaseResponse(ConstResponse.DESC_PARAM, ConstResponse.STATUS_PARAM_ERROR);
        }

        String nameCheck=PatternUtil.isNickname(name);
        if (!TextUtils.isEmpty(nameCheck)) {
            return new BaseResponse(nameCheck, ConstResponse.STATUS_KNOWN_ERROR);
        }
        if (!PatternUtil.isPassword(password)) {
            return new BaseResponse("请输入6~18位密码", ConstResponse.STATUS_KNOWN_ERROR);
        }
        UserModel userfind = userMappper.findUserByname(name);
        if (userfind != null) {
            return new BaseResponse("用户已注册", ConstResponse.STATUS_KNOWN_ERROR);
        }
        String token = SafeUtil.MD5(name + "app" + password);
        password = SafeUtil.shortMD5(password);
        userMappper.register(name, password, token);
        return new DataResponse(userMappper.findUserByname(name), ConstResponse.DESC_OK,ConstResponse.STATUS_OK);
    }


}