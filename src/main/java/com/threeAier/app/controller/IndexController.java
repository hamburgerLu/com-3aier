package com.threeAier.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SuppressWarnings("all")
@Slf4j
@Controller
@RequestMapping(value = "/")
@Api(value = "indexController", description = "基础controller")
public class IndexController extends AppBaseController{


    @RequestMapping(value = "/login", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "登录接口", notes = "")
    public ResponseEntity login(String userName,String password){
        return new ResponseEntity(ok("登录成功",null), HttpStatus.OK);
    }


    @RequestMapping(value = "/")
    @ApiOperation(value = "登录接口", notes = "")
    public String index(){
        return "index";
    }
}
