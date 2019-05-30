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
     * 测试server是否连接成功
     * @return
     */
    @RequestMapping("/testServer")
    public String testServer(){
        return "连接成功";
    }

//    @RequestMapping("/testApi")
//    public void testApi(){
//        BaseApplication.mainFrame.testApi();
//    }

    /**
     * 通过名字查询学生信息
     * @param name
     * @return
     */
    @RequestMapping("/selectStudentAll")
    public String selectStudentByName(String name){
        StudentModel model=studentMappper.findStuByName(name);
        if (model!=null){
            //server端显示数据

           // BaseApplication.mainFrame.addMsgNextLine(model.toString());
            return model.getString();
        }
        return name;
    }


    /**
     * 根据老师的名字查询老师课程
     * @param teacherName
     * @return
     */
    @RequestMapping("/selectClassByTeacherName")
    public List selectCalss(String teacherName){
        List<Map<String,Object>> list= studentMappper.findClassByTeacherName( teacherName);
//        for (int i=0;i<list.size();i++){
            //server端添加数据
            BaseApplication.mainFrame.addMsgNextLine(list.toString());
       // }
        return list;
    }

    /**
     * 返回某课程下所有学生名单
     * @param className
     * @return
     */
    @RequestMapping("/selectStuByClassName")
    public List selectStuByClassName(String className){
        List<Map<String,Object>> list=studentMappper.findStuByClass(className);
        BaseApplication.mainFrame.addMsgNextLine(className+"下的学生名单如下：");
        //for (int i=0;i<list.size();i++){
            //添加学生名单
        BaseApplication.mainFrame.addMsgNextLine(list.toString());
        //}
        return list;
    }

    /**
     * 根据卡片id搜索对应人名
     * @param cardId
     * @return
     */
    @RequestMapping("/selectNameByCard")
    public String selectNameByCard(String cardId){
        String name=studentMappper.findStuByCardNum(cardId);
        if (name!=null){
            return name;
        }
        return "该卡片未绑定";
    }

    /**
     * 根据情况来设置不同周签到情况
     * @param weekNum
     * @param name
     * @return 影响行数
     */
    @RequestMapping("/updateWeek")
    public int updateWeek(String weekNum,String name){
       switch (weekNum){
           case "第一周":
                return studentMappper.updateWeekOneByName(name);
           case "第二周":
               return studentMappper.updateWeekTwoByName(name);
           case "第三周":
               return studentMappper.updateWeekThreeByName(name);
           case "第四周":
               return studentMappper.updateWeekFourByName(name);
           case "第五周":
               return studentMappper.updateWeekFiveByName(name);
           case "第六周":
               return studentMappper.updateWeekSixByName(name);
           case "第七周":
               return studentMappper.updateWeekSevenByName(name);
           case "第八周":
               return studentMappper.updateWeekEightByName(name);
           case "第九周":
               return studentMappper.updateWeekNineByName(name);
           case "第十周":
               return studentMappper.updateWeekTenByName(name);
               default:break;
       }
       return 0;
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