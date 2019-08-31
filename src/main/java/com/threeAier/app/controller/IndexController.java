package com.threeAier.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.threeAier.app.common.base.AppBaseController;
import com.threeAier.app.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("all")
@Slf4j
@Controller
@RequestMapping(value = "/")
@Api(value = "indexController", description = "基础controller")
public class IndexController extends AppBaseController {

    @Autowired
    private IndexService indexService;


    @RequestMapping(value = "/user/login", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "登录接口", notes = "")
    public ResponseEntity login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody JSONObject jsonObject){
        String userName = jsonObject.getString("userName");
        String password = jsonObject.getString("password");
        if (indexService.login(userName,password)){
            HttpSession session=httpServletRequest.getSession();
            session.setAttribute("USER", userName);

            return new ResponseEntity(ok("登录成功",null), HttpStatus.OK);
        }

        return new ResponseEntity(fail("登录失败",null), HttpStatus.OK);
    }


    @RequestMapping(value = "/")
    @ApiOperation(value = "index页面", notes = "")
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/news")
    @ApiOperation(value = "新闻页面", notes = "")
    public String news(){
        return "news";
    }



//    @RequestMapping(value = "/art_list")
//    @ApiOperation(value = "新闻页面", notes = "")
//    public String art_list(){
//        return "art-list";
//    }

    @RequestMapping(value = "/back_index")
    @ApiOperation(value = "新闻页面", notes = "")
    public String back_index(){
        return "back-index";
    }

    @RequestMapping(value = "/login")
    @ApiOperation(value = "登录", notes = "")
    public String login(){
        return "login";
    }
}
