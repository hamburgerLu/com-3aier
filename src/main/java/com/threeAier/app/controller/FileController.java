package com.threeAier.app.controller;

import com.threeAier.app.common.base.AppBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("all")
@Slf4j
@Controller
@RequestMapping(value = "/file")
@Api(value = "fileController", description = "文件相关上传下载")
public class FileController extends AppBaseController {

    @RequestMapping(value = "/upload", produces = {"application/json"}, method = RequestMethod.POST)
    @ApiOperation(value = "上传接口", notes = "")
    public ResponseEntity upload(MultipartFile multipartFile){
        return new ResponseEntity(ok("获取成功",null), HttpStatus.OK);
    }

    @RequestMapping(value = "/download", produces = {"application/json"}, method = RequestMethod.GET)
    @ApiOperation(value = "下载接口", notes = "")
    public ResponseEntity download(int id){
        return new ResponseEntity(ok("下载成功",null), HttpStatus.OK);
    }


}
